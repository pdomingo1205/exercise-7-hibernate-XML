package com.pdomingo.app;

import java.io.IOException;
import java.util.*;

import com.pdomingo.model.role.*;
import com.pdomingo.model.person.*;

import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateExample {

    public static void main(String[] args) throws MappingException, IOException {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            Query query;
            /*
            tx.begin();
            query = session.createQuery("from Role");
            List<Role> roleList = query.list();
            System.out.println("Roles found: " + roleList.size());
            for(Role role: roleList) {
                System.out.println(role.getRole());
                session.delete(role);
                System.out.println("Deleted " + role);
            }
            tx.commit();

            System.out.println("Create new Role");
            tx = session.getTransaction();
            tx.begin();
            Role role = new Role();
            role.setRole("Janitor");
            session.saveOrUpdate(role);
            tx.commit();
            */
            /*
            System.out.println("Create new Person");
            tx = session.getTransaction();
            tx.begin();
            Employee emp = new Employee();
            emp.setName("Joe");
            session.saveOrUpdate(emp);
            tx.commit();
            */

            //query = session.createQuery("from Role");

            //System.out.println("List all Roles: " + query.list().get(0));
            //List<Role> roleList = query.list();
            //role = roleList.get(0);
            //System.out.println(role.getRole());


            //Set<Role> roles = new HashSet<Role>();
            //roles.add(role);
            //role.setRole("Developer");
            //roles.add(role);
            Name name = new Name();
            name.setFirstName("John");
            name.setLastName("Doe");
            Address address = new Address();
            address.setStreetNo(666);
            address.setBarangay("Noland Street");

            Set<ContactInfo> contacts =  new HashSet<ContactInfo>();
            ContactInfo contact = new ContactInfo();

            System.out.println("Create new Person");
            tx = session.getTransaction();
            tx.begin();

            Person person = new Person();
            person.setName(name);
            person.setAddress(address);

            contact = new ContactInfo("09176265536",person);
            person.getContactInfo().add(contact);

            contact = new ContactInfo("696969",person);
            person.getContactInfo().add(contact);

                Set<Role> roles = new HashSet<Role>();
    			roles.add(new Role("Maths"));
    			roles.add(new Role("Computer Science"));

                person.setRoles(roles);
    			//Student student1 = new Student("Eswar", courses);
    			//Student student2 = new Student("Joe", courses);
    			//session.save(student1);
    			//session.save(student2);

            session.saveOrUpdate(person);
            session.save(contact);
            session.save(contact);


            //person.setRoles(roles);


            tx.commit();

        } catch (RuntimeException e) {
            tx.rollback();
            throw e;

        } finally {
            session.close();
        }
    }
}
