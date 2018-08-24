package com.pdomingo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Transaction;

import org.hibernate.MappingException;
import org.hibernate.Query;


import com.pdomingo.model.person.*;
import com.pdomingo.model.role.Role;

public class PersonDao implements DaoInterface<Person, Long> {

	private Session currentSession;

	private Transaction currentTransaction;

	public PersonDao(){
	}

	public Session openCurrentSession() {
		currentSession = sessionFactory.openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = sessionFactory.openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	private static SessionFactory getSessionFactory() {

		//Configuration configuration = new Configuration().configure();
        //SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(Person entity) {
		//System.out.println("\n\n\n" + entity.getName() + "\n\n\n");
		getCurrentSession().saveOrUpdate(entity);
		getCurrentSession().flush();
	}

	public void update(Person entity) {
		getCurrentSession().update(entity);
		getCurrentSession().flush();
	}

	public void updateRole(Person entity) {
		Person person = findById(entity.getPersonId());
		getCurrentSession().evict(person);
		person = entity;
		getCurrentSession().merge(person);
		getCurrentSession().flush();
	}

	public Person findById(Long id) {
		Person person = (Person) getCurrentSession().get(Person.class, id);
		return person;
	}

	public void delete(Person entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Person> findAll() {
		List<Person> persons = (List<Person>) getCurrentSession().createQuery("from Person").list();
		return persons;
	}

	public List<Person> findAllOrderBy(String field, String order) {
		List<Person> persons = (List<Person>) getCurrentSession().createQuery(String.format("from Person ORDER BY %s %s", field, order)).list();
		return persons;
	}

	@SuppressWarnings("unchecked")
	public List<Role> findPersonRoles(Long id) {
		//List<Role> roles = (List<Role>) getCurrentSession().createQuery("select role from person_roles inner join role on role.role_id = person_roles.role_id where person_roles.person_id=1").list();
		List<Role> roles = (List<Role>) getCurrentSession().createQuery("select role from Person person join person.roles role where person.personId = "+id).list();
		return roles;
	}

	@SuppressWarnings("unchecked")
	public List<String> findPersonContacts(Long id) {
		//List<Role> roles = (List<Role>) getCurrentSession().createQuery("select role from person_roles inner join role on role.role_id = person_roles.role_id where person_roles.person_id=1").list();
		List<String> roles = (List<String>) getCurrentSession().createQuery("select contactInfo from ContactInfo where person.personId = "+id).list();

		return roles;
	}


	public void deleteAll() {
		List<Person> entityList = findAll();
		for (Person entity : entityList) {
			delete(entity);
		}
	}

}
