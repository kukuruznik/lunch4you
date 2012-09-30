package com.lunch4you.dao;

import com.lunch4you.dao.common.ReadWriteDaoBase;
import com.lunch4you.dao.filter.CategoryFilter;
import com.lunch4you.domain.Category;


public interface CategoryDao extends ReadWriteDaoBase<Category, Long, CategoryFilter> {

}
