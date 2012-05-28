package com.lunch4you.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunch4you.dao.ArticleDao;
import com.lunch4you.dao.CustomerDao;
import com.lunch4you.dao.OrderDao;
import com.lunch4you.dao.filter.CustomerFilter;
import com.lunch4you.dao.filter.OrderFilter;
import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.Order;
import com.lunch4you.domain.OrderItem;

@Service
public final class MenuServiceImpl implements MenuService {

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public Article findArticleById( Long id ) {
		return articleDao.load( id );
	}

	@Override
	public Customer findCustomerByToken( String token ) {
		CustomerFilter filter = new CustomerFilter();
		filter.token = token;

		List<Customer> foundCustomers = customerDao.find( filter );
		return foundCustomers.size() == 0 ? null : foundCustomers.get( 0 );
	}

	@Override
	public Order createOrder( Customer customer, Article article ) {
		OrderItem item = new OrderItem();

		item.setAmount( 1 );
		item.setArticle( article );
		item.setName( article.getName() );
		item.setUnitPrice( article.getPrice() );
		item.setTotalPrice( article.getPrice() );

		Order newOrder = new Order();

		newOrder.setOwner( customer );
		newOrder.setStatus( Order.Status.OPEN );
		newOrder.setTotal( article.getPrice() );
		newOrder.setItems( Collections.singletonList( item ) );

		return orderDao.insert( newOrder );
	}

	@Override
	public List<Article> getMenu() {
		return articleDao.loadAll();
	}

	@Override
	public List<Order> getActiveOrders() {
		OrderFilter filter = new OrderFilter();
		filter.status = Order.Status.OPEN;

		List<Order> orders = orderDao.find( filter );

		return orders;
	}
}
