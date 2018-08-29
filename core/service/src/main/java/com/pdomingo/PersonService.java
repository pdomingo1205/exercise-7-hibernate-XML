package com.pdomingo.service;


import java.util.*;
import java.util.stream.Collectors;
import com.pdomingo.model.person.*;
import com.pdomingo.model.role.Role;
import com.pdomingo.dao.*;
import org.hibernate.NonUniqueObjectException;

public class PersonService {

	DaoParent<Person,Long> dao;

	public PersonService() {
		dao = new DaoParent();
	}

	public PersonService(DaoParent newDao) {
		dao = newDao;
	}

	public void setDao(DaoParent newDao){
		dao = newDao;
	}

	public String persist(Person entity) {
		String updateStatus;

		try{
			System.out.println("\n\n\n" + entity.getName() + "\n\n\n");
			dao.openCurrentSessionwithTransaction();
			dao.persist(entity);
			dao.closeCurrentSessionwithTransaction();
			updateStatus = "\n\t !!! Insert Successful !!!";
		}catch(Exception e){
			updateStatus = "\n\t !-- Insert Failed --!";
		}

		return updateStatus;

	}

	public String update(Person entity) {
		String updateStatus;
		try{
			dao.openCurrentSessionwithTransaction();
			//System.out.println(entity.getRoles());
			dao.update(entity);
			dao.closeCurrentSessionwithTransaction();
			updateStatus = "\n\t !!! Update Successful !!!";
		}
		catch(Exception e){
			updateStatus = "\n\t !-- Update Failed --!";
		}

		return updateStatus;
	}

	public Set<Role> removeRole(Set<Role> roles, String roleName){
		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
		    Role role  =  iterator.next();
		    if (role.getRole().equals(roleName)) {
		        iterator.remove();
		    }
		}
		return roles;
	}


	public Set<ContactInfo> removeContact(Set<ContactInfo> contacts, Long id){
		for (Iterator<ContactInfo> iterator = contacts.iterator(); iterator.hasNext();) {
			ContactInfo contact  =  iterator.next();
			if (contact.getContactInfoId().equals(id)) {
				iterator.remove();
			}
		}
		return contacts;
	}

	public Boolean containsRole(Set<Role> listRole, String name){
		//System.out.println("AAAAAAAAAAAAA"+listRole.stream().filter(role -> role.getRole().equals(name)).findFirst().isPresent());
    	return listRole.stream().filter(role -> role.getRole().equals(name)).findFirst().isPresent();
	}

	public Person findById(Long id) {
		dao.openCurrentSession();
		Person person =(Person) dao.findById(id, Person.class);
		dao.closeCurrentSession();
		return person;
	}

	public Boolean checkIfExists(Long Id){
		Boolean exists = true;
		Person person = null;
		try{
			dao.openCurrentSession();
			 person = (Person) dao.findById(Id, Person.class);
			dao.closeCurrentSession();
		}
		catch(NullPointerException ne){
			exists = false;
		}


		if(person == null){
			exists = false;
		}

		return exists;
	}

	public String delete(Long id) {

		String textToReturn;
		try{
			dao.openCurrentSessionwithTransaction();
			Person person = (Person) dao.findById(id, Person.class);
			dao.delete(person);
			dao.closeCurrentSessionwithTransaction();
			textToReturn = "\n\t!!! Deleted !!!\n";

		}catch(Exception e){
			textToReturn = "\n\t!-- Delete Failed --!\n";
		}

		return textToReturn;
	}

	public List<Person> findAll() {
		dao.openCurrentSession();
		List<Person> persons = dao.findAll(Person.class);
		dao.closeCurrentSession();
		return persons;
	}

	public List<Person> findOrderBy(String field, Integer order) {
		String stringOrder = order.equals(1)? "ASC": "DESC";
		dao.openCurrentSession();
		List<Person> persons = dao.findAllOrderBy(field, stringOrder);
		dao.closeCurrentSession();
		return persons;
	}

	public String deleteAll() {

		String textToReturn;
		try{
			dao.openCurrentSessionwithTransaction();
			dao.deleteAll(Person.class);
			dao.closeCurrentSessionwithTransaction();
			textToReturn = "\n\t!!! Deleted !!!\n";

		}catch(Exception e){
			textToReturn = "\n\t!-- Delete Failed --!\n";
		}

		return textToReturn;
	}

	public List<Role> findRoles(Long id){
		dao.openCurrentSessionwithTransaction();
		List<Role> roles = dao.findPersonRoles(id);
		dao.closeCurrentSessionwithTransaction();
		return roles;
	}

	public List<String> findContacts(Long id){
		dao.openCurrentSessionwithTransaction();
		List<ContactInfo> contacts = dao.findPersonContacts(id);

		List<String> contactString = new ArrayList<String>();

		for (ContactInfo contact : contacts) {
			contactString.add(contact.toString());
		}
		dao.closeCurrentSessionwithTransaction();

		return contactString;
	}

	public DaoParent dao() {
		return dao;
	}
}
