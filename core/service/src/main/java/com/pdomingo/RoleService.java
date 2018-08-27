package com.pdomingo.service;

import java.util.List;
import java.util.Set;

import com.pdomingo.model.role.Role;
import com.pdomingo.dao.RoleDao;
import org.hibernate.exception.ConstraintViolationException;

public class RoleService {

	private static RoleDao roleDao;

	public RoleService() {
		roleDao = new RoleDao();
	}
	public RoleService(RoleDao newDao) {
		roleDao = newDao;
	}

	public String persist(Role entity) {
		String textToReturn ="";

		try{
			roleDao.openCurrentSessionwithTransaction();
			roleDao.persist(entity);
			textToReturn =("\n\t!!! Role added! !!!\n");
			roleDao.closeCurrentSessionwithTransaction();

		}catch(Exception e){
			textToReturn = "\n\t!-- Failed to add role --!\n";
		}

		return textToReturn;
	}

	public String persistSet(Set<Role> entity) {
		String textToReturn ="";

		try{
			roleDao.openCurrentSessionwithTransaction();
			roleDao.persistSet(entity);
			roleDao.closeCurrentSessionwithTransaction();
			textToReturn =("\n\t!!! Role added! !!!\n");

		}catch(Exception e){
			textToReturn = "\n\t!-- Failed to add role --!\n";
		}

		return textToReturn;

	}

	public String update(Role entity) {
		String textToReturn;

		try{

			if(checkIfExists(entity.getRoleId())){
				roleDao.openCurrentSessionwithTransaction();
				roleDao.update(entity);
				textToReturn = ("\n\t!!! Role updated! !!!\n");
				roleDao.closeCurrentSessionwithTransaction();
			}
			else{
				textToReturn = "\n\t!-- Role does not exist --!\n";
			}

		}catch(Exception e){
			textToReturn = "\n\t!-- Failed to update Role --!\n";
		}

		return textToReturn;
	}

	public Boolean checkIfExists(Long Id){
		Boolean exists = true;

		roleDao.openCurrentSession();
		Role role = roleDao.findById(Id);
		roleDao.closeCurrentSession();

		if(role == null){
			exists = false;
		}

		return exists;
	}

	public Role findById(Long id) {
		roleDao.openCurrentSession();
		Role role = roleDao.findById(id);
		roleDao.closeCurrentSession();

		return role;
	}

	public Role checkIfUnique(String roleText){
		Role role = new Role();
		try{
			roleDao.openCurrentSession();
			role = roleDao.findByRoleName(roleText);

			if (role == null) {
				role = new Role();
				role.setRole(roleText);
			}
			roleDao.closeCurrentSession();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return role;
	}

	public String delete(Long id) {
		String textToReturn;

		try{
			if(checkIfExists(id)){
				roleDao.openCurrentSessionwithTransaction();
				Role role = roleDao.findById(id);
				roleDao.delete(role);
				textToReturn = "\n\t!!! Role deleted !!!\n";
				roleDao.closeCurrentSessionwithTransaction();
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
		roleDao.openCurrentSession();
		List<Role> roles = roleDao.findAll();
		roleDao.closeCurrentSession();
		return roles;
	}

	public String deleteAll() {

		String textToReturn;

		try{
				roleDao.openCurrentSessionwithTransaction();
				roleDao.deleteAll();
				roleDao.closeCurrentSessionwithTransaction();
				textToReturn = "\n\t!!! Role deleted !!!\n";
		}catch(ConstraintViolationException pse){
			textToReturn = "\n\t!-- Another Person is still assigned to this Role --!\n";
		}

		return textToReturn;
	}

	public RoleDao roleDao() {
		return roleDao;
	}
}
