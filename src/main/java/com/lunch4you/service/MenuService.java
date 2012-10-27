package com.lunch4you.service;

import java.util.List;
import java.util.Map;

import com.lunch4you.domain.Article;
import com.lunch4you.domain.ArticleWithOrders;
import com.lunch4you.domain.Category;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.DeliveryLocation;
import com.lunch4you.domain.DeliveryLocationWithArticles;
import com.lunch4you.domain.Order;

public interface MenuService {

	Article findArticleById( Long id );

	Customer getCustomer( Long id );

	Customer findCustomerByToken( String token );

	List<Customer> getAllCustomers();

	Customer registerCustomer( String firstName, String lastName, String email, Long defaultDeliveryLocationId  ); // we might need a RegistrationInfo class in the future 

	DeliveryLocation getDeliveryLocation( Long id );

	List<DeliveryLocation> getAllDeliveryLocations();

	Order createOrder(Long articleId, Long customerId, Long deliveryLocationId );

	List<Article> getMenu();

	List<Map<String, Object>> getGroupedMenu();

	List<Category> getCategories();

	List<Order> getActiveOrders();

	List<ArticleWithOrders> getActiveOrdersByArticle();

	List<DeliveryLocationWithArticles> getActiveOrdersByDeliveryLocation();
	
	List<Long> closeOrders( List<Long> ids );

}
