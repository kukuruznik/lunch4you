package com.lunch4you.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunch4you.domain.DeliveryLocation;
import com.lunch4you.service.MenuService;
import com.lunch4you.web.dto.DeliveryLocationDto;

@Controller
@RequestMapping( "/deliveryLocations" )
public class DeliveryLocatioController {

	private static final Logger logger = LoggerFactory.getLogger( DeliveryLocatioController.class );

	@Autowired
	private MenuService menuService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping( value = "/{id}.json", method = RequestMethod.GET )
	public @ResponseBody
	DeliveryLocationDto findOne( @PathVariable Long id ) {
		logger.trace( "DeliveryLocationController.findOne called" );

		DeliveryLocation deliveryLocation = menuService.getDeliveryLocation( id );
		DeliveryLocationDto deliveryLocationDto = beanMapper.map( deliveryLocation, DeliveryLocationDto.class );
		return deliveryLocationDto;
	}

	@RequestMapping( value = "/find.json", method = RequestMethod.GET )
	public @ResponseBody
	List<DeliveryLocationDto> findAll() {
		logger.trace( "DeliveryLocationController.findAll called" );

		List<DeliveryLocation> locations = menuService.getAllDeliveryLocations();
		List<DeliveryLocationDto> locationsDto = new ArrayList<DeliveryLocationDto>( locations.size() );

		for ( DeliveryLocation location : locations )
			locationsDto.add( beanMapper.map( location, DeliveryLocationDto.class ) );
		return locationsDto;
	}
}
