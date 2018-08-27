package com.pdomingo.service;

import java.util.List;

import com.pdomingo.model.person.ContactInfo;
import com.pdomingo.dao.ContactInfoDao;

public class ContactInfoService {

	private static ContactInfoDao contactInfoDao;

	public ContactInfoService() {
		contactInfoDao = new ContactInfoDao();
	}

	public ContactInfoService(ContactInfoDao newDao) {
		contactInfoDao = newDao;
	}

	public String persist(ContactInfo entity) {

		String textToReturn;
		try{
			contactInfoDao.openCurrentSessionwithTransaction();
			contactInfoDao.persist(entity);
			contactInfoDao.closeCurrentSessionwithTransaction();
				textToReturn = "\n\t!!! Insert Sucessful !!!\n";
		}catch(Exception e){
			textToReturn = "\n\t!-- Insert Failed --!\n";
		}
		return textToReturn;
	}

	public String update(ContactInfo entity) {
		String textToReturn;
		try{
				contactInfoDao.openCurrentSessionwithTransaction();
				ContactInfo persistentInfo = contactInfoDao.findById(entity.getContactInfoId());
				persistentInfo.setContactInfo(entity.getContactInfo());
				persistentInfo.setContactType(entity.getContactType());
				contactInfoDao.update(persistentInfo);
				contactInfoDao.closeCurrentSessionwithTransaction();
				textToReturn = "\n\t!!! Update Sucessful !!!\n";
		}catch(Exception e){
			textToReturn = "\n\t!-- Update Failed --!\n";
		}
		return textToReturn;
	}

	public Boolean checkIfExists(Long Id){
		Boolean exists = true;

		contactInfoDao.openCurrentSession();
		ContactInfo contact = contactInfoDao.findById(Id);
		contactInfoDao.closeCurrentSession();

		if(contact == null){
			exists = false;
		}

		return exists;
	}

	public ContactInfo findById(Long id) {

		contactInfoDao.openCurrentSession();
		ContactInfo contactInfo = contactInfoDao.findById(id);
		contactInfoDao.closeCurrentSession();
		return contactInfo;
	}

	public String delete(Long id) {

		String textToReturn;
		try{
				contactInfoDao.openCurrentSessionwithTransaction();
				contactInfoDao.deleteAll();
				contactInfoDao.closeCurrentSessionwithTransaction();
				textToReturn = "\n\t!!! Delete Sucessful !!!\n";
		}catch(Exception e){
			textToReturn = "\n\t!-- Delete Failed --!\n";
		}
		return textToReturn;
	}

	public List<ContactInfo> findAll() {
		contactInfoDao.openCurrentSession();
		List<ContactInfo> contactInfos = contactInfoDao.findAll();
		contactInfoDao.closeCurrentSession();
		return contactInfos;
	}

	public String deleteAll() {
		String textToReturn;
		try{
				contactInfoDao.openCurrentSessionwithTransaction();
				contactInfoDao.deleteAll();
				contactInfoDao.closeCurrentSessionwithTransaction();
				textToReturn = "\n\t!!! Delete Sucessful !!!\n";
		}catch(Exception e){
			textToReturn = "\n\t!-- Delete Failed --!\n";
		}
		return textToReturn;
	}

	public ContactInfoDao contactInfoDao() {
		return contactInfoDao;
	}
}
