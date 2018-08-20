package com.pdomingo.model.role;

import com.pdomingo.model.person.Person;
import java.util.*;

public class Role{

	private Long roleId;
	private String role;
	private Set<Person> assignedPersons = new HashSet<Person>(0);


	public Role(){

	}

	public Role(String newRole){
		this.role = newRole;
	}

	public Long getRoleId(){
		return roleId;
	}

	public String getRole(){
		return role;
	}

	public Set<Person> getAssignedPersons(){
		return assignedPersons;
	}

	public void setroleId(Long newRoleId){
		this.roleId = newRoleId;
	}

	public void setRole(String newRole){
		this.role = newRole;
	}

	public void setAssignedPersons(Set<Person> newPersons){
		this.assignedPersons = newPersons;
	}


}
