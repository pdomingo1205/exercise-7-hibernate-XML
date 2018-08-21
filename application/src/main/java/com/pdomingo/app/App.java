package com.pdomingo.app;

import java.io.IOException;
import java.util.*;


import com.pdomingo.model.role.*;
import com.pdomingo.model.person.*;
import com.pdomingo.service.*;



public class App {


	public static void main(String[] args) {
		PersonIO personIO = new PersonIO();

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
		//roles.add(new Role("Janitor"));
		//roles.add(new Role("Data Scientist"));

		Person person1 = new Person(name, address, new Date(), 1.5, new Date(), true);
		contact.setPerson(person1);
		contacts.add(contact);

		RoleService roleService = new RoleService();
		//Role newRole = roleService.checkIfUnique("Janitor");
		//roles.add(newRole);
		Role newerRole = new Role();
		newerRole.setRoleId(Long.valueOf(2));
		newerRole.setRole("Data Scientist");
		roles.add(newerRole);

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
		/*
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
		 */
	}

}
