package com.pdomingo.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public interface DaoInterface<T, Id extends Serializable> {

	static final Configuration configuration = new Configuration().configure();
	static final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	static final SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

	public void persist(T entity);

	public void update(T entity);

	public T findById(Id id);

	public void delete(T entity);

	public List<T> findAll();

	public void deleteAll();

}
