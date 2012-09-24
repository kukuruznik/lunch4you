package com.lunch4you.dao.jpa;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lunch4you.dao.DeliveryLocationDao;
import com.lunch4you.dao.common.AbstractReadWriteDao;
import com.lunch4you.dao.filter.DeliveryLocationFilter;
import com.lunch4you.domain.DeliveryLocation;

@Repository
public class JpaDeliveryLocationDao extends AbstractReadWriteDao<DeliveryLocation, Long, DeliveryLocationFilter> implements DeliveryLocationDao {

	@Override
	protected Class<DeliveryLocation> getEntityClass() {
		return DeliveryLocation.class;
	}

	@Override
	protected CriteriaQuery<DeliveryLocation> getCriteriaForFilter( DeliveryLocationFilter f ) {
		CriteriaQuery<DeliveryLocation> cq = entityManager.getCriteriaBuilder().createQuery( DeliveryLocation.class );
		Root<DeliveryLocation> root = cq.from( DeliveryLocation.class );
		cq.select( root );

		// TODO implement filtering
		return cq;
	}
}
