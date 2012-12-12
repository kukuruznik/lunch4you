package com.lunch4you.dao.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lunch4you.dao.CustomerDao;
import com.lunch4you.dao.common.AbstractReadWriteDao;
import com.lunch4you.dao.filter.CustomerFilter;
import com.lunch4you.domain.Customer;

@Repository
public class JpaCustomerDao extends AbstractReadWriteDao<Customer, Long, CustomerFilter> implements CustomerDao {

	@Override
	protected Class<Customer> getEntityClass() {
		return Customer.class;
	}

	@Override
	protected CriteriaQuery<Customer> getCriteriaForFilter( CustomerFilter f ) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = builder.createQuery( Customer.class );
		Root<Customer> root = cq.from( Customer.class );
		cq.select( root );

		if ( f != null ) {
			Predicate p = builder.and(); // always true

			if ( f.isActive != null )
				p = builder.and( p, builder.equal( root.get( "isActive" ), f.isActive ) );

			if ( f.isSubscribedMenuWeekly != null )
				p = builder.and( p, builder.equal( root.get( "isSubscribedMenuWeekly" ), f.isSubscribedMenuWeekly ) );

			if ( f.isSubscribedNews != null )
				p = builder.and( p, builder.equal( root.get( "isSubscribedNews" ), f.isSubscribedNews ) );

			if ( f.token != null )
				p = builder.and( p, builder.equal( root.get( "token" ), f.token ) );

			if ( f.email != null )
				p = builder.and( p, builder.equal( root.get( "email" ), f.email ) );

			cq.where( p );
		}

		return cq;
	}

}
