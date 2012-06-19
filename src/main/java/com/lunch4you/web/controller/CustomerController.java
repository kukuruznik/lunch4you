package com.lunch4you.web.controller;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping( value = "/byToken/{token}.json", method = RequestMethod.GET )
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
}
