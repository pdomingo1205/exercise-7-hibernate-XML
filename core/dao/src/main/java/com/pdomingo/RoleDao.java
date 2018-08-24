package com.pdomingo.dao;

import java.util.List;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.Criteria;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.pdomingo.model.role.Role;

public class RoleDao implements DaoInterface<Role, Long> {

	private Session currentSession;

	private Transaction currentTransaction;

	public RoleDao(){
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
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

		//Configuration configuration = new Configuration().configure();
        //SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}

	public Session getCurrentSession() {
		System.out.println("Searching..");
		//clearScreen();
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

	public void persist(Set<Role> entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	public void update(Role entity) {
		getCurrentSession().update(entity);
		clearScreen();
	}

	public Role findById(Long id) {

		Role role = (Role) getCurrentSession().get(Role.class, id);
		clearScreen();
		return role;
	}

	public void delete(Role entity) {
		getCurrentSession().delete(entity);
		clearScreen();
	}

	public Role findByRoleName(String roleName){
		Role role = new Role();
		try{
			Query query= getCurrentSession().createQuery("from Role where role=:name");
			query.setParameter("name", roleName);
			role = (Role) query.uniqueResult();
		}catch(Exception e){

		}
		clearScreen();
		return role;
	}


	@SuppressWarnings("unchecked")
	public List<Role> findAll() {
		System.out.println("Searching..");
		List<Role> roles = (List<Role>) getCurrentSession().createQuery("from Role").list();
		clearScreen();
		return roles;
	}

	public void deleteAll() {
		System.out.println("Searching..");
		List<Role> entityList = findAll();
		for (Role entity : entityList) {
			delete(entity);
		}
		clearScreen();
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

}
