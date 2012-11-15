package com.lunch4you.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lunch4you.dao.OrderItemDao;
import com.lunch4you.dao.common.AbstractReadWriteDao;
import com.lunch4you.dao.filter.OrderFilter;
import com.lunch4you.domain.OrderItem;

@Repository
public class JpaOrderItemDao extends AbstractReadWriteDao<OrderItem, Long, OrderFilter> implements OrderItemDao {

	@Override
	protected Class<OrderItem> getEntityClass() {
		return OrderItem.class;
	}

	@Override
	protected CriteriaQuery<OrderItem> getCriteriaForFilter( OrderFilter f ) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderItem> cq = builder.createQuery( OrderItem.class );
		Root<OrderItem> root = cq.from( OrderItem.class );
		cq.select( root );

		if ( f != null ) {
			Predicate p = builder.and(); // always true

//			if ( f.status != null )
//				p = builder.and( p, builder.equal( root.get("order").get( "status" ), f.status ) );

			cq.where( p );
		}

		List<Order> orderByList = new ArrayList<Order>();
		orderByList.add( builder.asc( root.get("order").get( "deliveryLocation" ).get("abbreviation") ) );
		orderByList.add( builder.asc( root.get("article").get( "category" ).get("sortOrder") ) );
		orderByList.add( builder.asc( root.get("article").get("name_cz") ) );
		orderByList.add( builder.asc( root.get("order").get( "owner" ).get("firstName") ) );
		cq.orderBy( orderByList );

		//cq.orderBy( builder.desc( root.get("order").get( "timestamp" ) ) );

		return cq;
	}
}
