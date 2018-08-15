package com.model.person;

import java.util.*;

public class Name{

	private Long nameId;
	private Person person;
	private String firstName;
	private String lastName;
	private String middleName;
	private String suffix;
	private String title;


	public Name(){

	}

	public Long getNameId(){
		return nameId;
	}

	public Person getPerson(){
		return person;
	}

	public String getFirstName(){
		return barangay;
	}

	public String getLastName(){
		return barangay;
	}

	public String getMidName(){
		return barangay;
	}

	public String getSuffix(){
		return municipality;
	}

	public String getZipCode(){
		return ZipCode;
	}

	public void setNameId(Long newNameId){
		this.nameId = newNameId;
	}

	public void setPerson(Person newPerson){
		this.person = newPerson;
	}

	public void setFirstName(String newFirstName){
		this.firstName = newFirstName;
	}

	public void setLastName(String newLastName){
		this.lastName = newLastName;
	}

	public void setMiddleName(String newMiddleName){
		this.middlName = newLastName;
	}

	public void setSuffix(String newSuffix){
		this.suffix = newSuffix;
	}

	public void setTitle(String newTitle){
		this.lastName = newTitle;
	}




}
