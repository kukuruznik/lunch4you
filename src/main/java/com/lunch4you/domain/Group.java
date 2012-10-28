package com.lunch4you.domain;

import java.util.LinkedHashMap;

public class Group<ENTITY, ITEM> {
	public ENTITY entity;
	public LinkedHashMap<Long, ITEM> items = new LinkedHashMap<Long, ITEM>();
}
