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
	RoleIO roleIO = new RoleIO();
	ContactIO contactIO = new ContactIO();
	PersonService personService = new PersonService();

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
		name.setFirstName(InputValidation.Validate.getRequiredInput());

		System.out.println("\n \t Input Middle Name \n");
		name.setMiddleName(InputValidation.Validate.getInput());

		System.out.println("\n \t Input Last Name \n");
		name.setLastName(InputValidation.Validate.getRequiredInput());

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

	public Date askDate(String dateType){
		System.out.println(String.format("\n \t Input %s in (YYYY-MM-DD) \n", dateType));
		return InputValidation.Validate.getDate();
	}

	public void createPerson(){
		Name name = askName();
		Address address = askAddress();
		Date birthDay = askDate("Birth Day");
		Date hireDate = null;

		Boolean employmentStatus = InputValidation.Validate.getYesOrNo("\n\t--- Are you currently employed? Y/N ---\n");
		if(employmentStatus.equals(true)){
			hireDate = askDate("Date Hired");
		}

		Double GWA = InputValidation.Validate.getGWA();
		ContactInfo contact = contactIO.editContact(new ContactInfo());
		Role role = roleIO.addRole();

		Person person = new Person(name, address, birthDay, GWA, hireDate, employmentStatus);

		Set<ContactInfo> contacts = new HashSet<ContactInfo>();
		contact.setPerson(person);
		contacts.add(contact);

		Set<Role> roles = new HashSet<Role>();
		roles.add(role);

		person.setContactInfo(contacts);
		person.setRoles(roles);

		personService.persist(person);
		//Person person = new Person();


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
