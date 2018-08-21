package com.pdomingo.app;

import java.io.IOException;
import java.util.*;
import java.text.*;

import org.apache.commons.lang3.StringUtils;
import com.pdomingo.model.role.*;
import com.pdomingo.model.person.*;
import com.pdomingo.service.*;

import static com.pdomingo.service.InputValidation.Validate.*;



public class RoleIO {


	public RoleIO(){

	}

	public void chooseOperation(){

		System.out.println("\n \t--- Choose Operation --- \n");
		System.out.println("\n \t1. Create Role");
		System.out.println("\n \t2. Read Role");
		System.out.println("\n \t3. Update Role");
		System.out.println("\n \t4. Delete Role");
		System.out.println("\n \t5. List Roles");
		System.out.println("\n \t7. Exit");

	}

	private String askRole(){

		System.out.println("\n \tInput role \n");

		String inputRole = InputValidation.Validate.getRequiredInput();

		return inputRole;
	}

	public Role createRole(){
		Role role = new Role();

        role.setRole(askRole());
        return role;
	}

	public void readRole(){

	}

	public void updateRole(){

	}

	public void deleteRole(){

	}

	public void listRoles(){

	}

}
