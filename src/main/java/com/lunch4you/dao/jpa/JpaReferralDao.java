package com.lunch4you.dao.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lunch4you.dao.ReferralDao;
import com.lunch4you.dao.common.AbstractReadWriteDao;
import com.lunch4you.dao.filter.ReferralFilter;
import com.lunch4you.domain.Referral;

@Repository
public class JpaReferralDao extends AbstractReadWriteDao<Referral, Long, ReferralFilter> implements ReferralDao {

	@Override
	protected Class<Referral> getEntityClass() {
		return Referral.class;
	}

	@Override
	protected CriteriaQuery<Referral> getCriteriaForFilter( ReferralFilter f ) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Referral> cq = builder.createQuery( Referral.class );
		Root<Referral> root = cq.from( Referral.class );
		cq.select( root );

		return cq;
	}
}
