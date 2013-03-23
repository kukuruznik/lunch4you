package com.lunch4you.web.dto;

public final class ArticleDto {

	public Long id;

	public Integer priceDelivery;

	public Integer priceRestaurant;

	public Integer dailyLimit;
	
	public boolean isActiveDelivery;
	
	public boolean isActiveRestaurantWeekly;

	public boolean isActiveRestaurantDaily;

	public boolean isNew;

	public boolean isSoldOut;

	public CategoryDto category;

	public String code;

	public String name_cz;
	
	public String name_en;
	
	public String description_cz;
	
	public String description_en;
}
