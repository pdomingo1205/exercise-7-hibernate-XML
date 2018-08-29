package com.pdomingo.service;

import java.util.List;
import java.util.Set;

import com.pdomingo.model.role.Role;
import com.pdomingo.dao.*;
import org.hibernate.exception.ConstraintViolationException;

public class RoleService {

	private static DaoParent<Role,Long> dao;

	public RoleService() {
		dao = new DaoParent();
	}
	public RoleService(DaoParent newDao) {
		dao = newDao;
	}

	public String persist(Role entity) {
		String textToReturn ="";

		try{
			dao.openCurrentSessionwithTransaction();
			dao.persist(entity);
			textToReturn =("\n\t!!! Role added! !!!\n");
			dao.closeCurrentSessionwithTransaction();

		}catch(Exception e){
			textToReturn = "\n\t!-- Failed to add role --!\n";
		}

		return textToReturn;
	}


	public String update(Role entity) {
		String textToReturn;

			if(checkIfExists(entity.getRoleId())){
				dao.openCurrentSessionwithTransaction();
				dao.update(entity);
				textToReturn = ("\n\t!!! Role updated! !!!\n");
				dao.closeCurrentSessionwithTransaction();
			}
			else{
				textToReturn = "\n\t!-- Role does not exist --!\n";
			}


		return textToReturn;
	}

	public Boolean checkIfExists(Long Id){
		Boolean exists = true;
		dao.openCurrentSession();
		Role role = dao.findById(Id, Role.class);
		dao.closeCurrentSession();

		if(role == null){
			exists = false;
		}

		return exists;
	}

	public Role findById(Long id) {
		dao.openCurrentSession();
		Role role = dao.findById(id, Role.class);
		dao.closeCurrentSession();

		return role;
	}

	public Role checkIfUnique(String roleText){
		Role role = new Role();
		try{
			dao.openCurrentSession();
			role = dao.findByRoleName(roleText);

			if (role == null) {
				role = new Role();
				role.setRole(roleText);
			}
			dao.closeCurrentSession();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return role;
	}

	public String delete(Long id) {
		String textToReturn;

		try{
			if(checkIfExists(id)){
				dao.openCurrentSessionwithTransaction();
				Role role = dao.findById(id, Role.class);
				dao.delete(role);
				textToReturn = "\n\t!!! Role deleted !!!\n";
				dao.closeCurrentSessionwithTransaction();
			}
			else{
				textToReturn = "\n\t!-- Role does not exist --!\n";
			}
		}catch(ConstraintViolationException pse){
			textToReturn = "\n\t!-- Another Person is still assigned to this Role --!\n";
		}

		return textToReturn;
	}

	public List<Role> findAll() {
		dao.openCurrentSession();
		List<Role> roles = dao.findAll(Role.class);
		dao.closeCurrentSession();
		return roles;
	}

	public String deleteAll() {

		String textToReturn;

		try{
				dao.openCurrentSessionwithTransaction();
				dao.deleteAll(Role.class);
				dao.closeCurrentSessionwithTransaction();
				textToReturn = "\n\t!!! Role deleted !!!\n";
		}catch(ConstraintViolationException pse){
			textToReturn = "\n\t!-- Another Person is still assigned to this Role --!\n";
		}

		return textToReturn;
	}

	public DaoParent getDao() {
		return dao;
	}
}
