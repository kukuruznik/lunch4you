package com.lunch4you.dao.jpa;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lunch4you.dao.ArticleDao;
import com.lunch4you.dao.ArticleFilter;
import com.lunch4you.domain.Article;

@Repository
public class JpaArticleDao extends AbstractReadWriteDao<Article, Long, ArticleFilter> implements ArticleDao {

	@Override
	protected Class<Article> getEntityClass() {
		return Article.class;
	}

	@Override
	protected CriteriaQuery<Article> getCriteriaForFilter( ArticleFilter f ) {
		CriteriaQuery<Article> cq = entityManager.getCriteriaBuilder().createQuery( Article.class );
		Root<Article> root = cq.from( Article.class );
		cq.select( root );

		// TODO implement filtering
		return cq;
	}
}
