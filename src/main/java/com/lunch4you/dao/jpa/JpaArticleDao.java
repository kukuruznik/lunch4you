package com.lunch4you.dao.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Article> cq = cb.createQuery( Article.class );
		Root<Article> root = cq.from( Article.class );
		cq.select( root );
		cq.orderBy( cb.asc( root.get( "name_cz" ) ) );

		// TODO implement filtering
		return cq;
	}
}
