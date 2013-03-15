package com.lunch4you.web.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunch4you.domain.Article;
import com.lunch4you.domain.CategoryWithArticles;
import com.lunch4you.service.MenuService;
import com.lunch4you.web.dto.ArticleDto;
import com.lunch4you.web.dto.CategoryDto;
import com.lunch4you.web.dto.CategoryWithArticlesDto;

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
	
	@RequestMapping( value = "/groupedByCategory.json", method = RequestMethod.GET )
	public @ResponseBody
	List<CategoryWithArticlesDto> getArticlesGroupedByCategory(@RequestParam Map<String,String> params) {
		logger.trace( "ArticleController.getArticlesGroupedByCategory called" );
		
		String parActiveRestaurant = params.get("activeRestaurant");
		Boolean activeRestaurant = parActiveRestaurant.equals("null") ? null : Boolean.parseBoolean(parActiveRestaurant);

		String parActiveDelivery = params.get("activeDelivery");
		Boolean activeDelivery = parActiveDelivery.equals("null") ? null : Boolean.parseBoolean(parActiveDelivery);
		
		LinkedHashMap<Long,CategoryWithArticles> categoriesWithArticles = menuService.getArticlesByCategories( activeDelivery, activeRestaurant );
		List<CategoryWithArticlesDto> categoriesWithArticlesDtos = new ArrayList<CategoryWithArticlesDto>( );

		for ( CategoryWithArticles categoryWithArticles : categoriesWithArticles.values() ) {
			CategoryWithArticlesDto categoryWithArticlesDto = new CategoryWithArticlesDto();

			categoryWithArticlesDto.entity = beanMapper.map( categoryWithArticles.entity, CategoryDto.class );

			for ( Article article : categoryWithArticles.items.values() )
				categoryWithArticlesDto.items.add( beanMapper.map( article, ArticleDto.class ) );

			categoriesWithArticlesDtos.add( categoryWithArticlesDto );
		}
		return categoriesWithArticlesDtos;
	}

	/**
	 * Updates only certain attributes of the article
	 * @param data
	 * @return
	 */
	@RequestMapping( value = "/createOrUpdateArticle.json", method = RequestMethod.POST )
	public @ResponseBody
	ArticleDto createOrUpdateArticle( @RequestBody Map<String, Object> data ) {
		
		Object art = data.get( "article" );
		Article article = beanMapper.map(art, Article.class);
		long categoryId = Long.parseLong( data.get( "categoryId" ).toString() );
		
		Article updatedArticle = menuService.createOrUpdateArticle( article, categoryId );

		ArticleDto articleDto = beanMapper.map( updatedArticle, ArticleDto.class );
		
		return articleDto;
	}
	
	@RequestMapping( value = "/setActive.json", method = RequestMethod.GET )
	public @ResponseBody
	ArticleDto setActive(@RequestParam Long articleId, @RequestParam boolean active, @RequestParam String service) {
		
		Article updatedArticle = menuService.setArticleActive( articleId, active, service );

		ArticleDto articleDto = beanMapper.map( updatedArticle, ArticleDto.class );
		
		return articleDto;
	}

	@RequestMapping( value = "/setLimit.json", method = RequestMethod.GET )
	public @ResponseBody
	ArticleDto setActive(@RequestParam Long articleId, @RequestParam Integer limit) {
		
		Article updatedArticle = menuService.setArticleLimit( articleId, limit );

		ArticleDto articleDto = beanMapper.map( updatedArticle, ArticleDto.class );
		
		return articleDto;
	}
}
