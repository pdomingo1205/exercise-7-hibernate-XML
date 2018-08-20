package com.pdomingo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.MappingException;
import org.hibernate.Query;


import com.pdomingo.model.role.Role;

public class RoleDao implements DaoInterface<Role, Long> {

	private Session currentSession;

	private Transaction currentTransaction;

	public RoleDao(){
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

	public void persist(Role entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	public void update(Role entity) {
		getCurrentSession().update(entity);
	}

	public Role findById(Long id) {
		Role role = (Role) getCurrentSession().get(Role.class, id);
		return role;
	}

	public void delete(Role entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Role> findAll() {
		List<Role> roles = (List<Role>) getCurrentSession().createQuery("distinct from Role").list();
		return roles;
	}

	public void deleteAll() {
		List<Role> entityList = findAll();
		for (Role entity : entityList) {
			delete(entity);
		}
	}

}
