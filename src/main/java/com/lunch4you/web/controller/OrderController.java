package com.lunch4you.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	OrderDto createNew( @RequestBody Map<String, Object> data ) {
		logger.trace( "OrderController.createNew called with data: " + data );

		long articleId = Long.parseLong( data.get( "articleId" ).toString() );
		String token = data.get( "token" ).toString();

		Order order = menuService.createOrder( articleId, token );
		OrderDto orderDto = mapOrder( order );

		return orderDto;
	}

	@RequestMapping( value = "/active.json", method = RequestMethod.GET )
	public @ResponseBody
	List<OrderDto> findActive() {
		logger.trace( "OrderController.findActive called" );

		List<Order> orders = menuService.getActiveOrders();
		List<OrderDto> orderDtos = mapOrderList( orders );

		return orderDtos;
	}

	private OrderDto mapOrder( Order o ) {
		OrderDto orderDto = beanMapper.map( o, OrderDto.class );
		orderDto.onlyItem = beanMapper.map( o.getItems().get( 0 ), OrderItemDto.class );  // TODO: temporary solution only - remove later
		return orderDto;
	}

	private List<OrderDto> mapOrderList( List<Order> orders ) {
		List<OrderDto> orderDtos = new ArrayList<OrderDto>( orders.size() );
		
		for ( Order o : orders ) {
			OrderDto orderDto = mapOrder( o );
			orderDtos.add( orderDto );
		}
		return orderDtos;
	}
}
