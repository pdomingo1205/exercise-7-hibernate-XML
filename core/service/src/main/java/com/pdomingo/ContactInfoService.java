package com.pdomingo.service;

import java.util.List;

import com.pdomingo.model.person.ContactInfo;
import com.pdomingo.dao.DaoParent;

public class ContactInfoService {

	private DaoParent<ContactInfo,Long>dao;

	public ContactInfoService() {
		dao = new DaoParent();
	}

	public ContactInfoService(DaoParent newDao) {
		dao = newDao;
	}


	public String update(ContactInfo entity) {
		String textToReturn;
		try{
				dao.openCurrentSessionwithTransaction();
				ContactInfo persistentInfo = (ContactInfo) dao.findById(entity.getContactInfoId(), ContactInfo.class);
				persistentInfo.setContactInfo(entity.getContactInfo());
				persistentInfo.setContactType(entity.getContactType());
				dao.update(persistentInfo);
				dao.closeCurrentSessionwithTransaction();
				textToReturn = "\n\t!!! Update Sucessful !!!\n";
		}catch(Exception e){
			textToReturn = "\n\t!-- Update Failed --!\n";
		}
		return textToReturn;
	}

	public Boolean checkIfExists(Long Id){
		Boolean exists = true;

		dao.openCurrentSession();
		ContactInfo contact = (ContactInfo) dao.findById(Id, ContactInfo.class);
		dao.closeCurrentSession();

		if(contact == null){
			exists = false;
		}

		return exists;
	}

	public ContactInfo findById(Long id) {

		dao.openCurrentSession();
		ContactInfo contactInfo = (ContactInfo) dao.findById(id, ContactInfo.class);
		dao.closeCurrentSession();
		return contactInfo;
	}

	public String delete(Long id) {

		String textToReturn;
		try{
				dao.openCurrentSessionwithTransaction();
				dao.delete(findById(id));
				dao.closeCurrentSessionwithTransaction();
				textToReturn = "\n\t!!! Delete Sucessful !!!\n";
		}catch(Exception e){
			textToReturn = "\n\t!-- Delete Failed --!\n";
		}
		return textToReturn;
	}

	public List<ContactInfo> findAll() {
		dao.openCurrentSession();
		List<ContactInfo> contactInfos = dao.findAll(ContactInfo.class);
		dao.closeCurrentSession();
		return contactInfos;
	}


	public DaoParent dao() {
		return dao;
	}
}
