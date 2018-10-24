package com.axelor.config;

import com.axelor.controller.AddressService;
import com.axelor.controller.AddressServiceImpl;
import com.axelor.controller.ContactService;
import com.axelor.controller.ContactServiceImpl;
import com.axelor.controller.EmployeeResources;
import com.axelor.controller.EmployeeService;
import com.axelor.controller.EmployeeServiceImpl;
import com.axelor.controller.GroupCircleService;
import com.axelor.controller.GroupCircleServiceImpl;
import com.google.inject.AbstractModule;

public class ContactModule extends AbstractModule{

		@Override
		protected void configure() {
				bind(EmployeeResources.class);
				bind(EmployeeService.class).to(EmployeeServiceImpl.class);
				bind(AddressService.class).to(AddressServiceImpl.class);
				bind(ContactService.class).to(ContactServiceImpl.class);
				bind(GroupCircleService.class).to(GroupCircleServiceImpl.class);
		}
}
