package com.pdomingo.service;

import java.util.List;

import com.pdomingo.model.person.*;
import com.pdomingo.model.role.Role;
import com.pdomingo.dao.PersonDao;

public class PersonService {

	private static PersonDao personDao;

	public PersonService() {
		personDao = new PersonDao();
	}

	public void persist(Person entity) {
		System.out.println("\n\n\n" + entity.getName() + "\n\n\n");
		personDao.openCurrentSessionwithTransaction();
		personDao.persist(entity);
		personDao.closeCurrentSessionwithTransaction();
	}

	public void update(Person entity) {
		personDao.openCurrentSessionwithTransaction();
		//System.out.println(entity.getRoles());
		personDao.update(entity);
		personDao.closeCurrentSessionwithTransaction();
	}

	public void updateRole(Person entity) {
		personDao.openCurrentSessionwithTransaction();
		//System.out.println(entity.getRoles());
		personDao.updateRole(entity);
		personDao.closeCurrentSessionwithTransaction();
	}

	public Person findById(Long id) {
		personDao.openCurrentSession();
		Person person = personDao.findById(id);
		//System.out.println("pservice findbyid\n\n\n" + person.getName() + "\n\n\n");
		personDao.getCurrentSession().evict(person);
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

	public void delete(Long id) {
		try{
			personDao.openCurrentSessionwithTransaction();
			Person person = personDao.findById(id);
			personDao.delete(person);
			personDao.closeCurrentSessionwithTransaction();

		}finally{

		}
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

	public void deleteAll() {
		personDao.openCurrentSessionwithTransaction();
		personDao.deleteAll();
		personDao.closeCurrentSessionwithTransaction();
	}

	public List<Role> findRoles(Long id){
		personDao.openCurrentSessionwithTransaction();
		List<Role> roles = personDao.findPersonRoles(id);
		personDao.closeCurrentSessionwithTransaction();
		return roles;
	}

	public List<String> findContacts(Long id){
		personDao.openCurrentSessionwithTransaction();
		List<String> roles = personDao.findPersonContacts(id);
		personDao.closeCurrentSessionwithTransaction();
		return roles;
	}

	public PersonDao personDao() {
		return personDao;
	}
}
