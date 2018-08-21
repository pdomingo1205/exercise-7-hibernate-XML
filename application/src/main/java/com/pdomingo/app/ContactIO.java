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


	public ContactIO(){

	}

	public void chooseOperation(){

		System.out.println("\n \t--- Choose Operation --- \n");
		System.out.println("\n \t1. Create Contact");
		System.out.println("\n \t2. Read Contact");
		System.out.println("\n \t3. Update Contact");
		System.out.println("\n \t4. Delete Contact");
		System.out.println("\n \t5. List Contacts");
		System.out.println("\n \t7. Exit");

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
                    contactInfo = InputValidation.Validate.getMatchedNumber("^[09][0-9]{10}$");
                    break;
            case 2:
                    contactInfo = InputValidation.Validate.getMatchedNumber("^[09][0-9]{8}$");
                    break;
            case 3:
                    contactInfo = InputValidation.Validate.getEmailAddress();
                    break;
            default:
                    break;
        }

        return contactInfo;
    }



	public ContactInfo createContact(){
		ContactInfo contact = new ContactInfo();

        Integer intContactType = askContactType();

        contact.setContactType(contactTypeToString(intContactType));
        contact.setContactInfo(askContactInfo(intContactType));
        return contact;
	}

	public void readContact(){

	}

	public void updateContact(){

	}

	public void deleteContact(){

	}

	public void listContacts(){

	}

}
