package com.model.person;

import java.util.*;

public class ContactInfo{

	private Long contactInfoId;
	private String contactInfo;
	private Person person;


	public ContactInfo(){

	}

	public Long getContactInfoId(){
		return contactInfoId;
	}

	public String getContactInfo(){
		return contactInfo;
	}

	public Person getPerson(){
		return person;
	}

	public void setContactInfoId(Long newContactInfoId){
		this.contactInfoId = newContactInfoId;
	}

	public void setRole(String newContactInfo){
		this.contactInfo = newContactInfo;
	}

	public void setPerson(Person newPerson){
		this.person = newPerson;
	}


}
