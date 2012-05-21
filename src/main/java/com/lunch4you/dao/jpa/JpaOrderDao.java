package com.lunch4you.dao.jpa;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lunch4you.dao.OrderDao;
import com.lunch4you.dao.common.AbstractReadWriteDao;
import com.lunch4you.dao.filter.OrderFilter;
import com.lunch4you.domain.Order;

@Repository
public class JpaOrderDao extends AbstractReadWriteDao<Order, Long, OrderFilter> implements OrderDao {

	@Override
	protected Class<Order> getEntityClass() {
		return Order.class;
	}

	@Override
	protected CriteriaQuery<Order> getCriteriaForFilter( OrderFilter f ) {
		CriteriaQuery<Order> cq = entityManager.getCriteriaBuilder().createQuery( Order.class );
		Root<Order> root = cq.from( Order.class );
		cq.select( root );

		// TODO implement filtering
		return cq;
	}
}
