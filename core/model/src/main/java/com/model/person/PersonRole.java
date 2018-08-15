package com.model.person;

import java.util.*;
import com.model.role.Roles;

public class PersonRole{

	private Long personRoleId;
	private Person person;
	private Role role;


	public PersonRole(){

	}

	public Long getPersonRoleId(){
		return personRoleId;
	}

	public person getPerson(){
		return person;
	}

	public Role getRole(){
		return role;
	}

	public void setPersonRolesId(String newPersonRoleId){
		roleId = newRoleId;
	}

	public void setPerson(String newPerson){
		person = newPerson;
	}

	public void setRole(Role newRole){
		role = newRole;
	}



}
