package com.lunch4you.dao.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> cq = builder.createQuery( Order.class );
		Root<Order> root = cq.from( Order.class );
		cq.select( root );

		if ( f != null ) {
			Predicate p = builder.and(); // always true

			if ( f.status != null )
				p = builder.and( p, builder.equal( root.get( "status" ), f.status ) );

			if ( f.ids != null )
				p = builder.and( p, root.get( "id" ).in( f.ids ) );

			cq.where( p );
		}

		return cq;
	}
}
