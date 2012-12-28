package com.lunch4you.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lunch4you.domain.CategoryWithArticles;
import com.lunch4you.domain.Customer;

public class JobsImpl implements Jobs {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MailingService mailingService;

	@Override
	public void emailMenu() {

		LinkedHashMap<Long,CategoryWithArticles> groupedMenu = menuService.getArticlesByCategories( true );

		List<Customer> customers = menuService.getSubscribedCustomers(true, false);

		for ( Customer customer : customers ) {
			mailingService.sendMenu( customer, groupedMenu );
		}
	}
}
