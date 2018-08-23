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
	RoleService roleService = new RoleService();

	public RoleIO(){

	}

	public static void clearScreen() {
	    System.out.print("\033[H\033[2J");
	    System.out.flush();
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


	public Role addRole(){
		Role role = new Role();
		String roleName = askRole();

		Role existingRole = roleService.checkIfUnique(roleName);
		System.out.println(existingRole);
        return role;
	}

	public void createRole(){
		Role role = new Role();
		String roleName = askRole();

		role = roleService.checkIfUnique(roleName);

        roleService.persist(role);
	}

	private String askRole(){

		System.out.println("\n \t--- Input name of Role ---\n");

		String inputRole = InputValidation.Validate.getRequiredInput();

		return inputRole;
	}

	public void readRole(){

		System.out.println("\n\t Input ID of role to read *Use List to find ID's \n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		Role role;

		if(roleService.checkIfExists(inputId)){
			role = roleService.findById(inputId);

			System.out.println(role);
		}
		else{
			System.out.println("\n\t Role does not exist \n");
		}

	}

	public void updateRole(){

		System.out.println("\n\t Input ID of role to update *Use List to find ID's \n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		Role role;

		if(roleService.checkIfExists(inputId)){
			role = roleService.findById(inputId);

			System.out.println("\n\t Input new Role \n");
			String newRole = InputValidation.Validate.getRequiredInput();
			role.setRole(newRole);
			roleService.update(role);

		}
		else{
			System.out.println("\n\t Role does not exist \n");
		}


	}

	public void deleteRole(){

		System.out.println("\n\t Input ID of role to delete *Use List to find ID's \n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		Role role;

		if(roleService.checkIfExists(inputId)){

			roleService.delete(inputId);

		}
		else{
			System.out.println("\n\t Role does not exist \n");
		}
	}

	public void listRoles(){
		System.out.println(roleService.findAll());
	}

}
