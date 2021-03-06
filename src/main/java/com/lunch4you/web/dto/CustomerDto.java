package com.lunch4you.web.dto;


public class CustomerDto {

	public Long id;

	public String firstName;

	public String lastName;
	
	public String email;

	/**
	 *  This field is mapped / populated only in some cases, as it carries sensitive information
	 */
	public String token;

	public Integer credit;
	
	public boolean isActive;

	public DeliveryLocationDto defaultDeliveryLocation;

	public Boolean isSubscribedMenuWeekly = true;

	public Boolean isSubscribedNews = true;
}
