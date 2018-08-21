package com.pdomingo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.MappingException;
import org.hibernate.Query;


import com.pdomingo.model.person.Person;

public class PersonDao implements DaoInterface<Person, Long> {

	private Session currentSession;

	private Transaction currentTransaction;

	public PersonDao(){
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
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
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

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
		System.out.println("\n\n\n" + entity.getName() + "\n\n\n");
		getCurrentSession().saveOrUpdate(entity);
		getCurrentSession().flush();
	}

	public void update(Person entity) {
		getCurrentSession().update(entity);
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

	public void deleteAll() {
		List<Person> entityList = findAll();
		for (Person entity : entityList) {
			delete(entity);
		}
	}

}
