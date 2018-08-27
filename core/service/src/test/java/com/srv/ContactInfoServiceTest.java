package com.pdomingo.service;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import com.pdomingo.dao.ContactInfoDao;
import com.pdomingo.service.*;
import com.pdomingo.model.person.*;
import com.pdomingo.model.role.Role;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ContactInfoServiceTest
    {

        protected static ContactInfoService contactService = new ContactInfoService();
        ContactInfo contact;
        ContactInfoDao mockDao;

        @Test(expected = Exception.class)
        public void test_PersistShould_NotInsert(){
            contact = new ContactInfo();
            mockDao = mock(ContactInfoDao.class);
            doThrow(new Exception()).when(mockDao).persist(any());
            contactService = new ContactInfoService(mockDao);

            assertEquals("Checking if insertion fails", "\n\t!-- Insert Failed --!\n", contactService.persist(contact));
        }

        @Test(expected = Exception.class)
        public void test_DeleteShould_NotDelete(){
            contact = new ContactInfo();
            mockDao = mock(ContactInfoDao.class);
            doThrow(new Exception()).when(mockDao).delete(any());
            when(mockDao.findById(any())).thenReturn(contact);
            contactService = new ContactInfoService(mockDao);

            assertEquals("Checking if deletion fails", "\n\t!-- Delete Failed --!\n", contactService.delete(Long.valueOf(1)));
        }

        @Test
        public void test_DeleteShould_Delete(){
            contact = new ContactInfo();
            mockDao = mock(ContactInfoDao.class);
            doNothing().when(mockDao).delete(any());
            when(mockDao.findById(any())).thenReturn(contact);
            contactService = new ContactInfoService(mockDao);

            assertEquals("Checking if deletion succeeds", "\n\t!!! Delete Sucessful !!!\n", contactService.delete(Long.valueOf(1)));
        }

        @Test(expected = Exception.class)
        public void test_DeleteAllShould_NotDelete(){
            contact = new ContactInfo();
            mockDao = mock(ContactInfoDao.class);
            doThrow(new Exception()).when(mockDao).deleteAll();

            contactService = new ContactInfoService(mockDao);

            assertEquals("Checking if deletion fails", "\n\t!-- Delete Failed --!\n", contactService.deleteAll());
        }

        @Test
        public void test_DeleteAllShould_Delete(){
            contact = new ContactInfo();
            mockDao = mock(ContactInfoDao.class);
            doNothing().when(mockDao).deleteAll();
            contactService = new ContactInfoService(mockDao);

            assertEquals("Checking if deletion succeeds", "\n\t!!! Delete Sucessful !!!\n",contactService.deleteAll());
        }

        @Test
        public void test_PersistShould_Insert(){
            contact = new ContactInfo();
            mockDao = mock(ContactInfoDao.class);
            doNothing().when(mockDao).persist(any());
            contactService = new ContactInfoService(mockDao);

            assertEquals("Checking if insertion succesful",  "\n\t!!! Insert Sucessful !!!\n", contactService.persist(contact));

        }

        @Test
        public void test_findById_Exists(){
            ContactInfo c = new ContactInfo();
            c.setContactInfoId(Long.valueOf(1));

            mockDao = mock(ContactInfoDao.class);
            //doNothing().when(mockDao).getCurrentSession();
            //doReturn(contact).when(mockDao).persist(any());
            when(mockDao.findById(any())).thenReturn(c);
            contactService = new ContactInfoService(mockDao);
            //System.out.println("AAA"+contactService.findById(Long.valueOf(1)));

            assertEquals("Checking if found",  c, contactService.findById(Long.valueOf(1)));

        }

        @Test
        public void test_checkExists_False(){
            mockDao = mock(ContactInfoDao.class);
            //doNothing().when(mockDao).getCurrentSession();
            //doReturn(contact).when(mockDao).persist(any());
            when(mockDao.findById(any())).thenReturn(null);
            contactService = new ContactInfoService(mockDao);
            //System.out.println("AAA"+contactService.findById(Long.valueOf(1)));

            assertFalse("Checking if found", contactService.checkIfExists(Long.valueOf(1)));
        }

        @Test
        public void test_checkExists_True(){
            ContactInfo c = new ContactInfo();
            c.setContactInfoId(Long.valueOf(1));

            mockDao = mock(ContactInfoDao.class);
            //doNothing().when(mockDao).getCurrentSession();
            //doReturn(contact).when(mockDao).persist(any());
            when(mockDao.findById(any())).thenReturn(c);
            contactService = new ContactInfoService(mockDao);
            //System.out.println("AAA"+contactService.findById(Long.valueOf(1)));

            assertTrue("Checking if exists should be true", contactService.checkIfExists(Long.valueOf(1)));
        }

        @Test
        public void test_UpdateShould_Update(){
            ContactInfo contact = new ContactInfo();
            contact.setContactInfo("09176265536");
            contact.setContactType("Phone");
            contact.setContactInfoId(Long.valueOf(1));
            Person p = new Person();
            contact.setPerson(p);


            mockDao = mock(ContactInfoDao.class);
            doNothing().when(mockDao).update(any());
            when(mockDao.findById(any())).thenReturn(contact);
            contactService = new ContactInfoService(mockDao);

            assertEquals("Checking if update succesful", "\n\t!!! Update Sucessful !!!\n", contactService.update(contact));

        }

        @Test(expected = Exception.class)
        public void test_Update_Fail(){
            ContactInfo contact = new ContactInfo();
            contact.setContactInfo("09176265536");
            contact.setContactType("Phone");
            contact.setContactInfoId(Long.valueOf(1));
            Person p = new Person();
            contact.setPerson(p);


            mockDao = mock(ContactInfoDao.class);
            doThrow(new Exception()).when(mockDao).update(any());
            when(mockDao.findById(any())).thenReturn(contact);
            contactService = new ContactInfoService(mockDao);

            assertEquals("Checking if update fails", "\n\t!-- Update Failed --!\n", contactService.update(contact));
        }


        @Test
        public void test_FindAll(){
            List<ContactInfo> contacts = new ArrayList<ContactInfo>();

            ContactInfo c1 = new ContactInfo();
            ContactInfo c2 = new ContactInfo();
            ContactInfo c3 = new ContactInfo();
            contacts.add(c1);
            contacts.add(c2);
            contacts.add(c3);

            mockDao = mock(ContactInfoDao.class);
            when(mockDao.findAll()).thenReturn(contacts);
            contactService = new ContactInfoService(mockDao);

            assertEquals("Checking if set size is 3", 3, contactService.findAll().size());
            //assertTrue("Checking if set is same", roles.contains(role3));
        }



    }
