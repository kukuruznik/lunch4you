package com.lunch4you.service;

import java.util.List;

import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;

public interface MenuService {

	List<Article> getMenu();

	List<Customer> getAllCustomers();
}
