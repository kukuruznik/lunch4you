package com.lunch4you.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunch4you.domain.Customer;
import com.lunch4you.service.MenuService;
import com.lunch4you.web.dto.CustomerDto;

@Controller
@RequestMapping( "/customers" )
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger( CustomerController.class );

	@Autowired
	private MenuService menuService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping( value = "/find.json", method = RequestMethod.GET )
	public @ResponseBody
	List<CustomerDto> findAll() {
		logger.trace( "CustomerController.findAll called" );

		List<Customer> customers = menuService.getAllCustomers();
		List<CustomerDto> menuDto = new ArrayList<CustomerDto>( customers.size() );

		for ( Customer customer : customers )
			menuDto.add( beanMapper.map( customer, CustomerDto.class ) );
		return menuDto;
	}
}
