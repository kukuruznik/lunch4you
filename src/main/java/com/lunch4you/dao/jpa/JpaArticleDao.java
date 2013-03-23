package com.lunch4you.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lunch4you.dao.ArticleDao;
import com.lunch4you.dao.common.AbstractReadWriteDao;
import com.lunch4you.dao.filter.ArticleFilter;
import com.lunch4you.domain.Article;

@Repository
public class JpaArticleDao extends AbstractReadWriteDao<Article, Long, ArticleFilter> implements ArticleDao {

	@Override
	protected Class<Article> getEntityClass() {
		return Article.class;
	}

	@Override
	protected CriteriaQuery<Article> getCriteriaForFilter( ArticleFilter f ) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Article> cq = builder.createQuery( Article.class );
		Root<Article> root = cq.from( Article.class );
		cq.select( root );

		if ( f != null ) {
			Predicate p = builder.and(); // always true

			if ( f.isActiveDelivery != null )
				p = builder.and( p, builder.equal( root.get( "isActiveDelivery" ), f.isActiveDelivery ) );

			if ( f.isActiveRestaurant != null )
				p = builder.and( p, builder.equal( root.get( "isActiveRestaurantWeekly" ), f.isActiveRestaurant ) );

			cq.where( p );
		}
		
		List<Order> orderByList = new ArrayList<Order>();
		orderByList.add(builder.asc( root.get( "category" ).get("sortOrder") ));
		orderByList.add(builder.asc( root.get( "name_cz" ) ));
		cq.orderBy( orderByList );
		

		return cq;
	}
}
