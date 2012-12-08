package com.lunch4you.web.controller;

import java.util.List;
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
import com.lunch4you.domain.Referral;
import com.lunch4you.service.MenuService;
import com.lunch4you.web.dto.CustomerDto;
import com.lunch4you.web.dto.ReferralDto;

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

	@RequestMapping( value = "/customers/sendMenu.json", method = RequestMethod.GET )
	public @ResponseBody
	List<Map<String, Object>> sendMenu( ) {
		logger.trace( "CustomerController.sendMenu called" );

		List<Map<String,Object>> results = menuService.sendMenu();
		
				
		// replace each customer object in the result set with CustomerDTO object
		for(Map<String, Object> result : results){
			Customer customer = (Customer) result.get("customer");
//			CustomerDto customerDto = beanMapper.map( customer, CustomerDto.class );
			result.put("customer", customer.getFirstName() + " " + customer.getLastName() + " " + customer.getEmail());
		}
		
		return results;
	}

	@RequestMapping( value = "/customers", method = RequestMethod.POST )
	public @ResponseBody
	CustomerDto createNew( @RequestBody Map<String, Object> data ) {
		logger.trace( "CustomerController.createNew called with data: " + data );

		String firstName = data.get( "firstName" ).toString();
		String lastName = data.get( "lastName" ).toString();
		String email = data.get( "email" ).toString();
		long defaultDeliveryLocationId = Long.parseLong(data.get( "defaultDeliveryLocationId" ).toString());
		
		Customer customer = menuService.registerCustomer( firstName, lastName, email, defaultDeliveryLocationId, true );
		CustomerDto customerDto = beanMapper.map( customer, CustomerDto.class );

		return customerDto;
	}
	
	@RequestMapping( value = "/customers/updateCustomer.json", method = RequestMethod.POST )
	public @ResponseBody
	CustomerDto updateCurrent( @RequestBody CustomerDto customerDto ) {
		logger.trace( "CustomerController.updateCurrent called with data: " + customerDto );

		Customer customer = menuService.getCustomer( customerDto.id );
		beanMapper.map( customerDto, customer );
		customer = menuService.updateCustomer( customer );
		customerDto = beanMapper.map( customer, CustomerDto.class );
		
		return customerDto;
	}
	
	
	/**
	 * Updates only certain attributes related to user's profile
	 * @param data
	 * @return
	 */
	@RequestMapping( value = "/customers/updateProfile.json", method = RequestMethod.POST )
	public @ResponseBody
	CustomerDto updateProfile( @RequestBody Map<String, Object> data ) {
		logger.trace( "CustomerController.updateProfile called with data: " + data );
		
		Object cust = data.get( "customer" );
		Customer customerProfile = beanMapper.map(cust, Customer.class);
		long defaultDeliveryLocationId = Long.parseLong( data.get( "defaultDeliveryLocationId" ).toString() );
		
		Customer customer = menuService.updateCustomerProfile( customerProfile, defaultDeliveryLocationId );

		CustomerDto customerDto = beanMapper.map( customer, CustomerDto.class );
		
		return customerDto;
	}

	@RequestMapping( value = "/customers/createReferral.json", method = RequestMethod.POST )
	public @ResponseBody
	ReferralDto createReferral( @RequestBody Map<String, Object> data ) {
		logger.trace( "CustomerController.createReferral called" );
		
		// senderId: customer.id, deliveryLocationId: deliveryLocationId, recipientEmail : recipientEmail, referralMessage 
		long senderId = Long.parseLong(data.get( "senderId" ).toString());
		long deliveryLocationId = Long.parseLong(data.get( "deliveryLocationId" ).toString());
		String recipientEmail = data.get( "recipientEmail" ).toString();
		String referralMessage = data.get( "referralMessage" ).toString();

		Referral referral = menuService.createReferral(senderId, deliveryLocationId, recipientEmail, referralMessage);
		
		ReferralDto referralDto = beanMapper.map( referral, ReferralDto.class );

		return referralDto;
	}
}
