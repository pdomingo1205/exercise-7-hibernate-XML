package com.pdomingo.service;


import java.util.*;
import java.util.stream.Collectors;
import com.pdomingo.model.person.*;
import com.pdomingo.model.role.Role;
import com.pdomingo.dao.PersonDao;
import org.hibernate.NonUniqueObjectException;

public class PersonService {

	private static PersonDao personDao;

	public PersonService() {
		personDao = new PersonDao();
	}

	public PersonService(PersonDao newDao) {
		personDao = newDao;
	}

	public String persist(Person entity) {
		String updateStatus;

		try{
			System.out.println("\n\n\n" + entity.getName() + "\n\n\n");
			personDao.openCurrentSessionwithTransaction();
			personDao.persist(entity);
			personDao.closeCurrentSessionwithTransaction();
			updateStatus = "\n\t !!! Insert Successful !!!";
		}catch(Exception e){
			updateStatus = "\n\t !-- Insert Failed --!";
		}

		return updateStatus;

	}

	public String update(Person entity) {
		String updateStatus;
		try{
			personDao.openCurrentSessionwithTransaction();
			//System.out.println(entity.getRoles());
			personDao.update(entity);
			personDao.closeCurrentSessionwithTransaction();
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

	public Boolean containsRole(Set<Role> list, String name){
    	return list.stream().filter(o -> o.getRole().equals(name)).findFirst().isPresent();
	}

	public Person findById(Long id) {
		personDao.openCurrentSession();
		Person person = personDao.findById(id);
		//System.out.println("pservice findbyid\n\n\n" + person.getName() + "\n\n\n");
		//personDao.getCurrentSession().evict(person);
		personDao.closeCurrentSession();
		return person;
	}

	public Boolean checkIfExists(Long Id){
		Boolean exists = true;

		personDao.openCurrentSession();
		Person person = personDao.findById(Id);
		personDao.closeCurrentSession();

		if(person == null){
			exists = false;
		}

		return exists;
	}

	public String delete(Long id) {

		String textToReturn;
		try{
			personDao.openCurrentSessionwithTransaction();
			Person person = personDao.findById(id);
			personDao.delete(person);
			personDao.closeCurrentSessionwithTransaction();
			textToReturn = "\n\t!!! Deleted !!!\n";

		}catch(Exception e){
			textToReturn = "\n\t!-- Delete Failed --!\n";
		}

		return textToReturn;
	}

	public List<Person> findAll() {
		personDao.openCurrentSession();
		List<Person> persons = personDao.findAll();
		personDao.closeCurrentSession();
		return persons;
	}

	public List<Person> findOrderBy(String field, Integer order) {
		String stringOrder = order.equals(1)? "ASC": "DESC";
		personDao.openCurrentSession();
		List<Person> persons = personDao.findAllOrderBy(field, stringOrder);
		personDao.closeCurrentSession();
		return persons;
	}

	public String deleteAll() {

		String textToReturn;
		try{
			personDao.openCurrentSessionwithTransaction();
			personDao.deleteAll();
			personDao.closeCurrentSessionwithTransaction();
			textToReturn = "\n\t!!! Deleted !!!\n";

		}catch(Exception e){
			textToReturn = "\n\t!-- Delete Failed --!\n";
		}

		return textToReturn;
	}

	public List<Role> findRoles(Long id){
		personDao.openCurrentSessionwithTransaction();
		List<Role> roles = personDao.findPersonRoles(id);
		personDao.closeCurrentSessionwithTransaction();
		return roles;
	}

	public List<String> findContacts(Long id){
		personDao.openCurrentSessionwithTransaction();
		List<ContactInfo> contacts = personDao.findPersonContacts(id);
		
		List<String> contactString = new ArrayList<String>();

		for (ContactInfo contact : contacts) {
			contactString.add(contact.toString());
		}
		personDao.closeCurrentSessionwithTransaction();

		return contactString;
	}

	public PersonDao personDao() {
		return personDao;
	}
}
