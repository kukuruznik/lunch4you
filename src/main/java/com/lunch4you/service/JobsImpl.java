package com.lunch4you.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;

public class JobsImpl implements Jobs {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MailingService mailingService;

	@Override
	public void emailMenu() {

		List<Map<String,Object>> groupedMenu = menuService.getGroupedMenu();

		List<Customer> allCustomers = menuService.getAllCustomers();
		List<Article> menu = menuService.getMenu();

		for ( Customer customer : allCustomers ) {
			mailingService.sendMenu( customer, menu, groupedMenu );
		}
	}
}
