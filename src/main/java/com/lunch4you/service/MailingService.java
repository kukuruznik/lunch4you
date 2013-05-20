package com.lunch4you.service;

import java.util.LinkedHashMap;

import com.lunch4you.auth.AuthHelper.SignInRequest;
import com.lunch4you.domain.CategoryWithArticles;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.Order;
import com.lunch4you.domain.Referral;


public interface MailingService {

	void sendMenu( Customer customer, LinkedHashMap<Long,CategoryWithArticles> categoriesWithArticles );

	void sendOrderConfirmation( Order order );
	
	void sendDeliveryNotification(Order order);

	void sendReferral(Referral referral);

	void sendRegistration(Customer newCustomer, SignInRequest req);

	void sendSignIn(Customer customer, SignInRequest req);
}
