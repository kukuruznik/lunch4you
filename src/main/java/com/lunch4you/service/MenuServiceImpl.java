package com.lunch4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunch4you.dao.MealDao;
import com.lunch4you.domain.Meal;

@Service
public final class MenuServiceImpl implements MenuService {

	@Autowired
	private MealDao mealDao;

	@Override
	public List<Meal> getMenu() {
		return mealDao.loadAll();
	}
}
