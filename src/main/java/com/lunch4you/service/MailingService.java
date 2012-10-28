package com.lunch4you.service;

import java.util.LinkedHashMap;

import com.lunch4you.domain.CategoryWithArticles;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.Order;


public interface MailingService {

	void sendMenu( Customer customer, LinkedHashMap<Long,CategoryWithArticles> categoriesWithArticles );

	void sendOrderConfirmation( Order order );
}
