package com.lunch4you.dao.jpa;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lunch4you.dao.CustomerDao;
import com.lunch4you.dao.CustomerFilter;
import com.lunch4you.domain.Customer;

@Repository
public class JpaCustomerDao extends AbstractReadWriteDao<Customer, Long, CustomerFilter> implements CustomerDao {

	@Override
	protected Class<Customer> getEntityClass() {
		return Customer.class;
	}

	@Override
	protected CriteriaQuery<Customer> getCriteriaForFilter( CustomerFilter f ) {
		CriteriaQuery<Customer> cq = entityManager.getCriteriaBuilder().createQuery( Customer.class );
		Root<Customer> root = cq.from( Customer.class );
		cq.select( root );

		// TODO implement filtering
		return cq;
	}

}
