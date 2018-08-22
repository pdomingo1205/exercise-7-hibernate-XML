package com.pdomingo.service;

import java.util.List;
import java.util.Set;

import com.pdomingo.model.role.Role;
import com.pdomingo.dao.RoleDao;

public class RoleService {

	private static RoleDao roleDao;

	public RoleService() {
		roleDao = new RoleDao();
	}

	public void persist(Role entity) {
		roleDao.openCurrentSessionwithTransaction();
		roleDao.persist(entity);
		System.out.println("\n\t Role added! \n");
		roleDao.closeCurrentSessionwithTransaction();
	}

	public void persistSet(Set<Role> entity) {
		roleDao.openCurrentSessionwithTransaction();
		roleDao.persist(entity);
		roleDao.closeCurrentSessionwithTransaction();
	}

	public void update(Role entity) {

		roleDao.openCurrentSessionwithTransaction();
		roleDao.update(entity);
		System.out.println("\n\t Role updated! \n");
		roleDao.closeCurrentSessionwithTransaction();
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

		}

		return role;
	}

	public void delete(Long id) {


		if(checkIfExists(id)){
			roleDao.openCurrentSessionwithTransaction();
			Role role = roleDao.findById(id);
			roleDao.delete(role);
			System.out.println("\n\t Role deleted \n");
		}
		else{
			System.out.println("\n\t Role does not exist \n");
		}

		roleDao.closeCurrentSessionwithTransaction();
	}

	public List<Role> findAll() {
		roleDao.openCurrentSession();
		List<Role> roles = roleDao.findAll();
		roleDao.closeCurrentSession();
		return roles;
	}

	public void deleteAll() {
		roleDao.openCurrentSessionwithTransaction();
		roleDao.deleteAll();
		roleDao.closeCurrentSessionwithTransaction();
	}

	public RoleDao roleDao() {
		return roleDao;
	}
}
