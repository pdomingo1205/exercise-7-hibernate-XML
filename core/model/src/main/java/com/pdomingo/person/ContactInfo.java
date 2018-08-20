package com.pdomingo.model.person;

import java.util.*;

public class ContactInfo implements java.io.Serializable{

	private Long contactInfoId;
	private String contactInfo;
	private Person person;


	public ContactInfo(){
	}

	public ContactInfo(String newContactInfo, Person newPerson){
		this.contactInfo = newContactInfo;
		this.person = newPerson;
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

	public void setContactInfo(String newContactInfo){
		this.contactInfo = newContactInfo;
	}

	public void setPerson(Person newPerson){
		this.person = newPerson;
	}


}
