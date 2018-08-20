package com.pdomingo.model.role;

import com.pdomingo.model.person.Person;
import java.util.*;

public class Role{

	private Long roleId;
	private String role;

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

	public void setroleId(Long newRoleId){
		this.roleId = newRoleId;
	}

	public void setRole(String newRole){
		this.role = newRole;
	}



}
