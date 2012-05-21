package com.lunch4you.dao.common;

import java.util.List;

public interface ReadOnlyDaoBase<ENTITY, ID, FILTER> {

	ENTITY load( ID id );

	List<ENTITY> loadAll();

	List<ENTITY> find( FILTER filter );
	
	List<ENTITY> find( FILTER filter, int page, int pageSize );
}
