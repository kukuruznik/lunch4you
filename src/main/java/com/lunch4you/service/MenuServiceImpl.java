package com.lunch4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunch4you.dao.ArticleDao;
import com.lunch4you.dao.CustomerDao;
import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;

@Service
public final class MenuServiceImpl implements MenuService {

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Article> getMenu() {
		return articleDao.loadAll();
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.loadAll();
	}
}
