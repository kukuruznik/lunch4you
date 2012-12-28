package com.lunch4you.web.dto;

public final class ArticleDto {

	public Long id;

	public Integer price;
	
	public Integer dailyLimit;
	
	public boolean isActive;

	public boolean isNew;

	public boolean isSoldOut;

	public CategoryDto category;
	
	public String name_cz;
	
	public String name_en;
	
	public String description_cz;
	
	public String description_en;
}
