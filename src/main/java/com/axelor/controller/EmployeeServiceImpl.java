package com.axelor.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.axelor.pojo.Employee;
import com.axelor.pojo.GroupCircle;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class EmployeeServiceImpl implements EmployeeService {

	@Inject
	Provider<EntityManager> emp;

	@Override
	@Transactional
	public void insert(Employee e) {
		EntityManager em = emp.get();
		em.persist(e);
	}

	@Override
	@Transactional
	public void update(Employee employee) {
		EntityManager em = emp.get();
		
		em.merge(employee);
	
	}
	
	@Override
	@Transactional
	public List<Employee> all() {
		EntityManager em = emp.get();
		TypedQuery<Employee> query = em.createQuery("select e from Employee e", Employee.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Employee getEmployee(int id) {
		EntityManager em = emp.get();
		TypedQuery<Employee> query1 = em.createQuery("select e from Employee e where e.id=" + id, Employee.class);
		return query1.getSingleResult();
	}

	@Override
	@Transactional
	public void delete(int id) {
		EntityManager em = emp.get();
		Employee s = em.find(Employee.class, id);
		List<GroupCircle> grpList1 = s.getGroupcircle();
		s.setGroupcircle(null);
		for (GroupCircle grp : grpList1) {
			List<Employee> empList = grp.getEmployee();
			empList.remove(s);
		}
		em.remove(s);
	}

	@Override
	@Transactional
	public void deleteGroupCircle(int id) {
		EntityManager em = emp.get();
		Employee s = em.find(Employee.class, id);
		List<GroupCircle> grpList1 = s.getGroupcircle();
		s.setGroupcircle(null);
		
		for (GroupCircle grp : grpList1) {
			List<Employee> empList = grp.getEmployee();
			empList.remove(s);
			
		}
		
	}

	@Override
	@Transactional
	public Employee find(int id) {
		EntityManager em = emp.get();
		return em.find(Employee.class, id);
		

	}

}
