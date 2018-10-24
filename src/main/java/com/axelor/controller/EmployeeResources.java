package com.axelor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.axelor.pojo.GroupCircle;
import com.axelor.pojo.Address;
import com.axelor.pojo.Contact;
import com.axelor.pojo.Employee;
import com.google.common.base.Strings;
import com.google.inject.Inject;

@Path("/")
public class EmployeeResources {

	@Inject
	private EmployeeService employeeService;

	@Inject
	private AddressService addressService;

	@Inject
	private ContactService contactService;

	@Inject
	private GroupCircleService groupCircleService;

	@GET
	public void list(@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws IOException, Exception {

		List<Employee> employee1 = employeeService.all();
		request.setAttribute("employee", employee1);
		
		List<GroupCircle> groupC = groupCircleService.all();
		request.setAttribute("groupCircle", groupC);

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@POST
	@Path("/add")
	public void insert(@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws IOException, Exception {
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String gender = request.getParameter("gender");
		String[] group = request.getParameterValues("group");

		String[] title = request.getParameterValues("title");
		String[] home_no = request.getParameterValues("home_no");
		String[] street = request.getParameterValues("street");
		String[] city = request.getParameterValues("city");
		String[] state = request.getParameterValues("state");
		String[] pincode = request.getParameterValues("pincode");
		String[] phone = request.getParameterValues("contact");
		String[] contAddress = request.getParameterValues("contAddress");

		/*Employee employee = new Employee();
		employee.setFirst_name(first_name);
		employee.setLast_name(last_name);
		employee.setGender(gender);
		employeeService.insert(employee);*/
		
		Employee employee = new Employee();
		employee.setFirst_name(first_name);
		employee.setLast_name(last_name);
		employee.setGender(gender);
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);
	
		employeeService.insert(employee);
		
		List<GroupCircle> grpList = new ArrayList<>();
		for (String str : group) {
			GroupCircle gc = groupCircleService.find(Integer.parseInt(str));
			List<Employee> empList = gc.getEmployee();
			empList.add(employee);
			gc.setEmployee(empList);
			grpList.add(gc); 
			groupCircleService.update(gc);
		}
		
		employee.setGroupcircle(grpList);
		employeeService.update(employee);
		

		List<Address> addressList = new ArrayList<>();
		for (int i = 0; i < city.length; i++) {
			Address address = new Address();
			address.setTitle(title[i]);
			address.setHome_no(home_no[i]);
			address.setStreet(street[i]);
			address.setCity(city[i]);
			address.setState(state[i]);
			address.setPincode(pincode[i]);
			address.setEmployee(employee);
			addressList.add(address);
			employee.setAddressList(addressList);
			addressService.insert(address);
		}

		List<Contact> con = new ArrayList<>();
		for(int i = 0; i< phone.length; i++) {
			Contact contact = new Contact();
			contact.setContact(phone[i]);
			contact.setContAddress(contAddress[i]);
			contact.setEmployee(employee);
			con.add(contact);
			employee.setContactList(con);
			contactService.insert(contact);
		}

		/*List<GroupCircle> groupCircles = new ArrayList<>();
		for (String str : group) {
			GroupCircle gc = new GroupCircle();
			gc.setGroupName(str);
			gc.setEmployee(employee);
			groupCircles.add(gc);
			employee.setGroupcircle(groupCircles);
			groupCircleService.insert(gc);
		}*/

		response.sendRedirect(request.getContextPath());
	}

	@PUT
	@Path("/add")
	public void insert1(Employee employee) throws IOException, Exception {
		employeeService.insert(employee);
	}

	@GET
	@Path("/create")
	public void create(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws IOException, Exception {

		req.getRequestDispatcher("/form.jsp").forward(req, res);
	}

	@GET
	@Path("/edit/{id}")
	public void edit(@PathParam("id") int id, @Context HttpServletRequest req, @Context HttpServletResponse res)
			throws IOException, Exception {

		Employee employee = employeeService.getEmployee(id);
		req.setAttribute("employee", employee);

		List<Address> address = addressService.getAddress(id);
		req.setAttribute("address", address);

		List<Contact> contact = contactService.getContact(id);
		req.setAttribute("contact", contact);

		
		
		List<GroupCircle> groupC = groupCircleService.all();
		req.setAttribute("allGroupCircle", groupC);

		List<String> groupC1 = new ArrayList<>();
		for (GroupCircle groupCircle : employee.getGroupcircle()) {
			groupC1.add(groupCircle.getGroupName());
		}
		
		req.setAttribute("groupCircle", groupC1 );

		req.getRequestDispatcher("/update.jsp").forward(req, res);
	}

	@POST
	@Produces("application/json")
	@Path("/update/{id}")
	public void update(@PathParam("id") int id, @Context HttpServletRequest request,

			@Context HttpServletResponse response) throws IOException, Exception {

		System.out.println("-------------------------");
		String[] group = request.getParameterValues("group");

		Employee employee = employeeService.getEmployee(id);

		employee.setFirst_name(request.getParameter("first_name"));
		employee.setLast_name(request.getParameter("last_name"));
		employee.setGender(request.getParameter("gender"));

		String[] addressId = request.getParameterValues("addressId");
		String[] title = request.getParameterValues("title");
		String[] home_no = request.getParameterValues("home_no");
		String[] street = request.getParameterValues("street");
		String[] city = request.getParameterValues("city");
		String[] state = request.getParameterValues("state");
		String[] pincode = request.getParameterValues("pincode");
		String[] contactId = request.getParameterValues("contactId");
		String[] phone = request.getParameterValues("phone");
		String[] contAddress = request.getParameterValues("contAddress");
		
		employeeService.update(employee);

		List<Address> addressList = employee.getAddressList();
		for (int i = 0; i < city.length; i++) {
			Address address = addressService.find(Integer.parseInt(addressId[i]));
			address.setTitle(title[i]);
			address.setHome_no(home_no[i]);
			address.setStreet(street[i]);
			address.setCity(city[i]);
			address.setState(state[i]);
			address.setPincode(pincode[i]);
			address.setEmployee(employee);
			addressList.add(address);
			employee.setAddressList(addressList);
			addressService.update(address);
		}
		employee.setAddressList(addressList);

		employeeService.deleteGroupCircle(id);
		
		 employeeService.deleteGroupCircle(id);
			
			List<GroupCircle> grpList = new ArrayList<>();
			for (String str : group) {
				GroupCircle gc = groupCircleService.find(Integer.parseInt(str));
				List<Employee> empList = gc.getEmployee();
				empList.add(employee);
				gc.setEmployee(empList);
				grpList.add(gc); 
				groupCircleService.update(gc);
			}
			employee.setGroupcircle(grpList);
			employeeService.update(employee);
/*
		List<GroupCircle> groupCircles = new ArrayList<>();
		for (String str : group) {
			GroupCircle gc = new GroupCircle();
			gc.setGroupName(str);
			gc.setEmployee(employee);
			groupCircles.add(gc);
			employee.setGroupcircle(groupCircles);
			groupCircleService.insert(gc);
		}
		*/
		

		List<Contact> contactList = employee.getContactList();
		for (int i = 0; i < phone.length; i++) {
			Contact contact = contactService.find(Integer.parseInt(contactId[i]));
			contact.setContact(phone[i]);
			contact.setContAddress(contAddress[i]);
			contact.setEmployee(employee);
			contactList.add(contact);
			employee.setContactList(contactList);
			contactService.update(contact);
		}
		employee.setContactList(contactList);

		response.sendRedirect(request.getContextPath());
	}

	@POST
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public void findEmployee(@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws IOException, Exception {

		if (!Strings.isNullOrEmpty(request.getParameter("id"))) {

			int id = Integer.parseInt(request.getParameter("id"));

			Employee emp = employeeService.find(id);
			List<Employee> employee = new ArrayList<>();
			employee.add(emp);
			request.setAttribute("employee", employee);

			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {

			response.sendRedirect(request.getContextPath());
		}
	}

	@GET
	@Path("/contact")
	public void contact(@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws IOException, Exception {

		request.getRequestDispatcher("/form.jsp").include(request, response);

	}

	@GET
	@Path("/delete/{id}")
	public void deleteEmployee(@PathParam("id") int id, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException, Exception {

		employeeService.delete(id);
		response.sendRedirect(request.getContextPath());

	}

	@DELETE
	@Path("/delete/{id}")
	public void deleteEmployee1(@PathParam("id") int id, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws IOException, Exception {

		employeeService.delete(id);
		response.sendRedirect(request.getContextPath());

	}
}