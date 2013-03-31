package com.lunch4you.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lunch4you.domain.Article;
import com.lunch4you.domain.ArticleWithOrders;
import com.lunch4you.domain.Category;
import com.lunch4you.domain.CategoryWithArticles;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.DeliveryLocation;
import com.lunch4you.domain.DeliveryLocationWithArticles;
import com.lunch4you.domain.Order;
import com.lunch4you.domain.OrderItem;
import com.lunch4you.domain.OrderResult;
import com.lunch4you.domain.Referral;

public interface MenuService {

	Article findArticleById( Long id );

	Customer getCustomer( Long id );

	Customer findCustomerByToken( String token );

	List<Customer> getAllCustomers();

	Customer registerCustomer( String firstName, String lastName, String email, boolean wantsToReceiveMenu ); // we might need a RegistrationInfo class in the future 
	
	Customer updateCustomer( Customer customer ); // we might need a RegistrationInfo class in the future 

	DeliveryLocation getDeliveryLocation( Long id );

	List<DeliveryLocation> getAllDeliveryLocations();

	OrderResult createOrder(Long articleId, Long customerId, Long deliveryLocationId, String note );

	List<Article> getMenu();

	LinkedHashMap<Long, CategoryWithArticles> getArticlesByCategories(Boolean activeDelivery, Boolean activeRestaurantWeekly, Boolean activeRestaurantDaily);

	List<Category> getCategories();

	List<Order> getActiveOrders();

	List<OrderItem> getActiveOrderItems();

	LinkedHashMap<Long, ArticleWithOrders> getActiveOrdersByArticle();

	LinkedHashMap<Long, DeliveryLocationWithArticles> getActiveOrdersByDeliveryLocation();
	
	List<Long> closeOrders( List<Long> ids );

	void deleteOrders(List<Long> ids);

	void notifyDelivery(List<Long> ids);

	List<Customer> getSubscribedCustomers(Boolean menuWeekly, Boolean news);

	List<Map<String, Object>> sendMenu();

	List<Referral> createReferrals(long senderId, List<String> recipientEmails, String referralMessage);

	Customer updateCustomerProfile(Customer customerProfile,
			long defaultDeliveryLocationId);

	void setDefaultDeliveryLocation(long customerId, long deliveryLocationId);

	Article createOrUpdateArticle(Article article, long categoryId);

	Article setArticleFlag(Long articleId, String flagName, boolean value);

	Article setArticleLimit(Long articleId, Integer limit);



}
