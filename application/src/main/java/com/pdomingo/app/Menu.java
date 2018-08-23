package com.pdomingo.app;

import java.io.IOException;
import java.util.*;

import com.pdomingo.model.role.*;
import com.pdomingo.model.person.*;
import com.pdomingo.service.*;

public class Menu {
	Scanner scan = new Scanner(System.in);
	PersonIO personIO = new PersonIO();
	ContactIO contactIO = new ContactIO();
	RoleIO roleIO = new RoleIO();

	public Menu(){

	}

	public Menu(Scanner newScanner){
		scan = newScanner;
	}

	public Integer chooseCategory(){

		System.out.println("\n \t--- Choose Category --- \n");
		System.out.println("\n \t1. Person");
		System.out.println("\n \t2. Role");
		System.out.println("\n \t3. Contact Info");
		System.out.println("\n \t4. Exit");

		return InputValidation.Validate.getIntegerInRange(1,4);
	}

	public void doChooseCategory(Integer choice){

		switch(choice){
			case 1:
				personIO.start();
				break;
			case 2:
				//roleIO.start();
				break;
			case 3:
				//contactIO.start();
				break;
			default:
				break;
		}
	}

	public void start(){
		Integer choice;
		do{
			choice = chooseCategory();
			doChooseCategory(choice);
		}while(choice != 4);

	}


}
