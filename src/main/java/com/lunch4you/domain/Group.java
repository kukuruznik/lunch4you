package com.lunch4you.domain;

import java.util.LinkedHashMap;

public class Group<ENTITY, ITEM> {
	public ENTITY entity;
	public LinkedHashMap<Long, ITEM> items = new LinkedHashMap<Long, ITEM>();
	public int countOrderItems = 0;
	public int countOrders = 0;
	
	

	public ENTITY getEntity() {
		return entity;
	}
	public LinkedHashMap<Long, ITEM> getItems() {
		return items;
	}

}
