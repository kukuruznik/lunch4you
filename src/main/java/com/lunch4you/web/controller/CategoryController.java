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

import com.lunch4you.domain.Category;
import com.lunch4you.service.MenuService;
import com.lunch4you.web.dto.CategoryDto;

@Controller
@RequestMapping( "/categories" )
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger( ArticleController.class );

	@Autowired
	private MenuService menuService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping( value = "/find.json", method = RequestMethod.GET )
	public @ResponseBody
	List<CategoryDto> findAll() {
		logger.trace( "CategoryController.findAll called" );

		List<Category> categories = menuService.getCategories();
		List<CategoryDto> categoriesDto = new ArrayList<CategoryDto>( categories.size() );

		for ( Category category : categories )
			categoriesDto.add( beanMapper.map( category, CategoryDto.class ) );
		return categoriesDto;
	}
}
