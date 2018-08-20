package com.pdomingo.service;

import java.util.List;

import com.pdomingo.model.person.Person;
import com.pdomingo.dao.PersonDao;

public class PersonService {

	private static PersonDao personDao;

	public PersonService() {
		personDao = new PersonDao();
	}

	public void persist(Person entity) {
		personDao.openCurrentSessionwithTransaction();
		personDao.persist(entity);
		personDao.closeCurrentSessionwithTransaction();
	}

	public void update(Person entity) {
		personDao.openCurrentSessionwithTransaction();
		personDao.update(entity);
		personDao.closeCurrentSessionwithTransaction();
	}

	public Person findById(Long id) {
		personDao.openCurrentSession();
		Person person = personDao.findById(id);
		personDao.closeCurrentSession();
		return person;
	}

	public void delete(Long id) {
		personDao.openCurrentSessionwithTransaction();
		Person person = personDao.findById(id);
		personDao.delete(person);
		personDao.closeCurrentSessionwithTransaction();
	}

	public List<Person> findAll() {
		personDao.openCurrentSession();
		List<Person> persons = personDao.findAll();
		personDao.closeCurrentSession();
		return persons;
	}

	public void deleteAll() {
		personDao.openCurrentSessionwithTransaction();
		personDao.deleteAll();
		personDao.closeCurrentSessionwithTransaction();
	}

	public PersonDao personDao() {
		return personDao;
	}
}
