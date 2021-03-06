package com.pdomingo.app;

import java.io.IOException;
import java.util.*;
import java.text.*;

import org.apache.commons.lang3.StringUtils;
import com.pdomingo.model.role.*;
import com.pdomingo.model.person.*;
import com.pdomingo.service.*;

import static com.pdomingo.util.InputValidation.Validate.*;
import com.pdomingo.util.*;


public class PersonIO {

	Scanner scan = new Scanner(System.in);
	RoleIO roleIO = new RoleIO();
	ContactIO contactIO = new ContactIO();
	PersonService personService = new PersonService();
	ContactInfoService contactInfoService = new ContactInfoService();

	public PersonIO(){

	}

	public PersonIO(Scanner newScanner){
		scan = newScanner;
	}

	public Integer chooseOperation(){

		System.out.println("\n \t--- Choose Operation --- \n");
		System.out.println("\n \t1. Create Person");
		System.out.println("\n \t2. Read Person");
		System.out.println("\n \t3. Update Person");
		System.out.println("\n \t4. Delete Person");
		System.out.println("\n \t5. List Persons");
		System.out.println("\n \t6. Manage Person (Role/Contact Info)");
		System.out.println("\n \t7. Exit");

		return InputValidation.Validate.getIntegerInRange(1,7);

	}

	public void doChooseOperation(Integer choice){

		switch(choice){
			case 1:
				createPerson();
				break;
			case 2:
				readPerson();
				break;
			case 3:
				updatePerson();
				break;
			case 4:
				deletePerson();
				break;
			case 5:
				askListBy();
				break;
			case 6:
				managePerson();
				break;
			case 7:
				break;
		}

	}

	public Name askName(){
		Name name = new Name();

		System.out.println("\n \t--- Input Title ---\n");
		name.setTitle(InputValidation.Validate.getInput());

		System.out.println("\n \t--- Input First Name ---\n");
		name.setFirstName(InputValidation.Validate.getRequiredInput());

		System.out.println("\n \t--- Input Middle Name ---\n");
		name.setMiddleName(InputValidation.Validate.getInput());

		System.out.println("\n \t--- Input Last Name ---\n");
		name.setLastName(InputValidation.Validate.getRequiredInput());

		System.out.println("\n \t--- Input Suffix ---\n");
		name.setSuffix(InputValidation.Validate.getInput());

		return name;
	}

	public Address askAddress(){
		Address address = new Address();

		System.out.println("\n \t--- Input Street # ---\n");
		address.setStreetNo(InputValidation.Validate.getInteger());

		System.out.println("\n \t--- Input Barangay ---\n");
		address.setBarangay(InputValidation.Validate.getInput());

		System.out.println("\n \t--- Input Municipality ---\n");
		address.setMunicipality(InputValidation.Validate.getInput());

		System.out.println("\n \t--- Input Zip Code ---\n");
		address.setZipCode(InputValidation.Validate.getInput());

		return address;
	}

	public Date askDate(String dateType){
		System.out.println(String.format("\n \t--- Input %s in (YYYY-MM-DD) ---\n", dateType));
		return InputValidation.Validate.getDate();
	}

	public void createPerson(){

		Name name = askName();
		Address address = askAddress();
		Date birthDay = askDate("Birth Day");
		Date hireDate = askDate("Date Hired");
		Boolean employmentStatus = InputValidation.Validate.getYesOrNo("\n\t --- Is currently employed? Y/N ---\n");
		Double GWA = InputValidation.Validate.getGWA();

		ContactInfo contact = contactIO.editContact(new ContactInfo());
		Role role = roleIO.addRole();
		System.out.println(role);
		Person person = new Person(name, address, birthDay, GWA, hireDate, employmentStatus);

		Set<ContactInfo> contacts = new HashSet<ContactInfo>();
		contact.setPerson(person);
		contacts.add(contact);

		Set<Role> roles = new HashSet<Role>();
		roles.add(role);

		person.setContactInfo(contacts);
		person.setRoles(roles);

		System.out.println(personService.persist(person));
		//Person person = new Person();


	}

	public String listRole(Long id){
		String toReturn = "";
		for(Role role : personService.findRoles(id)) {
		   toReturn = "\n\t" + toReturn +" "+ role;
		}
		return toReturn;
	}

	public String listContacts(Long id){
		String toReturn = "";
		for(String contactInfo : personService.findContacts(id)) {
		   toReturn = "\n\t" + toReturn + " " + contactInfo;
		}
		return toReturn;
	}

	public void readPerson(){

			System.out.println("\n\t--- Input ID of Person to read ---\n");
			Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
			Person person;

			if(personService.checkIfExists(inputId)){
				person = personService.findById(inputId);
				StringBuilder personDetail = new StringBuilder();
				personDetail.append(String.format("\n\t--- Name : %s ", person.getName()));
				personDetail.append(String.format("\n\t--- Born in : %s", person.getbDay()));
				personDetail.append(String.format("\n\t--- Lives in : %s", person.getAddress()));
				personDetail.append(String.format("\n\t--- gwa : %f", person.getGWA()));

				if(person.getCurrEmployed()){
					personDetail.append(String.format("\n\t--- Hired in : %s", person.getDateHired()));
				}

				personDetail.append(String.format("\n\t--- Contact Information : %s", listContacts(inputId)));
				personDetail.append(String.format("\n\t--- Roles : %s", listRole(inputId)));

				String finalString = personDetail.toString();
				System.out.println(finalString);
			}
			else{
				System.out.println("\n\t!-- Person does not exist--!\n");
			}

	}

	private Integer askUpdateChoice(){

		System.out.println("\n\t--- Choose what to update *Use Manage Person to edit contacts/Roles ---\n");
		System.out.println("\n\t1. Name \n");
		System.out.println("\n\t2. Address \n");
		System.out.println("\n\t3. GWA \n");
		System.out.println("\n\t4. BirthDay \n");
		System.out.println("\n\t5. Set Hire Date\n");
		System.out.println("\n\t6. Set Employment Status\n");
		System.out.println("\n\t7. Exit \n");

		Integer choice = InputValidation.Validate.getIntegerInRange(1,7);
		return choice;
	}

	private Person doUpdateChoice(Integer choice, Person person){
		String contactInfo = "";

		switch (choice.intValue()){

			case 1:
					person.setName(askName());
					break;
			case 2:
					person.setAddress(askAddress());
					break;
			case 3:
					person.setGWA(InputValidation.Validate.getGWA());
					break;
			case 4:
					person.setbDay(askDate("Birth Day"));
					break;
			case 5:
					person.setDateHired(askDate("Date Hired"));
					break;
			case 6:
					person.setCurrEmployed(InputValidation.Validate.getYesOrNo("\n\t--- Is currently employed? Y/N ---\n"));
					break;
			default:
					break;
		}

		return person;
	}

	public void updatePerson(){
		System.out.println("\n\t---Input ID of Person to update *Use List to find ID's ---\n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		Person person;

		if (personService.checkIfExists(inputId)) {
			person = personService.findById(inputId);
			Integer choice;

			do{
				choice = askUpdateChoice();
				person = doUpdateChoice(choice,person);
			}while(choice.intValue() != 7);

			System.out.println(personService.update(person));

		}
		else {
			System.out.println("\n\t!-- Person does not exist --!\n");
		}
	}

	public void deletePerson(){
		System.out.println("\n\t Input ID of person to delete *Use List to find ID's \n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		Role role;

		if (personService.checkIfExists(inputId)) {

			System.out.println(personService.delete(inputId));

		}
		else {
			System.out.println("\n\t Person does not exist \n");
		}
	}

	public void askListBy(){
		System.out.println("\n\t --- List by? ---\n");
		System.out.println("\n\t 1.GWA \n");
		System.out.println("\n\t 2.Date Hired \n");
		System.out.println("\n\t 3.Last Name \n");
		System.out.println("\n\t 4.No Order \n");

		doListPerson(InputValidation.Validate.getIntegerInRange(1,4));

	}

	public Integer askOrder(){
		System.out.println("\n\t --- Order by? ---\n");
		System.out.println("\n\t 1.Ascending \n");
		System.out.println("\n\t 2.Descending \n");

		return InputValidation.Validate.getIntegerInRange(1,2);
	}

	public void doListPerson(Integer choice){
		Integer order;

		switch(choice){
			case 1:

				listPersonsByGWA(askOrder());
				break;
			case 2:
				listPersonsBy("date_hired", askOrder());
				break;
			case 3:
				listPersonsBy("last_name", askOrder());
				break;
			case 4:
				listPersonsBy();
				break;
		}
	}

	public void listPersonsBy(){
		for (Person p : personService.findAll()) {
			System.out.println(String.format("ID:%s %s", p.getPersonId(), p));
		}
	}

	public void listPersonsByGWA(Integer order){

		List<Person> persons = personService.findAll();
		Collections.sort(persons, new Comparator<Person>(){
		    public int compare(Person p1, Person p2) {
		        return p1.getGWA().compareTo(p2.getGWA());
		    }
		});

		if(order.equals(1)){
			for (Person p : persons) {
				System.out.println(String.format("ID:%s %s", p.getPersonId(), p));
			}
		}else{
			Collections.reverse(persons);
			for (Person p : persons) {
				System.out.println(String.format("ID:%s %s", p.getPersonId(), p));
			}
		}

	}

	public void listPersonsBy(String property, Integer order){

		for (Person p : personService.findOrderBy(property, order)) {
			System.out.println(String.format("ID:%s %s", p.getPersonId(), p));
		}

	}

	private Integer chooseManage(){
		System.out.println("\n\t---Choose what to manage---\n");
		System.out.println("\n\t1.Role\n");
		System.out.println("\n\t2.Contact Info\n");

		return InputValidation.Validate.getIntegerInRange(1,2);
	}

	private void manageRole(Person person){

		System.out.println("\n\t--- Choose Operation ---\n");
		System.out.println("\n\t1.Add Role\n");
		System.out.println("\n\t2.Delete Role\n");
		System.out.println("\n\t3.Update Role\n");
		System.out.println("\n\t4.List Role\n");

		Set<Role> roles;
		String roleToUpdate;

		System.out.println(listRole(person.getPersonId()));

		switch(InputValidation.Validate.getIntegerInRange(1,4)){

			case 1:
				roles = person.getRoles();
				System.out.println(roles);
				roles.add(roleIO.addRole());
				person.setRoles(roles);
				//person.getRoles().add(roleIO.addRole());
				break;

			case 2:
				roles = person.getRoles();
				System.out.println("--- Input name of Role to Delete ---");
				roleToUpdate = InputValidation.Validate.getRequiredInput();

				if(personService.containsRole(roles, roleToUpdate)){
					roles = personService.removeRole(roles, roleToUpdate);
				}else{
					System.out.println("Role does not exist");
				}

				break;

			case 3:
				roles = person.getRoles();
				System.out.println("--- Input name of Role to Update ---");
				roleToUpdate = InputValidation.Validate.getRequiredInput();

				if(personService.containsRole(roles, roleToUpdate)){
					roles = personService.removeRole(roles, roleToUpdate);
					roles.add(roleIO.addRole());
				}else{
					System.out.println("Role does not exist");
				}

				break;

			case 4:
				System.out.println(listRole(person.getPersonId()));
			break;

		}

		System.out.println(personService.update(person));

	}

	private void manageContacts(Person person){

		System.out.println("\n\t--- Choose Operation ---\n");
		System.out.println("\n\t1.Add Contact Info\n");
		System.out.println("\n\t2.Delete Contact Info\n");
		System.out.println("\n\t3.Update Contact Info\n");
		System.out.println("\n\t4.List Contacts\n");
		Set<ContactInfo> contacts;
		ContactInfo contact;
		Long id;

		System.out.println(listContacts(person.getPersonId()));

		Integer choice = InputValidation.Validate.getIntegerInRange(1,4);

		switch(choice){

			case 1:
				contacts = person.getContactInfo();
				contact = contactIO.addContact();

				contact.setPerson(person);
				contacts.add(contact);
				person.setContactInfo(contacts);
				break;

			case 2:
				contacts = person.getContactInfo();

				System.out.println("--- Input Id of Contact Info to Delete ---");
				id = Long.valueOf(InputValidation.Validate.getInteger());

				contacts =  personService.removeContact(contacts, Long.valueOf(id));
				person.setContactInfo(contacts);

				System.out.println(person.getContactInfo());
				System.out.println(contactInfoService.delete(id));
				break;

			case 3:
				contactIO.updateContact();
				break;

			case 4:
				System.out.println(listContacts(person.getPersonId()));
			break;
		}

		if(choice.equals(4) == false && choice.equals(3) == false){
			System.out.println(personService.update(person));
		}

	}

	public void managePerson(){
		System.out.println("\n\t--- Input ID of Person to Manage ---\n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		Person person;

		if(personService.checkIfExists(inputId)){
			person = personService.findById(inputId);
			if(chooseManage().equals(1)){
				manageRole(person);

			}else{
				manageContacts(person);
			}
		}
		else{
			System.out.println("\n\t!-- Person does not exist--!\n");
		}
	}

	public void start(){
		Integer choice;
		do{
			choice = chooseOperation();
			doChooseOperation(choice);
		}while(choice != 7);
	}

}
