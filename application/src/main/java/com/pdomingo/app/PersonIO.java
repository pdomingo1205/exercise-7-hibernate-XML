package com.pdomingo.app;

import java.io.IOException;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import com.pdomingo.model.role.*;
import com.pdomingo.model.person.*;
import com.pdomingo.service.*;

public class Menu {
	Scanner scan = new Scanner(System.in);

	public Menu(){

	}

	public Menu(Scanner newScanner){
		scan = newScanner;
	}

	public String chooseOperation(){
		String input;

		System.out.println("\n \t--- Choose Operation --- \n");
		System.out.println("\n \ta. Create Person");
		System.out.println("\n \tb. Read Person");
		System.out.println("\n \tc. Update Person");
		System.out.println("\n \td. Delete Person");
		System.out.println("\n \te. List Persons");
		System.out.println("\n \tf. Manage Person (Role/Contact Info)");
		System.out.println("\n \tg. Exit");


		input = scan.nextLine();
		return input;
	}

	public Name askName(){
		Name name = new Name();

		System.out.println()

		System.out.println("\n \t Input Title \n");
		name.setTitle(scan.nextLine());

		System.out.println("\n \t Input First Name \n");
		name.setFirstName(scan.nextLine());

		System.out.println("\n \t Input Middle Name \n");
		name.setMiddleName(scan.nextLine());

		System.out.println("\n \t Input Last Name \n");
		name.setLastName(scan.nextLine());

		System.out.println("\n \t Input Suffix \n");
		name.setSuffix(scan.nextLine());

		return name;
	}

	public Address askAddress(){
		Address address = new Address();

		System.out.println("\n \t Input Street # \n");
		address.setStreetNo(scan.nextLine());

		System.out.println("\n \t Input Barangay \n");
		address.setBarangay(scan.nextLine());

		System.out.println("\n \t Input Municipality \n");
		address.setMunicipality(scan.nextLine());

		System.out.println("\n \t Input Zip Code \n");
		address.setZipCode(scan.nextLine());

		return address;
	}

	import java.util.Date;


	public Date askBirthDay(){
		String sDate1 = scan.nextLine();
		Date date = new SimpleDateFormat("YYYY-MM-DD").parse(sDate1);

		return date;
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
		String input;
		input = chooseOperation();

		switch(input.toUpperCase()) {
			case "A":

				break;

			case "B":
		}

	}

}
