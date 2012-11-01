package com.lunch4you.web.dto;

import java.util.Date;

import com.lunch4you.domain.Order;


public final class OrderDto {

	public Long id;

	public CustomerDto owner;

	public DeliveryLocationDto deliveryLocation;

	public Order.Status status;

	public Date timestamp;

	public String timeString;

	// TODO: this is a temporary solution,
	// as for now we limit all orders to one item only
	public OrderItemDto onlyItem;
}
