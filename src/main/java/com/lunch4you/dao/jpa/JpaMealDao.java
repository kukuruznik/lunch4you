package com.lunch4you.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lunch4you.dao.MealDao;
import com.lunch4you.dao.MealFilter;
import com.lunch4you.domain.Meal;

@Repository
public class JpaMealDao implements MealDao {

	@PersistenceContext
	transient EntityManager entityManager;

	@Override
	public Meal load( Long id ) {
		return entityManager.find( Meal.class, id );
	}

	@Override
	public List<Meal> loadAll() {
		return entityManager.createQuery( "SELECT m FROM Meal m", Meal.class ).getResultList();
	}

	@Override
	public List<Meal> find( MealFilter filter ) {
		// TODO implement filtering!
		return loadAll();
	}

	@Override
	public List<Meal> find( MealFilter filter, int page, int pageSize ) {
		// TODO implement filtering!
		return entityManager.createQuery( "SELECT m FROM Meal m", Meal.class ).setFirstResult( page * pageSize )
				.setMaxResults( pageSize ).getResultList();
	}

	@Override
	public Meal insert( Meal newEntity ) {
		entityManager.persist( newEntity );
		return newEntity;
	}

	@Override
	public Meal update( Meal entity ) {
		return entityManager.merge( entity );
	}

	@Override
	public void delete( Meal entity ) {
		entityManager.remove( entity );
	}
}
