package com.axelor.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String title;

	@Column
	private String home_no;

	@Column
	private String street;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String pincode;

	@ManyToOne
	// @JoinColumn(name ="id")
	private Employee employee;

	public Address() {
	}

	public Address(int id, String title, String home_no, String street, String city, String state, String pincode,
			Employee employee) {
		this.id = id;
		this.title = title;
		this.home_no = home_no;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.employee = employee;
	}

	/*
	 * public static Address updateAddress(Address address, Map<String, Object>
	 * addrValues) { if(addrValues!=null) { for(Object obj : addrValues.keySet()) {
	 * if(obj.equals("home_no")) address.setHome_no(addrValues.get(obj).toString());
	 * else if(obj.equals("street"))
	 * address.setStreet(addrValues.get(obj).toString()); else
	 * if(obj.equals("title")) address.setTitle(addrValues.get(obj).toString());
	 * else if(obj.equals("city")) address.setCity(addrValues.get(obj).toString());
	 * else if(obj.equals("state"))
	 * address.setState(addrValues.get(obj).toString()); else
	 * if(obj.equals("pincode")) address.setPincode(addrValues.get(obj).toString());
	 * } } return address; }
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHome_no() {
		return home_no;
	}

	public void setHome_no(String home_no) {
		this.home_no = home_no;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
