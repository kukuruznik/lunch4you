package com.lunch4you.service;

import java.util.List;

import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.Order;


public interface MailingService {

	void sendMenu( Customer customer, List<Article> menu );

	void sendOrderConfirmation( Order order );
}