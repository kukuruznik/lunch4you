package com.lunch4you.dao;

import com.lunch4you.dao.common.ReadWriteDaoBase;
import com.lunch4you.dao.filter.ArticleFilter;
import com.lunch4you.domain.Article;

public interface ArticleDao extends ReadWriteDaoBase<Article, Long, ArticleFilter> {
}
