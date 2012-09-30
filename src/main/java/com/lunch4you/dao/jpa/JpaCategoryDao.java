package com.lunch4you.dao.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lunch4you.dao.CategoryDao;
import com.lunch4you.dao.common.AbstractReadWriteDao;
import com.lunch4you.dao.filter.CategoryFilter;
import com.lunch4you.domain.Category;

@Repository
public class JpaCategoryDao extends AbstractReadWriteDao<Category, Long, CategoryFilter> implements CategoryDao {

	@Override
	protected Class<Category> getEntityClass() {
		return Category.class;
	}

	@Override
	protected CriteriaQuery<Category> getCriteriaForFilter( CategoryFilter filter ) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> cq = cb.createQuery( Category.class );
		Root<Category> root = cq.from( Category.class );
		cq.select( root );
		cq.orderBy( cb.asc( root.get( "sortOrder" ) ), cb.asc( root.get( "name_cz" ) ) );

		// TODO implement filtering
		return cq;
	}
}
