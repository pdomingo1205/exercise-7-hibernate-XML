package com.pdomingo.app;

import java.io.IOException;
import java.util.*;
import java.text.*;

import org.apache.commons.lang3.StringUtils;
import com.pdomingo.model.role.*;
import com.pdomingo.model.person.*;
import com.pdomingo.service.*;
import com.pdomingo.util.*;

import static com.pdomingo.util.InputValidation.Validate.*;



public class RoleIO {
	RoleService roleService = new RoleService();

	public RoleIO(){

	}

	public static void clearScreen() {
	    System.out.print("\033[H\033[2J");
	    System.out.flush();
	}
	public Integer chooseOperation(){

		System.out.println("\n\t--- Choose Operation --- \n");
		System.out.println("\n\t 1. Create Role");
		System.out.println("\n\t 2. Update Role");
		System.out.println("\n\t 3. Delete Role");
		System.out.println("\n\t 4. List Roles");
		System.out.println("\n\t 5. Exit");

		return InputValidation.Validate.getIntegerInRange(1,5);
	}

	public void doChooseOperation(Integer choice){

		switch(choice){
			case 1:
				createRole();
				break;
			case 2:
				updateRole();
				break;
			case 3:
				deleteRole();
				break;
			case 4:
				listRoles();
				break;
			case 5:
				break;
		}

	}


	public Role addRole(){
		Role role = new Role();
		String roleName = askRole();


		Role existingRole = roleService.checkIfUnique(roleName);
		role = existingRole;
        return role;
	}

	public void createRole(){
		Role role = new Role();
		String roleName = askRole();

		role = roleService.checkIfUnique(roleName);

        System.out.println(roleService.persist(role));
	}

	private String askRole(){

		System.out.println("\n \t--- Input name of Role ---\n");

		String inputRole = InputValidation.Validate.getRequiredInput();

		return inputRole;
	}

	public void readRole(){

		System.out.println("\n\t--- Input ID of role to read *Use List to find ID's --- \n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		Role role;

		if(roleService.checkIfExists(inputId)){
			role = roleService.findById(inputId);

			System.out.println(role);
		}
		else{
			System.out.println("\n\t!-- Role does not exist --!\n");
		}

	}

	public void updateRole(){

		System.out.println("\n\t--- Input ID of role to update *Use List to find ID's ---\n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		Role role;

		if(roleService.checkIfExists(inputId)){
			role = roleService.findById(inputId);

			System.out.println("\n\t--- Input new Role ---\n");
			String newRole = InputValidation.Validate.getRequiredInput();
			
				role = roleService.checkIfUnique(newRole);
			System.out.println(roleService.update(role));

		}
		else{
			System.out.println("\n\t!-- Role does not exist --!\n");
		}


	}

	public void deleteRole(){

		System.out.println("\n\t--- Input ID of role to delete *Use List to find ID's ---\n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		Role role;

		if(roleService.checkIfExists(inputId)){
			System.out.println(roleService.delete(inputId));

		}
		else{
			System.out.println("\n\t!-- Role does not exist --!\n");
		}
	}

	public void listRoles(){
		for (Role r : roleService.findAll()) {
			System.out.println(r);
		}
	}

	public void start(){
		Integer choice;
		do{
			choice = chooseOperation();
			doChooseOperation(choice);
		}while(choice != 5);
	}

}
