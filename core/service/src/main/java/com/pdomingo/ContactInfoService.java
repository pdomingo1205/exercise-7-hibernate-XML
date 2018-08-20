package com.pdomingo.service;

import java.util.List;

import com.pdomingo.model.person.ContactInfo;
import com.pdomingo.dao.ContactInfoDao;

public class ContactInfoService {

	private static ContactInfoDao contactInfoDao;

	public ContactInfoService() {
		contactInfoDao = new ContactInfoDao();
	}

	public void persist(ContactInfo entity) {
		contactInfoDao.openCurrentSessionwithTransaction();
		contactInfoDao.persist(entity);
		contactInfoDao.closeCurrentSessionwithTransaction();
	}

	public void update(ContactInfo entity) {
		contactInfoDao.openCurrentSessionwithTransaction();
		contactInfoDao.update(entity);
		contactInfoDao.closeCurrentSessionwithTransaction();
	}

	public ContactInfo findById(Long id) {
		contactInfoDao.openCurrentSession();
		ContactInfo contactInfo = contactInfoDao.findById(id);
		contactInfoDao.closeCurrentSession();
		return contactInfo;
	}

	public void delete(Long id) {
		contactInfoDao.openCurrentSessionwithTransaction();
		ContactInfo contactInfo = contactInfoDao.findById(id);
		contactInfoDao.delete(contactInfo);
		contactInfoDao.closeCurrentSessionwithTransaction();
	}

	public List<ContactInfo> findAll() {
		contactInfoDao.openCurrentSession();
		List<ContactInfo> contactInfos = contactInfoDao.findAll();
		contactInfoDao.closeCurrentSession();
		return contactInfos;
	}

	public void deleteAll() {
		contactInfoDao.openCurrentSessionwithTransaction();
		contactInfoDao.deleteAll();
		contactInfoDao.closeCurrentSessionwithTransaction();
	}

	public ContactInfoDao contactInfoDao() {
		return contactInfoDao;
	}
}
