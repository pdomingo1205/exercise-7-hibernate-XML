package com.pdomingo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.MappingException;
import org.hibernate.Query;


import com.pdomingo.model.person.ContactInfo;

public class ContactInfoDao implements DaoInterface<ContactInfo, Long> {

	private Session currentSession;

	private Transaction currentTransaction;

	public ContactInfoDao(){
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

	public void persist(ContactInfo entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	public void update(ContactInfo entity) {
		getCurrentSession().update(entity);
	}

	public ContactInfo findById(Long id) {
		ContactInfo contactInfo = (ContactInfo) getCurrentSession().get(ContactInfo.class, id);
		return contactInfo;
	}

	public void delete(ContactInfo entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ContactInfo> findAll() {
		List<ContactInfo> contactInfos = (List<ContactInfo>) getCurrentSession().createQuery("from ContactInfo").list();
		return contactInfos;
	}

	public void deleteAll() {
		List<ContactInfo> entityList = findAll();
		for (ContactInfo entity : entityList) {
			delete(entity);
		}
	}

}
