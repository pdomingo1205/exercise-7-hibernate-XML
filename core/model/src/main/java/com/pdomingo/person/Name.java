package com.pdomingo.model.person;

import java.util.*;

public class Name{

	private String firstName;
	private String lastName;
	private String middleName;
	private String suffix;
	private String title;


	public Name(){

	}

	public Name(String newFirstName, String newLastName, String newMiddleName,
				String newSuffix, String newTitle){

		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.middleName = newMiddleName;
		this.suffix = newSuffix;
		this.title = newTitle;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getMiddleName(){
		return middleName;
	}

	public String getSuffix(){
		return suffix;
	}

	public String getTitle(){
		return title;
	}

	public void setFirstName(String newFirstName){
		this.firstName = newFirstName;
	}

	public void setLastName(String newLastName){
		this.lastName = newLastName;
	}

	public void setMiddleName(String newMiddleName){
		this.middleName = newMiddleName;
	}

	public void setSuffix(String newSuffix){
		this.suffix = newSuffix;
	}

	public void setTitle(String newTitle){
		this.lastName = newTitle;
	}

	public String toString(){
		return title + firstName + middleName
			+ lastName + suffix;
	}




}
