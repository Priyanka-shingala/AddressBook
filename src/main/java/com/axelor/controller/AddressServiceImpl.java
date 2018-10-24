package com.axelor.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.axelor.pojo.Address;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class AddressServiceImpl implements AddressService {

	@Inject
	Provider<EntityManager> emp;

	@Override
	@Transactional
	public void insert(Address a) {
		EntityManager em = emp.get();
		em.persist(a);
	}

	@Override
	@Transactional
	public void update(Address address) {
	
		EntityManager em = emp.get();
		em.merge(address);
	}

	@Override
	@Transactional
	public List<Address> all() {
		EntityManager em = emp.get();
		TypedQuery<Address> query = em.createQuery("select a from Address a", Address.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public List<Address> getAddress(int id) {
		EntityManager em = emp.get();
		TypedQuery<Address> query1 = em.createQuery("select a from Address a where a.employee.id=" + id, Address.class);
		return query1.getResultList();
	}

	@Override
	@Transactional
	public void delete1(int id) {
		EntityManager em = emp.get();
		Address s = em.find(Address.class, id);
		em.remove(s);
	}

	@Override
	@Transactional
	public Address find(int id) {
		EntityManager em = emp.get();
		return em.find(Address.class, id);
	}
}