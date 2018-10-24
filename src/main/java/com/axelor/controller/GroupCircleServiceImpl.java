package com.axelor.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.axelor.pojo.GroupCircle;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class GroupCircleServiceImpl implements GroupCircleService {
	@Inject
	// EntityManager em;
	Provider<EntityManager> emp;

	@Override
	@Transactional
	public void insert(GroupCircle g) {
		EntityManager em = emp.get();
		em.persist(g);
	}

	@Override
	@Transactional
	public void update(GroupCircle groupCircle) {
		EntityManager em = emp.get();
		em.merge(groupCircle);
		// em.persist(groupCircle);
	}

	@Override
	@Transactional
	public List<GroupCircle> all() {
		EntityManager em = emp.get();
		TypedQuery<GroupCircle> query = em.createQuery("select g from GroupCircle g", GroupCircle.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public List<GroupCircle> getGroupCircle(int id) {
		EntityManager em = emp.get();
		TypedQuery<GroupCircle> query1 = em.createQuery("select g from GroupCircle g where g.employee.id=" + id,
				GroupCircle.class);
		return query1.getResultList();
	}

	@Override
	@Transactional
	public void delete3(int id) {
		EntityManager em = emp.get();
		// GroupCircle s = em.find(GroupCircle.class,id);
		em.clear();
	}

	@Override
	@Transactional
	public GroupCircle find(int id) {
		EntityManager em = emp.get();
		return em.find(GroupCircle.class, id);
	}
}
