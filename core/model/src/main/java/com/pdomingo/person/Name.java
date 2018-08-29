package com.pdomingo.model.person;

import java.util.*;
import org.apache.commons.lang3.StringUtils;

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

	public String getTitle(){
		return title;
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
		this.title = newTitle;
	}

	@Override
	public String toString(){
		return String.format("%s %s %s %s %s", StringUtils.defaultIfBlank(this.title, "\t"),
							 StringUtils.defaultIfBlank(this.firstName, "\t"), StringUtils.defaultIfBlank(this.middleName, "\t"), StringUtils.defaultIfBlank(this.lastName, "\t"), StringUtils.defaultIfBlank(this.suffix, "\t"));
	}




}
