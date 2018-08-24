package com.pdomingo.app;

import java.io.IOException;
import java.util.*;
import java.text.*;

import org.apache.commons.lang3.StringUtils;
import com.pdomingo.model.role.*;
import com.pdomingo.model.person.*;
import com.pdomingo.service.*;

import static com.pdomingo.service.InputValidation.Validate.*;



public class ContactIO {

	ContactInfoService contactInfoService = new ContactInfoService();
	public ContactIO(){

	}

	public Integer chooseOperation(){

		System.out.println("\n \t--- Choose Operation --- \n");
		System.out.println("\n \t1. Update Contact");
		System.out.println("\n \t2. Delete Contact");
		System.out.println("\n \t3. List Contacts");
		System.out.println("\n \t4. Exit");

		return InputValidation.Validate.getIntegerInRange(1,4);
	}

	public void doChooseOperation(Integer choice){

		switch(choice){
			case 1:
				updateContact();
				break;
			case 2:
				deleteContact();
				break;
			case 3:
				listContacts();
				break;
			case 4:
				break;
		}

	}

	private Integer askContactType(){

		System.out.println("\n \t--- Choose Contact Type --- \n");
        System.out.println("\n \t1. Phone");
        System.out.println("\n \t2. Landline");
        System.out.println("\n \t3. Email Address");

		Integer choice = InputValidation.Validate.getIntegerInRange(1,3);

		return choice;
	}

    private String contactTypeToString(Integer intContactType){
        String contactType = "";

        switch (intContactType.intValue()){
            case 1: contactType = "Phone";
            break;
            case 2: contactType = "Landline";
            break;
            case 3: contactType = "Email Address";
            break;
            default:
            break;
        }

        return contactType;
    }

    private String askContactInfo(Integer intContactType){
        String contactInfo = "";
        switch (intContactType.intValue()){

            case 1:
					System.out.println("Input phone number ex.09176365546");
                    contactInfo = InputValidation.Validate.getMatchedNumber("^[09][0-9]{10}$");
                    break;
            case 2:
					System.out.println("Input landline number ex.3514848");
                    contactInfo = InputValidation.Validate.getMatchedNumber("^[0-9]{7}$");
                    break;
            case 3:
					System.out.println("Input email address ex.catlassmcarthur@yahoo.com");
                    contactInfo = InputValidation.Validate.getEmailAddress();
                    break;
            default:
                    break;
        }

        return contactInfo;
    }


	public ContactInfo editContact(ContactInfo contact){
		contact = new ContactInfo();
        Integer intContactType = askContactType();

        contact.setContactType(contactTypeToString(intContactType));
        contact.setContactInfo(askContactInfo(intContactType));
        return contact;
	}

	public void createContact(){
		ContactInfo contact = new ContactInfo();
		Integer intContactType = askContactType();

		contact.setContactType(contactTypeToString(intContactType));
        contact.setContactInfo(askContactInfo(intContactType));

		contactInfoService.persist(contact);
	}

	public void readContact(){
		System.out.println("\n\t Input ID of contact to read *Use List to find ID's \n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		ContactInfo contact;

		if(contactInfoService.checkIfExists(inputId)){
			contact = contactInfoService.findById(inputId);

			System.out.println(contact.getContactType());
		}
		else{
			System.out.println("\n\t Contact does not exist \n");
		}
	}

	public void updateContact(){

		System.out.println("\n\t Input ID of Contact Info to update *Use List to find ID's \n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		ContactInfo contactInfo;

		if(contactInfoService.checkIfExists(inputId)){
			contactInfo = contactInfoService.findById(inputId);
			System.out.println("\n\n\n"+contactInfo.getContactInfoId());
			ContactInfo contact = editContact(contactInfo);
			contact.setContactInfoId(inputId);
			try{
				System.out.println(contact);

			}catch(Exception e){

			}
			contactInfoService.update(contact);

		}
		else{
			System.out.println("\n\t Contact Info does not exist \n");
		}
	}

	public void deleteContact(){
		System.out.println("\n\t Input ID of Contact Info to delete *Use List to find ID's \n");
		Long inputId = Long.valueOf(InputValidation.Validate.getInteger());
		ContactInfo contactInfo;

		if(contactInfoService.checkIfExists(inputId)){

			contactInfoService.delete(inputId);

		}
		else{
			System.out.println("\n\t Contact Info does not exist \n");
		}
	}

	public void listContacts(){
		for (ContactInfo c : contactInfoService.findAll()) {
			System.out.println(c);
		}
	}

	public void start(){
		Integer choice;
		do{
			choice = chooseOperation();
			doChooseOperation(choice);
		}while(choice != 4);
	}

}
