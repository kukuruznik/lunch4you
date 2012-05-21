package com.lunch4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunch4you.dao.ArticleDao;
import com.lunch4you.dao.CustomerDao;
import com.lunch4you.dao.OrderDao;
import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.Order;

@Service
public final class MenuServiceImpl implements MenuService {

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public List<Article> getMenu() {
		return articleDao.loadAll();
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.loadAll();
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDao.loadAll();
	}
}
