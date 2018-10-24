package com.axelor.controller;

import java.util.List;

import com.axelor.pojo.Contact;

public interface ContactService {
	
	public void insert(Contact c);
	void update(Contact contact);
	public List<Contact> getContact(int id);
	List<Contact> all();
	public void delete2(int id);
	 public Contact find(int id);
}
