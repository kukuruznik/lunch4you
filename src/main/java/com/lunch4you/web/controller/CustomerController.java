package com.lunch4you.web.controller;

import java.util.Map;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunch4you.domain.Customer;
import com.lunch4you.service.MenuService;
import com.lunch4you.web.dto.CustomerDto;

@Controller
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger( CustomerController.class );

	@Autowired
	private MenuService menuService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping( value = "/backoffice/customers/{id}.json", method = RequestMethod.GET )
	public @ResponseBody
	CustomerDto findOne( @PathVariable Long id ) {
		logger.trace( "CustomerController.findOne called" );

		Customer customer = menuService.getCustomer( id );
		CustomerDto customerDto = beanMapper.map( customer, CustomerDto.class );
		return customerDto;
	}

	@RequestMapping( value = "/customers/byToken/{token}.json", method = RequestMethod.GET )
	public @ResponseBody
	CustomerDto findByToken( @PathVariable String token ) {
		logger.trace( "CustomerController.findByToken called" );

		Customer customer = menuService.findCustomerByToken( token );

		if ( customer == null ) {
			return null;
		} else {
			CustomerDto customerDto = beanMapper.map( customer, CustomerDto.class );
	
			return customerDto;
		}
	}

	@RequestMapping( value = "/customers", method = RequestMethod.POST )
	public @ResponseBody
	CustomerDto createNew( @RequestBody Map<String, Object> data ) {
		logger.trace( "CustomerController.createNew called with data: " + data );

		String firstName = data.get( "firstName" ).toString();
		String lastName = data.get( "lastName" ).toString();
		String email = data.get( "email" ).toString();
		
		// TODO - upravit na precitanie z requestu
		//long defaultDeliveryLocationId = Long.parseLong(data.get( "defaultDeliveryLocationId" ).toString());
		long defaultDeliveryLocationId = 2;
		
		Customer customer = menuService.registerCustomer( firstName, lastName, email, defaultDeliveryLocationId );
		CustomerDto customerDto = beanMapper.map( customer, CustomerDto.class );

		return customerDto;
	}
}
