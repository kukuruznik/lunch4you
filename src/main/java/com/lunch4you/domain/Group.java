package com.lunch4you.domain;

import java.util.ArrayList;
import java.util.List;

public class Group<ENTITY, ITEM> {
	public ENTITY entity;
	public List<ITEM> items = new ArrayList<ITEM>();
}
