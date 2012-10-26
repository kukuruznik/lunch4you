package com.lunch4you.web.controller;

import java.security.Principal;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunch4you.dao.CustomerDao;
import com.lunch4you.domain.Customer;
import com.lunch4you.web.dto.CustomerDto;

@Controller
public class AccessController {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping(value = "/backoffice/loggedInUser", method = RequestMethod.GET)
	public @ResponseBody
	String getUser(Principal principal) {

		return principal.getName();
	}

	@RequestMapping(value = "/backoffice/currentCustomer", method = RequestMethod.GET)
	public @ResponseBody
	CustomerDto getCurrentCustomer(Principal principal) {
		try {
			Long customerId = Long.parseLong( principal.getName() );
			Customer customer = customerDao.load( customerId );
			CustomerDto customerDto = beanMapper.map( customer, CustomerDto.class );

			return customerDto;

		} catch ( NumberFormatException e ) {
			return null;
		}
	}
}
