package com.lunch4you.web.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunch4you.domain.Article;
import com.lunch4you.service.MenuService;
import com.lunch4you.web.dto.ArticleDto;
import com.lunch4you.web.dto.CategoryDto;
import com.lunch4you.web.dto.MenuGroupDto;

@Controller
@RequestMapping( "/articles" )
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger( ArticleController.class );

	@Autowired
	private MenuService menuService;

	@Autowired
	private Mapper beanMapper;

	@RequestMapping( value = "/{id}.json", method = RequestMethod.GET )
	public @ResponseBody
	ArticleDto findOne( @PathVariable Long id ) {
		logger.trace( "ArticleController.findOne called" );

		Article article = menuService.findArticleById( id );

		if ( article == null ) {
			return null;
		} else {
			ArticleDto articleDto = beanMapper.map( article, ArticleDto.class );
			return articleDto;
		}
	}

	@RequestMapping( value = "/find.json", method = RequestMethod.GET )
	public @ResponseBody
	List<ArticleDto> findAll() {
		logger.trace( "ArticleController.findAll called" );

		List<Article> menu = menuService.getMenu();
		List<ArticleDto> menuDto = new ArrayList<ArticleDto>( menu.size() );

		for ( Article meal : menu )
			menuDto.add( beanMapper.map( meal, ArticleDto.class ) );
		return menuDto;
	}
	
	@SuppressWarnings( "unchecked" )
	@RequestMapping( value = "/grouped.json", method = RequestMethod.GET )
	public @ResponseBody
	List<MenuGroupDto> getGroupedMenu() {
		logger.trace( "ArticleController.getGroupedMenu called" );
		
		List<Map<String, Object>> groupedMenu = menuService.getGroupedMenu();
		List<MenuGroupDto> groupedMenuDto = new ArrayList<MenuGroupDto>( groupedMenu.size() );

		for ( Map<String, Object> group : groupedMenu ) {
			MenuGroupDto menuGroupDto = new MenuGroupDto();

			menuGroupDto.category = beanMapper.map( group.get( "category" ), CategoryDto.class );
			menuGroupDto.articles = new LinkedList<ArticleDto>();

			for ( Article article : (List<Article>) group.get( "articles" ) )
				menuGroupDto.articles.add( beanMapper.map( article, ArticleDto.class ) );

			groupedMenuDto.add( menuGroupDto );
		}
		return groupedMenuDto;
	}
}
