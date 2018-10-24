package com.axelor.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class GroupCircle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String groupName;
	
	 @ManyToMany(cascade = CascadeType.PERSIST) // @JoinColumn(name ="employee_id")
	 @LazyCollection(LazyCollectionOption.FALSE)
	  private List<Employee> employee;
	 
	/*@ManyToOne
	// @JoinColumn(name ="id")
	private Employee employee;*/

	public GroupCircle() {
	}

	public GroupCircle(int id, String groupName, List<Employee> employee) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String group) {
		this.groupName = group;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	

}
