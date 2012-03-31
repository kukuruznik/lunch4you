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

import com.lunch4you.domain.Meal;
import com.lunch4you.service.MenuService;
import com.lunch4you.web.dto.MealDto;

@Controller
@RequestMapping( "/meals" )
public class MealController {

	private static final Logger logger = LoggerFactory.getLogger( MealController.class );

	@Autowired
	private MenuService menuService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping( value = "/find.json", method = RequestMethod.GET )
	public @ResponseBody
	List<MealDto> findAll() {
		logger.trace( "MealController.findAll called" );

		List<Meal> menu = menuService.getMenu();
		List<MealDto> menuDto = new ArrayList<MealDto>( menu.size() );

		for ( Meal meal : menu )
			menuDto.add( beanMapper.map( meal, MealDto.class ) );
		return menuDto;
	}
}
