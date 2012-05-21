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

import com.lunch4you.domain.Order;
import com.lunch4you.service.MenuService;
import com.lunch4you.web.dto.OrderDto;

@Controller
@RequestMapping( "/orders" )
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger( OrderController.class );

	@Autowired
	private MenuService menuService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping( value = "/find.json", method = RequestMethod.GET )
	public @ResponseBody
	List<OrderDto> findAll() {
		logger.trace( "OrderController.findAll called" );

		List<Order> orders = menuService.getAllOrders();
		List<OrderDto> orderDtos = new ArrayList<OrderDto>( orders.size() );

		for ( Order o : orders )
			orderDtos.add( beanMapper.map( o, OrderDto.class ) );
		return orderDtos;
	}
}
