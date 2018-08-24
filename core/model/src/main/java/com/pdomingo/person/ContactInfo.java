package com.pdomingo.model.person;

import java.util.*;

public class ContactInfo implements java.io.Serializable{

	private Long contactInfoId;
	private String contactInfo;
	private String contactType;
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

	public String getContactType(){
		return contactType;
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

	public void setContactType(String newContactType){
		this.contactType = newContactType;
	}

	public void setPerson(Person newPerson){
		this.person = newPerson;
	}

	@Override
	public String toString(){
		return String.format("ID:%d Person:%s| %s: %s", contactInfoId, person.getName(), contactType, contactInfo);
	}

}
