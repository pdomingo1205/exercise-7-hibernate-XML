package com.model.person;

import java.util.*;
import com.model.role.Roles;

public class Person{

	 private Long personId;
	 private Name name;
	 private Address address;
	 private Date bDay;
	 private Double gwa;
	 private Date dateHired;
	 private Boolean currEmployed;
	 private Set<ContactInfo> contactInfo = new HashSet<ContactInfo>(0);
	 private Set<Role> roles = new HashSet<StockDailyRecord>(0);

	public Person(){

	}

	public Long getID(){
		return personId;
	}

	public Name getName(){
		return name;
	}

	public Address getAddress(){
		return address;
	}

	public Date get birthDay(){
		return bDay;
	}

	public Double getGWA(){
		return gwa;
	}

	public Date getDateHired(){
		return dateHired;
	}

	public Boolean isEmployed(){
		return currEmployed;
	}

	public List<String> getContact(){
		return contactInfo;
	}

	public List<String> getRoles(){
		return roles;
	}

	public void setID(Long newID){
		this.id = newID;
	}

	public void setName(Name newName){
		this.name = newName;
	}

	public void setAddress(Address newAddress){
		this.address = newAddress;
	}

	public void setBDay(Date newBDay){
		this.bday = newBDay;
	}

	public void setGWA(Double newGWA){
		this.gwa = newGWA;
	}

	public void setDateHired(Date newDateHired){
		this.dateHired = newDateHired;
	}

	public void setIsEmployed(Boolean newEmploymentStatus){
		this.isEmployed = newEmploymentStatus;
	}

	public void setContact(Set<ContactInfo> newContactInfo){
		this.contactInfo = newContactInfo;
	}

	public void setRoles(Set<Role> newRoles){
		this.roles = newRoles;
	}
}
