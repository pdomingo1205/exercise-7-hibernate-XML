package com.pdomingo.app;

import java.io.IOException;
import java.util.*;

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

	public void createPerson(){

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

	public void newPerson(){
		System.out.println("");
	}

	/*
	public void start() {
		// Name sample
		Name name = new Name();
		name.setTitle("Dr.");
		name.setFirstName("John");
		name.setMiddleName("H");
		name.setLastName("Doe");
		name.setSuffix("jr.");

		//Address sample
		Address address = new Address();
		address.setStreetNo(666);
		address.setBarangay("Noland Street");
		address.setMunicipality("Malabon");
		address.setZipCode("1020");

		//Contact Sample
		Set<ContactInfo> contacts =  new HashSet<ContactInfo>();
		ContactInfo contact = new ContactInfo();
		contact.setContactInfo("09195545454");
		contact.setContactType("Phone number");
		//Role sample
		Set<Role> roles = new HashSet<Role>();
		roles.add(new Role("Janitor"));
		roles.add(new Role("Data Scientist"));

		Person person1 = new Person(name, address, new Date(), 1.5, new Date(), true);
		contact.setPerson(person1);
		contacts.add(contact);

		person1.setRoles(roles);
		person1.setContactInfo(contacts);

		PersonService personService = new PersonService();
		personService.persist(person1);
		System.out.println("*** Persist - start ***");

		List<Person> persons1 = personService.findAll();
		System.out.println("Persons Persisted are :");

		for (Person b : persons1) {
			System.out.println("-" + b.toString());
		}

		System.out.println("*** Persist - end ***");
		System.out.println("*** Update - start ***");
		System.out.println("\n\n\n\n" + personService.findById(person1.getPersonId()).getContactInfo() +"\n\n\n\n");
		System.out.println("\n\n\n\n" + personService.findById(person1.getPersonId()).getRoles() +"\n\n\n\n");
		System.out.println("\n\n\n\n" + personService.findById(person1.getPersonId()).getAddress() +"\n\n\n\n");
		System.out.println("\n\n\n\n" + personService.findById(person1.getPersonId()).getName() +"\n\n\n\n");
		personService.update(person1);
		Long id1 = person1.getPersonId();

		Person another = personService.findById(id1);
		System.out.println("Person found with id " + id1 + " is =>" + another.toString());
		System.out.println("*** Find - end ***");
		System.out.println("*** Delete - start ***");
		personService.delete(id1);
		System.out.println("Deleted person with id " + id1 + ".");
		System.out.println("Now all persons are " + personService.findAll().size() + ".");
		System.out.println("*** Delete - end ***");
		System.out.println("*** FindAll - start ***");

		personService.deleteAll();
		System.out.println("Persons found are now " + personService.findAll().size());
		System.out.println("*** DeleteAll - end ***");

		 System.exit(0);
	}
*/
}
