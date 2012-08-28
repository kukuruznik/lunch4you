package com.lunch4you.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunch4you.dao.ArticleDao;
import com.lunch4you.dao.CustomerDao;
import com.lunch4you.dao.OrderDao;
import com.lunch4you.dao.filter.CustomerFilter;
import com.lunch4you.dao.filter.OrderFilter;
import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.Order;
import com.lunch4you.domain.OrderItem;

@Transactional
@Service
public final class MenuServiceImpl implements MenuService {

	private static final Logger logger = LoggerFactory.getLogger( MenuServiceImpl.class );

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private MailingService mailingService;

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
	public List<Customer> getAllCustomers() {
		return customerDao.loadAll();
	}

	@Override
	public Order createOrder( Long articleId, String token ) {
		OrderItem item = new OrderItem();
		Article article = articleDao.load( articleId );
		Customer customer = findCustomerByToken( token );

		item.setAmount( 1 );
		item.setArticle( article );

		Order newOrder = new Order();

		newOrder.setOwner( customer );
		newOrder.setStatus( Order.Status.OPEN );
		newOrder.setItems( Collections.singletonList( item ) );

		Order order = orderDao.insert( newOrder );

		try {
			mailingService.sendOrderConfirmation( order );
		} catch ( MailSendException e ) {
			logger.error( "Confirmation e-mail sending failed!", e );
		}

		return order;
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

	@Override
	public List<Long> closeOrders( List<Long> ids ) {
		OrderFilter filter = new OrderFilter();
		filter.status = Order.Status.OPEN;
		filter.ids = ids;
		List<Order> orders = orderDao.find( filter );

		for ( Order o : orders ) {
			ids.remove( o.getId() );
			o.setStatus( Order.Status.CLOSED );
		}
		return ids;
	}
}
