package com.pdomingo.service;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import com.pdomingo.dao.PersonDao;
import com.pdomingo.service.*;
import com.pdomingo.model.person.*;
import com.pdomingo.model.role.Role;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PersonServiceTest
    {

        protected static PersonService personService = new PersonService();
        Person person;
        PersonDao mockDao;

        @Test(expected = Exception.class)
        public void test_PersistShould_NotInsert(){
            person = new Person();
            mockDao = mock(PersonDao.class);
            doThrow(new Exception()).when(mockDao).persist(any());
            personService = new PersonService(mockDao);

            assertEquals("Checking if insertion fails", "\n\t !-- Insert Failed --!", personService.persist(person));
        }

        @Test(expected = Exception.class)
        public void test_DeleteShould_NotDelete(){
            person = new Person();
            mockDao = mock(PersonDao.class);
            doThrow(new Exception()).when(mockDao).delete(any());
            when(mockDao.findById(any())).thenReturn(person);
            personService = new PersonService(mockDao);

            assertEquals("Checking if deletion fails", "\n\t!-- Delete Failed --!\n", personService.delete(Long.valueOf(1)));
        }

        @Test
        public void test_DeleteShould_Delete(){
            person = new Person();
            mockDao = mock(PersonDao.class);
            doNothing().when(mockDao).delete(any());
            when(mockDao.findById(any())).thenReturn(person);
            personService = new PersonService(mockDao);

            assertEquals("Checking if deletion succeeds", "\n\t!!! Deleted !!!\n", personService.delete(Long.valueOf(1)));
        }

        @Test(expected = Exception.class)
        public void test_DeleteAllShould_NotDelete(){
            person = new Person();
            mockDao = mock(PersonDao.class);
            doThrow(new Exception()).when(mockDao).deleteAll();

            personService = new PersonService(mockDao);

            assertEquals("Checking if deletion fails", "\n\t!-- Delete Failed --!\n", personService.deleteAll());
        }

        @Test
        public void test_DeleteAllShould_Delete(){
            person = new Person();
            mockDao = mock(PersonDao.class);
            doNothing().when(mockDao).deleteAll();
            personService = new PersonService(mockDao);

            assertEquals("Checking if deletion succeeds", "\n\t!!! Deleted !!!\n",personService.deleteAll());
        }

        @Test
        public void test_PersistShould_Insert(){
            person = new Person();
            mockDao = mock(PersonDao.class);
            doNothing().when(mockDao).persist(any());
            personService = new PersonService(mockDao);

            assertEquals("Checking if insertion succesful",  "\n\t !!! Insert Successful !!!", personService.persist(person));

        }

        @Test
        public void test_findById_Exists(){
            Person p = new Person();
            p.setPersonId(Long.valueOf(1));
            Name name = new Name();
            name.setFirstName("John");
            name.setLastName("Connor");
            p.setName(name);
            mockDao = mock(PersonDao.class);
            //doNothing().when(mockDao).getCurrentSession();
            //doReturn(person).when(mockDao).persist(any());
            when(mockDao.findById(any())).thenReturn(p);
            personService = new PersonService(mockDao);
            //System.out.println("AAA"+personService.findById(Long.valueOf(1)));

            assertEquals("Checking if found",  p, personService.findById(Long.valueOf(1)));

        }

        @Test
        public void test_checkExists_False(){
            mockDao = mock(PersonDao.class);
            //doNothing().when(mockDao).getCurrentSession();
            //doReturn(person).when(mockDao).persist(any());
            when(mockDao.findById(any())).thenReturn(null);
            personService = new PersonService(mockDao);
            //System.out.println("AAA"+personService.findById(Long.valueOf(1)));

            assertFalse("Checking if found", personService.checkIfExists(Long.valueOf(1)));
        }

        @Test
        public void test_checkExists_True(){
            Person p = new Person();
            p.setPersonId(Long.valueOf(1));
            Name name = new Name();
            name.setFirstName("John");
            name.setLastName("Connor");
            p.setName(name);
            System.out.println(p);
            mockDao = mock(PersonDao.class);
            //doNothing().when(mockDao).getCurrentSession();
            //doReturn(person).when(mockDao).persist(any());
            when(mockDao.findById(any())).thenReturn(p);
            personService = new PersonService(mockDao);
            //System.out.println("AAA"+personService.findById(Long.valueOf(1)));

            assertTrue("Checking if exists should be true", personService.checkIfExists(Long.valueOf(1)));
        }

        @Test
        public void test_UpdateShould_Update(){
            person = new Person();
            mockDao = mock(PersonDao.class);
            doNothing().when(mockDao).update(any());
            personService = new PersonService(mockDao);

            assertEquals("Checking if update succesful",  "\n\t !!! Update Successful !!!", personService.update(person));

        }

        @Test(expected = Exception.class)
        public void test_Update_Fail(){
            person = new Person();
            mockDao = mock(PersonDao.class);
            doThrow(new Exception()).when(mockDao).update(any());
            personService = new PersonService(mockDao);

            assertEquals("Checking if update fails", "\n\t !-- Update Failed --!", personService.update(person));
        }

        @Test
        public void test_RemoveRole_Removal_Success(){
            Set<Role> roles = new HashSet<Role>();
            Role role1 = new Role();
            Role role2 = new Role();
            Role role3 = new Role();

            role1.setRole("Developer");
            role2.setRole("Quality Assurance");
            role3.setRole("Manager");

            roles.add(role1);
            roles.add(role2);
            roles.add(role3);

            assertEquals("Checking if set size is 3", 3, roles.size());
            assertTrue("Checking if set contains Manager", roles.contains(role3));
            personService = new PersonService();
            roles = personService.removeRole(roles, "Manager");
            assertEquals("Checking if set size is 2", 2, roles.size());
            assertFalse("Checking if set does not contain Manager", roles.contains(role3));
        }

        @Test
        public void test_RemoveRole_Removal_Fail(){
            Set<Role> roles = new HashSet<Role>();
            Role role1 = new Role();
            Role role2 = new Role();
            Role role3 = new Role();

            role1.setRole("Developer");
            role2.setRole("Quality Assurance");
            role3.setRole("Manager");

            roles.add(role1);
            roles.add(role2);
            roles.add(role3);

            assertEquals("Checking if set size is 3", 3, roles.size());
            assertTrue("Checking if set contains Manager", roles.contains(role3));
            personService = new PersonService();
            roles = personService.removeRole(roles, "Manger");
            assertEquals("Checking if set size is 3", 3, roles.size());
            assertTrue("Checking if set still contain Manager", roles.contains(role3));
        }

        @Test
        public void test_FindAll(){
            List<Person> persons = new ArrayList<Person>();

            Person p1 = new Person();
            Person p2 = new Person();
            Person p3 = new Person();
            persons.add(p1);
            persons.add(p2);
            persons.add(p3);

            mockDao = mock(PersonDao.class);
            when(mockDao.findAll()).thenReturn(persons);
            personService = new PersonService(mockDao);

            assertEquals("Checking if set size is 3", 3, personService.findAll().size());
            //assertTrue("Checking if set is same", roles.contains(role3));
        }

        @Test
        public void test_FindAllOrderBy(){
            List<Person> persons = new ArrayList<Person>();

            Person p1 = new Person();
            Person p2 = new Person();
            Person p3 = new Person();
            persons.add(p1);
            persons.add(p2);
            persons.add(p3);

            mockDao = mock(PersonDao.class);
            when(mockDao.findAllOrderBy(any(),any())).thenReturn(persons);
            personService = new PersonService(mockDao);

            assertEquals("Checking if set size is 3", 3, personService.findOrderBy("Role",1).size());
            assertEquals("Checking if set size is 3", 3, personService.findOrderBy("Role",2).size());
            //assertTrue("Checking if set is same", roles.contains(role3));
        }

        @Test
        public void test_FindPersonRoles(){
            List<Role> roles = new ArrayList<Role>();

            Role role = new Role();
            roles.add(role);

            mockDao = mock(PersonDao.class);
            when(mockDao.findPersonRoles(any())).thenReturn(roles);
            personService = new PersonService(mockDao);

            assertEquals("Checking if set size is 1", 1, personService.findRoles(Long.valueOf(1)).size());
            //assertTrue("Checking if set is same", roles.contains(role3));
        }

        @Test
        public void test_FindContacts(){
            List<ContactInfo> contacts = new ArrayList<ContactInfo>();

            ContactInfo contact = new ContactInfo();
            contact.setContactInfo("09176265536");
            contact.setContactType("Phone");
            contact.setContactInfoId(Long.valueOf(1));
            Person p = new Person();
            contact.setPerson(p);
            contacts.add(contact);

            mockDao = mock(PersonDao.class);
            when(mockDao.findPersonContacts(any())).thenReturn(contacts);
            personService = new PersonService(mockDao);


            assertEquals("Checking if Contact is same", contact.toString(), personService.findContacts(Long.valueOf(1)).get(0));
            //assertTrue("Checking if set is same", roles.contains(role3));
        }

        @Test
        public void test_RemoveContact_Removal_Fail(){
            Set<ContactInfo> contacts = new HashSet<ContactInfo>();
            ContactInfo contact1 = new ContactInfo();
            ContactInfo contact2 = new ContactInfo();
            ContactInfo contact3 = new ContactInfo();

            contact1.setContactInfoId(Long.valueOf(1));
            contact2.setContactInfoId(Long.valueOf(2));
            contact3.setContactInfoId(Long.valueOf(3));

            contacts.add(contact1);
            contacts.add(contact2);
            contacts.add(contact3);

            assertEquals("Checking if set size is 3", 3, contacts.size());
            assertTrue("Checking if set contains 3rd contact Info", contacts.contains(contact3));
            personService = new PersonService();
            contacts = personService.removeContact(contacts, Long.valueOf(4));
            assertEquals("Checking if set size is 3", 3, contacts.size());
            assertTrue("Checking if set still contain 3rd contact Info", contacts.contains(contact3));
        }

        @Test
        public void test_RemoveContact_Removal_Success(){
            Set<ContactInfo> contacts = new HashSet<ContactInfo>();
            ContactInfo contact1 = new ContactInfo();
            ContactInfo contact2 = new ContactInfo();
            ContactInfo contact3 = new ContactInfo();

            contact1.setContactInfoId(Long.valueOf(1));
            contact2.setContactInfoId(Long.valueOf(2));
            contact3.setContactInfoId(Long.valueOf(3));

            contacts.add(contact1);
            contacts.add(contact2);
            contacts.add(contact3);

            assertEquals("Checking if set size is 3", 3, contacts.size());
            assertTrue("Checking if set contains 3rd contact Info", contacts.contains(contact3));
            personService = new PersonService();
            contacts = personService.removeContact(contacts, Long.valueOf(3));
            assertEquals("Checking if set size is 3", 2, contacts.size());
            assertFalse("Checking if set still contain 3rd contact Info", contacts.contains(contact3));
        }

        @Test
        public void test_ContainsRole_True(){
            Set<Role> roles = new HashSet<Role>();
            Role role1 = new Role();
            role1.setRole("Developer");
            roles.add(role1);
            assertTrue("Checking if set contains Developer", personService.containsRole(roles, "Developer"));
        }

        @Test
        public void test_ContainsRole_False(){
            Set<Role> roles = new HashSet<Role>();
            Role role1 = new Role();
            role1.setRole("Developer");
            roles.add(role1);
            assertFalse("Checking if set contains Developer", personService.containsRole(roles, "Programmer"));
        }

    }
