package com.lunch4you.dao;


public interface ReadWriteDaoBase<ENTITY, ID, FILTER> extends ReadOnlyDaoBase<ENTITY, ID, FILTER> {

	ENTITY insert( ENTITY newEntity );

	ENTITY update( ENTITY entity );

	void delete( ENTITY entity );
}
