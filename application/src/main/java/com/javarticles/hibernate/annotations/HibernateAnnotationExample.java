package com.javarticles.hibernate.annotations;

import java.io.IOException;
import java.util.List;

import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateAnnotationExample {

    public static void main(String[] args) throws MappingException, IOException {
        Configuration configuration = new Configuration().configure("/annotations/hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Query query = session.createQuery("from Employee where name='Joe'");
            List<Employee> empList = query.list();
            System.out.println("Employees found: " + empList.size());
            for(Employee emp: empList) {
                session.delete(emp);
                System.out.println("Deleted " + emp);
            }
            tx.commit();
            
            System.out.println("Create new employee");
            tx = session.getTransaction();
            tx.begin();
            Employee emp = new Employee();
            emp.setName("Joe");
            session.saveOrUpdate(emp);
            tx.commit();
            
            query = session.createQuery("from Employee where name='Joe'");
            System.out.println("List all employees: " + query.list());
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;

        } finally {
            session.close();
        }
    }
}
