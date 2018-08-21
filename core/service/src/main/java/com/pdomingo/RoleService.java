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
		roleDao.closeCurrentSessionwithTransaction();
	}

	public Role findById(Long id) {
		roleDao.openCurrentSession();
		Role role = roleDao.findById(id);
		roleDao.closeCurrentSession();
		return role;
	}

	public Role checkIfUnique(String roleText){

		return roleDao.findByRoleName(roleText);
	}

	public void delete(Long id) {
		roleDao.openCurrentSessionwithTransaction();
		Role role = roleDao.findById(id);
		roleDao.delete(role);
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
