package com.axelor.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.axelor.pojo.Contact;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class ContactServiceImpl implements ContactService {
	@Inject

	Provider<EntityManager> emp;
	
	@Override
	@Transactional
	public void insert(Contact c) {
		EntityManager em = emp.get();
		em.persist(c);
	}

	@Override
	@Transactional
	public void update(Contact contact) {
		EntityManager em = emp.get();
		
		em.merge(contact);
	}

	@Override
	@Transactional
	public List<Contact> all() {
		EntityManager em = emp.get();
		TypedQuery<Contact> query = em.createQuery("select c from Contact c", Contact.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public List<Contact> getContact(int id) {
		EntityManager em = emp.get();
		TypedQuery<Contact> query1 = em.createQuery("select c from Contact c where c.employee.id=" + id, Contact.class);
		return query1.getResultList();
	}
	 @Override
	 @Transactional
	 public void delete2(int id) {
		 EntityManager em = emp.get();
		 Contact s = em.find(Contact.class,id);
	        em.remove(s);
	}

	@Override
	@Transactional
	public Contact find(int id) {
		EntityManager em = emp.get();
		return em.find(Contact.class, id);
	}
}
