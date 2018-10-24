package com.axelor.controller;


import java.util.List;

import com.axelor.pojo.Employee;
import com.axelor.pojo.GroupCircle;

public interface GroupCircleService {
	
	public void insert(GroupCircle g);
	public void update(GroupCircle groupCircle);
	public List<GroupCircle> getGroupCircle(int id);
	List<GroupCircle> all();
	public void delete3(int id);
	 public GroupCircle find(int id);
}
