package com.pdomingo.app;

import java.io.IOException;
import java.util.*;
import java.text.*;

import org.apache.commons.lang3.StringUtils;
import com.pdomingo.model.role.*;
import com.pdomingo.model.person.*;
import com.pdomingo.service.*;

import static com.pdomingo.service.InputValidation.Validate.*;



public class PersonIO {
	Scanner scan = new Scanner(System.in);


	public PersonIO(){

	}

	public PersonIO(Scanner newScanner){
		scan = newScanner;
	}

	public void chooseOperation(){

		System.out.println("\n \t--- Choose Operation --- \n");
		System.out.println("\n \t1. Create Person");
		System.out.println("\n \t2. Read Person");
		System.out.println("\n \t3. Update Person");
		System.out.println("\n \t4. Delete Person");
		System.out.println("\n \t5. List Persons");
		System.out.println("\n \t6. Manage Person (Role/Contact Info)");
		System.out.println("\n \t7. Exit");

	}

	public Name askName(){
		Name name = new Name();

		System.out.println("\n \t Input Title \n");
		name.setTitle(InputValidation.Validate.getInput());

		System.out.println("\n \t Input First Name \n");
		name.setFirstName(InputValidation.Validate.getInput());

		System.out.println("\n \t Input Middle Name \n");
		name.setMiddleName(InputValidation.Validate.getInput());

		System.out.println("\n \t Input Last Name \n");
		name.setLastName(InputValidation.Validate.getInput());

		System.out.println("\n \t Input Suffix \n");
		name.setSuffix(InputValidation.Validate.getInput());

		return name;
	}

	public Address askAddress(){
		Address address = new Address();

		System.out.println("\n \t Input Street # \n");
		address.setStreetNo(InputValidation.Validate.getInteger());

		System.out.println("\n \t Input Barangay \n");
		address.setBarangay(InputValidation.Validate.getInput());

		System.out.println("\n \t Input Municipality \n");
		address.setMunicipality(InputValidation.Validate.getInput());

		System.out.println("\n \t Input Zip Code \n");
		address.setZipCode(InputValidation.Validate.getInput());

		return address;
	}



	public Date askBirthDay(){
		System.out.println("\n \t Input Birthday in (YYYY-MM-DD) \n");

		return InputValidation.Validate.getDate();
	}



	public void createPerson(){
		Person person = new Person();
		person.setName(askName());

	}

	public void readPerson(){

	}

	public void updatePerson(){

	}

	public void deletePerson(){

	}

	public void listPersons(){

	}

	public void managePerson(){

	}

	public void start(){
/*
		String input;
		chooseOperation();

		switch(input.toUpperCase()) {
			case "A":

				break;

			case "B":
		}
*/
	}

}
