package com.pdomingo.service;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import com.pdomingo.dao.DaoParent;
import com.pdomingo.service.*;
import com.pdomingo.model.person.*;
import com.pdomingo.model.role.Role;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RoleServiceTest
    {


        protected static RoleService roleService = new RoleService();
        Role role;
        DaoParent mockDao;

        @Test(expected = Exception.class)
        public void test_PersistShould_NotInsert(){
            role = new Role();
            mockDao = mock(DaoParent.class);
            doThrow(new Exception()).when(mockDao).persist(any(Role.class));
            roleService = new RoleService(mockDao);

            assertEquals("Checking if insertion fails", "\n\t!-- Failed to add role --!\n", roleService.persist(role));
        }



        @Test
        public void test_PersistShould_Insert(){
            role = new Role();
            mockDao = mock(DaoParent.class);
            doNothing().when(mockDao).persist(any(Role.class));
            roleService = new RoleService(mockDao);

            assertEquals("Checking if insertion succesful",  "\n\t!!! Role added! !!!\n", roleService.persist(role));

        }

        @Test(expected = Exception.class)
        public void test_DeleteShould_NotDelete(){
            role = new Role();
            mockDao = mock(DaoParent.class);
            doThrow(new Exception()).when(mockDao).delete(any());
            when(mockDao.findById(any(), any())).thenReturn(role);
            roleService = new RoleService(mockDao);

            assertEquals("Checking if deletion fails", "\n\t!-- Delete Failed --!\n", roleService.delete(Long.valueOf(1)));
        }

        @Test
        public void test_DeleteShould_Delete(){
            role = new Role();
            mockDao = mock(DaoParent.class);
            doNothing().when(mockDao).delete(any());
            when(mockDao.findById(any(), any())).thenReturn(role);
            roleService = new RoleService(mockDao);

            assertEquals("Checking if deletion succeeds", "\n\t!!! Role deleted !!!\n", roleService.delete(Long.valueOf(1)));
        }

        @Test(expected = Exception.class)
        public void test_DeleteAllShould_NotDelete(){
            role = new Role();
            mockDao = mock(DaoParent.class);
            doThrow(new Exception()).when(mockDao).deleteAll(any());

            roleService = new RoleService(mockDao);

            assertEquals("Checking if deletion fails", "\n\t!-- Another Person is still assigned to this Role --!\n", roleService.deleteAll());
        }

        @Test
        public void test_DeleteAllShould_Delete(){
            role = new Role();
            mockDao = mock(DaoParent.class);
            doNothing().when(mockDao).deleteAll(any());
            roleService = new RoleService(mockDao);

            assertEquals("Checking if deletion succeeds", "\n\t!!! Role deleted !!!\n",roleService.deleteAll());
        }


        @Test
        public void test_findById_Exists(){
            Role r = new Role();
            r.setRoleId(Long.valueOf(1));

            mockDao = mock(DaoParent.class);
            //doNothing().when(mockDao).getCurrentSession();
            //doReturn(role).when(mockDao).persist(any());
            when(mockDao.findById(any(), any())).thenReturn(r);
            roleService = new RoleService(mockDao);
            //System.out.println("AAA"+roleService.findById(Long.valueOf(1)));

            assertEquals("Checking if found",  r, roleService.findById(Long.valueOf(1)));

        }

        @Test
        public void test_checkExists_False(){
            mockDao = mock(DaoParent.class);
            //doNothing().when(mockDao).getCurrentSession();
            //doReturn(role).when(mockDao).persist(any());
            when(mockDao.findById(any(), any())).thenReturn(null);
            roleService = new RoleService(mockDao);
            //System.out.println("AAA"+roleService.findById(Long.valueOf(1)));

            assertFalse("Checking if found", roleService.checkIfExists(Long.valueOf(1)));
        }

        @Test
        public void test_checkExists_True(){
            Role r = new Role();
            r.setRoleId(Long.valueOf(1));

            mockDao = mock(DaoParent.class);
            when(mockDao.findById(any(), any())).thenReturn(r);
            roleService = new RoleService(mockDao);

            assertTrue("Checking if exists should be true", roleService.checkIfExists(Long.valueOf(1)));
        }

        @Test
        public void test_UpdateShould_Update(){
            Role role = new Role();
            role.setRole("Boy");
            role.setRoleId(Long.valueOf(1));

            mockDao = mock(DaoParent.class);
            doNothing().when(mockDao).update(any());
            when(mockDao.findById(any(), any())).thenReturn(role);
            roleService = new RoleService(mockDao);

            assertEquals("Checking if update succesful", "\n\t!!! Role updated! !!!\n", roleService.update(role));

        }

        @Test
        public void test_UpdateShould_NotExist(){
            Role role = new Role();
            role.setRole("Boy");
            role.setRoleId(Long.valueOf(1));

            mockDao = mock(DaoParent.class);
            doNothing().when(mockDao).update(any());
            when(mockDao.findById(any(), any())).thenReturn(null);
            roleService = new RoleService(mockDao);

            assertEquals("Checking if update succesful", "\n\t!-- Role does not exist --!\n", roleService.update(role));

        }

        @Test
        public void test_DeleteShould_NotExist(){
            Role role = new Role();
            role.setRole("Boy");
            role.setRoleId(Long.valueOf(1));

            mockDao = mock(DaoParent.class);
            doNothing().when(mockDao).update(any());
            when(mockDao.findById(any(), any())).thenReturn(null);
            roleService = new RoleService(mockDao);

            assertEquals("Checking if update succesful", "\n\t!-- Role does not exist --!\n", roleService.delete(Long.valueOf(1)));

        }

        @Test(expected = Exception.class)
        public void test_Update_Fail(){
            Role role = new Role();
            role.setRole("Boy");
            role.setRoleId(Long.valueOf(1));


            mockDao = mock(DaoParent.class);
            doThrow(new Exception()).when(mockDao).update(any());
            when(mockDao.findById(any(), any())).thenReturn(role);
            roleService = new RoleService(mockDao);

            assertEquals("Checking if update fails", "\n\t!-- Update Failed --!\n", roleService.update(role));
        }


        @Test
        public void test_FindAll(){
            List<Role> roles = new ArrayList<Role>();

            Role c1 = new Role();
            Role c2 = new Role();
            Role c3 = new Role();
            roles.add(c1);
            roles.add(c2);
            roles.add(c3);

            mockDao = mock(DaoParent.class);
            when(mockDao.findAll(any())).thenReturn(roles);
            roleService = new RoleService(mockDao);

            assertEquals("Checking if set size is 3", 3, roleService.findAll().size());
            //assertTrue("Checking if set is same", roles.contains(role3));
        }

        @Test
        public void test_checkIfUniqueSuccess(){
            //List<Role> roles = new ArrayList<Role>();
            Role role = new Role();
            role.setRole("Mechanic");
            role.setRoleId(Long.valueOf(1));

            mockDao = mock(DaoParent.class);
            when(mockDao.findByRoleName(any())).thenReturn(role);
            roleService = new RoleService(mockDao);

            assertEquals("Checking if mechanic exists", role, roleService.checkIfUnique("Mechanic"));
            //assertTrue("Checking if set is same", roles.contains(role3));
        }

        @Test
        public void test_checkIfUniqueFail(){
            //List<Role> roles = new ArrayList<Role>();
            Role role = new Role();
            role.setRole("Mechanic");
            role.setRoleId(Long.valueOf(1));

            mockDao = mock(DaoParent.class);
            when(mockDao.findByRoleName(any())).thenReturn(null);
            roleService = new RoleService(mockDao);

            assertEquals("Checking if mechanic does not exist", "Mechanic", roleService.checkIfUnique("Mechanic").getRole());
            //assertTrue("Checking if set is same", roles.contains(role3));
        }

        private Object actuallyT;

        public <T> List<T> magicalListGetter(Class<T> klazz) {
            List<T> list = new ArrayList<>();
            list.add(klazz.cast(actuallyT));

            try {
                list.add(klazz.getConstructor().newInstance()); // If default constructor
                list.add(klazz.getConstructor().newInstance());
            } catch(Exception e){

            }

            return list;
        }


    }
