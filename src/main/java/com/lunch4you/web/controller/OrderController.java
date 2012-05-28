package com.lunch4you.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunch4you.domain.Article;
import com.lunch4you.domain.Customer;
import com.lunch4you.domain.Order;
import com.lunch4you.service.MenuService;
import com.lunch4you.web.dto.OrderDto;
import com.lunch4you.web.dto.OrderItemDto;

@Controller
@RequestMapping( "/orders" )
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger( OrderController.class );

	@Autowired
	private MenuService menuService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping( value = "", method = RequestMethod.POST )
	public @ResponseBody
	OrderDto createNew( @RequestBody Article article, Principal principal ) {
		logger.trace( "OrderController.createNew called" );

		Customer customer = menuService.findCustomerByToken( principal.getName() );

		Order order = menuService.createOrder( customer, article );
		OrderDto orderDto = beanMapper.map( order, OrderDto.class );

		return orderDto;
	}

	@RequestMapping( value = "/findActive.json", method = RequestMethod.GET )
	public @ResponseBody
	List<OrderDto> findActive() {
		logger.trace( "OrderController.findActive called" );
		
		List<Order> orders = menuService.getActiveOrders();
		List<OrderDto> orderDtos = new ArrayList<OrderDto>( orders.size() );
		
		for ( Order o : orders ) {
			OrderDto orderDto = beanMapper.map( o, OrderDto.class );
			orderDtos.add( orderDto );
			orderDto.onlyItem = beanMapper.map( o.getItems().get( 0 ), OrderItemDto.class );  // TODO: temporary solution only - remove later
		}
		return orderDtos;
	}
}
