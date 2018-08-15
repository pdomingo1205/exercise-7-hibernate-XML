package com.model.role;

import java.util.*;

public class Role{

	private Long roleId;
	private String role;


	public Role(){

	}

	public Long getRoleId(){
		return roleId;
	}

	public String getRole(){
		return role;
	}

	public void setroleId(Long newRoleId){
		roleId = newRoleId;
	}

	public void setRole(String newRole){
		role = newRole;
	}


}
