package com.axelor.controller;

import java.util.List;
import com.axelor.pojo.Address;

public interface AddressService {

		public void insert(Address a);
		void update(Address address);
		public List<Address> getAddress(int id);
		List<Address> all();
		public void delete1(int id);
		 public Address find(int id);
	}


