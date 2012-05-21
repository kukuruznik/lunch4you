package com.lunch4you.dao.common;


public abstract class AbstractReadWriteDao<ENTITY, ID, FILTER> extends AbstractReadOnlyDao<ENTITY, ID, FILTER>
		implements ReadWriteDaoBase<ENTITY, ID, FILTER> {

	@Override
	public ENTITY insert( ENTITY newEntity ) {
		entityManager.persist( newEntity );
		return newEntity;
	}

	@Override
	public ENTITY update( ENTITY entity ) {
		return entityManager.merge( entity );
	}

	@Override
	public void delete( ENTITY entity ) {
		entityManager.remove( entity );
	}
}
