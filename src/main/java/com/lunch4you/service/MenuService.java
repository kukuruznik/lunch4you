package com.lunch4you.service;

import java.util.List;

import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.Order;

public interface MenuService {

	Article findArticleById( Long id );

	Customer findCustomerByToken( String token );

	Order createOrder( Customer customer, Article article );

	List<Article> getMenu();

	List<Order> getActiveOrders();
}
