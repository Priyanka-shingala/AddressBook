package com.axelor.pojo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

//@RequestScoped
@javax.persistence.Entity
public class Employee {

	/*
	 * @Inject
	 * 
	 * @RequestParameters private Map<String, String[]> parameters;
	 * 
	 * 
	 * @Inject private HttpSession session;
	 * 
	 * @Inject private ServletRequest request;
	 * 
	 * @Inject private ServletResponse response;
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String first_name;

	@Column
	private String last_name;

	@Column
	private String gender; 
	@OneToMany(mappedBy = "employee",cascade= CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Address> addressList;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Contact> contactList;

	@ManyToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<GroupCircle> groupcircle;
/*
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<GroupCircle> groupcircle;
	*/
	public Employee() {
	}

	public Employee(String first_name, String last_name) {
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public Employee(int id, String first_name, String last_name, String gender, List<Address> addressList,
			List<Contact> contactList, List<GroupCircle> groupcircle) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.addressList = addressList;
		this.contactList = contactList;
		this.groupcircle = groupcircle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<GroupCircle> getGroupcircle() {
		return groupcircle;
	}

	public void setGroupcircle(List<GroupCircle> groupcircle) {
		this.groupcircle = groupcircle;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	
}
