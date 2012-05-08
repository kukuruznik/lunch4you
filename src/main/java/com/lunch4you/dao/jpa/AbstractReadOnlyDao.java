package com.lunch4you.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import com.lunch4you.dao.ReadOnlyDaoBase;

public abstract class AbstractReadOnlyDao<ENTITY, ID, FILTER> implements ReadOnlyDaoBase<ENTITY, ID, FILTER> {

	@PersistenceContext
	protected transient EntityManager entityManager;

	protected abstract Class<ENTITY> getEntityClass();

	protected abstract CriteriaQuery<ENTITY> getCriteriaForFilter( FILTER f );

	@Override
	public ENTITY load( ID id ) {
		return entityManager.find( getEntityClass(), id );
	}

	@Override
	public List<ENTITY> loadAll() {
		return entityManager.createQuery( getCriteriaForFilter( null ) ).getResultList();
	}

	@Override
	public List<ENTITY> find( FILTER filter ) {
		return entityManager.createQuery( getCriteriaForFilter( filter ) ).getResultList();
	}

	@Override
	public List<ENTITY> find( FILTER filter, int page, int pageSize ) {
		return entityManager.createQuery( getCriteriaForFilter( filter ) ).setFirstResult( page * pageSize )
				.setMaxResults( pageSize ).getResultList();
	}
}
