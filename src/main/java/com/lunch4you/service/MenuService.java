package com.lunch4you.service;

import java.util.List;

import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.Order;

public interface MenuService {

	Article findArticleById( Long id );

	Customer getCustomer( Long id );

	Customer findCustomerByToken( String token );

	List<Customer> getAllCustomers();

	Customer registerCustomer( String firstName, String lastName, String email ); // we might need a RegistrationInfo class in the future 

	Order createOrder(Long articleId, Long customerId );

	List<Article> getMenu();

	List<Order> getActiveOrders();
	
	List<Long> closeOrders( List<Long> ids );
}
