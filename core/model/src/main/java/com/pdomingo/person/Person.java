package com.pdomingo.model.person;

import java.util.*;
import com.pdomingo.model.role.Role;

public class Person{

	 private Long personId;
	 private Name name;
	 private Address address;
	 private Date bDay;
	 private Double GWA;
	 private Date dateHired;
	 private Boolean currEmployed;
	 private Set<ContactInfo> contactInfo = new HashSet<ContactInfo>(0);
	 private Set<Role> roles = new HashSet<Role>(0);

	public Person(){

	}

	public Person(Name newName, Address newAddress, Date newBDay, Double newGWA,
					Date newDateHired,
					Boolean newCurrEmployed){

		this.name = newName;
		this.address = newAddress;
		this.bDay = newBDay;
		this.GWA = newGWA;
		this.dateHired = newDateHired;
		this.currEmployed = newCurrEmployed;

	}

	public Long getPersonId(){
		return personId;
	}

	public Name getName(){
		return name;
	}

	public Address getAddress(){
		return address;
	}

	public Date getbDay(){
		return bDay;
	}

	public Double getGWA(){
		return GWA;
	}

	public Date getDateHired() {
		return dateHired;
	}

	public Boolean getCurrEmployed(){
		return currEmployed;
	}

	public Set<ContactInfo> getContactInfo(){
		return contactInfo;
	}

	public Set<Role> getRoles(){
		return roles;
	}

	public void setPersonId(Long newID){
		this.personId = newID;
	}

	public void setName(Name newName){
		this.name = newName;
	}

	public void setAddress(Address newAddress){
		this.address = newAddress;
	}

	public void setbDay(Date newBDay){
		this.bDay = newBDay;
	}

	public void setGWA(Double newGWA){
		this.GWA = newGWA;
	}

	public void setDateHired(Date newDateHired){
		this.dateHired = newDateHired;
	}


	public void setCurrEmployed(Boolean newEmploymentStatus){
		this.currEmployed = newEmploymentStatus;
	}

	public void setContactInfo(Set<ContactInfo> newContactInfo){
		this.contactInfo = newContactInfo;
	}

	public void setRoles(Set<Role> newRoles){
		this.roles = newRoles;
	}
}
