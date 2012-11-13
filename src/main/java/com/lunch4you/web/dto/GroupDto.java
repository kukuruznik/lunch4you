package com.lunch4you.web.dto;

import java.util.ArrayList;
import java.util.List;

public class GroupDto<ENTITY, ITEM> {
	public ENTITY entity;
	public List<ITEM> items = new ArrayList<ITEM>();
	
	public int countOrderItems = 0;
	public int countOrders = 0;
	
}
