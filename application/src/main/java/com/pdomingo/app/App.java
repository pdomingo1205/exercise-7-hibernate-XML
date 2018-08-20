package com.pdomingo.app;

import java.io.IOException;
import java.util.*;

import com.pdomingo.model.role.*;
import com.pdomingo.model.person.*;
import com.pdomingo.service.*;

public class App {

	public static void main(String[] args) {

		Name name = new Name();
		name.setFirstName("John");
		name.setLastName("Doe");
		Address address = new Address();
		address.setStreetNo(666);
		address.setBarangay("Noland Street");

		Set<ContactInfo> contacts =  new HashSet<ContactInfo>();
		ContactInfo contact = new ContactInfo();

		PersonService personService = new PersonService();
		Person person1 = new Person(name, address, new Date(), 1.5, new Date(), true);
		name = new Name();
		name.setFirstName("Chris");
		name.setLastName("Phat");
		Person person2 = new Person(name, address, new Date(), 1.5, new Date(), true);
		name = new Name();
		name.setFirstName("Might");
		name.setLastName("Die");
		Person person3 = new Person(name, address, new Date(), 1.5, new Date(), true);
		System.out.println("*** Persist - start ***");
		personService.persist(person1);

		List<Person> persons1 = personService.findAll();
		System.out.println("Persons Persisted are :");

		for (Person b : persons1) {
			System.out.println("-" + b.toString());
		}

		System.out.println("*** Persist - end ***");
		System.out.println("*** Update - start ***");

		personService.persist(person1);
		/*
		System.out.println("Person Updated is =>" +personService.findById(person1.getPersonId()));
		System.out.println("*** Update - end ***");
		System.out.println("*** Find - start ***");
		Long id1 = person1.getPersonId();
		Person another = personService.findById(id1);
		System.out.println("Person found with id " + id1 + " is =>" + another.toString());
		System.out.println("*** Find - end ***");
		System.out.println("*** Delete - start ***");
		Long id3 = person3.getPersonId();
		personService.delete(id3);
		System.out.println("Deleted person with id " + id3 + ".");
		System.out.println("Now all persons are " + personService.findAll().size() + ".");
		System.out.println("*** Delete - end ***");
		System.out.println("*** FindAll - start ***");
		List<Person> persons2 = personService.findAll();
		System.out.println("Persons found are :");
		for (Person b : persons2) {
			System.out.println("-" + b.toString());
		}
		System.out.println("*** FindAll - end ***");
		System.out.println("*** DeleteAll - start ***");
		personService.deleteAll();
		System.out.println("Persons found are now " + personService.findAll().size());
		System.out.println("*** DeleteAll - end ***");
		*/
		 System.exit(0);
	}

	/** table creation statement:
	 CREATE TABLE `library`.`person` ( id VARCHAR(50) NOT NULL, title
	 VARCHAR(20) default NULL, author VARCHAR(50) default NULL, PRIMARY KEY
	 (id) );
	 */
}
