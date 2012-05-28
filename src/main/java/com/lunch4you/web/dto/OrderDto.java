package com.lunch4you.web.dto;

import com.lunch4you.domain.Order;


public final class OrderDto {

	public Long id;

	public Integer total;

	public CustomerDto owner;

	public Order.Status status;

	// TODO: this is a temporary solution,
	// as for now we limit all orders to one item only
	public OrderItemDto onlyItem;
}
