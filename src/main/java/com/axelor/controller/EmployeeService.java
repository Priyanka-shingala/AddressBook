package com.axelor.controller;

import java.util.List;

import com.axelor.pojo.Employee;

public interface EmployeeService {

	
	public void insert(Employee e);
	void update(Employee employee);
	public Employee getEmployee(int id);
	List<Employee> all();
	 public void delete(int id);
	 public Employee find(int id);
	 public void deleteGroupCircle(int id);
}
