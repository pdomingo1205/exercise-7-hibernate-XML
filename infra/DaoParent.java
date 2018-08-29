package com.pdomingo.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.pdomingo.model.person.*;
import com.pdomingo.model.role.*;

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

public class DaoParent<T, Id extends Serializable> {

	static private Session currentSession;
	static private Transaction currentTransaction;

	public DaoParent(){

	}

	public Session openCurrentSession() {
		currentSession = DaoConfig.getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = DaoConfig.getSessionFactory().openSession();
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

	public void persist(T entity){
		getCurrentSession().saveOrUpdate(entity);
		getCurrentSession().flush();
	}

	public void update(T entity) {
		getCurrentSession().update(entity);
		getCurrentSession().flush();
	}


	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public List<T> findAll (Class<T> type) {
		List<T> list = getCurrentSession().createCriteria(type).list();
		//Collections.sort(list);
		return list;
	}

	public <T>T findById (Long id, Class<?> type) {
		T t = (T) getCurrentSession().get(type, id);
		return t;
	}


	public void deleteAll(Class<T> type) {
		List<T> entityList = findAll(type);
		for (T entity : entityList) {
			delete(entity);
		}
	}

	public void updateRole(Person entity) {
		Person person = findById(entity.getPersonId(), Person.class);
		getCurrentSession().evict(person);
		person = entity;
		getCurrentSession().merge(person);
		getCurrentSession().flush();
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
	public List<ContactInfo> findPersonContacts(Long id) {
		//List<Role> roles = (List<Role>) getCurrentSession().createQuery("select role from person_roles inner join role on role.role_id = person_roles.role_id where person_roles.person_id=1").list();
		List<ContactInfo> roles = (List<ContactInfo>) getCurrentSession().createQuery("from ContactInfo where person.personId = "+id).list();

		return roles;
	}

	public Role findByRoleName(String roleName){
		Role role = new Role();
		try{
			Query query= getCurrentSession().createQuery("from Role where role=:name");
			query.setParameter("name", roleName);
			role = (Role) query.uniqueResult();
		}catch(Exception e){

		}
		return role;
	}

}
