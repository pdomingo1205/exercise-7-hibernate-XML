package com.pdomingo.service;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import java.io.IOException;
import java.text.*;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

public class InputValidation{


	private InputValidation(){

	}

	public static final class Validate{
		static Scanner scan = new Scanner(System.in);

		public static String getInput() {
			String text = scan.nextLine();
			if (text.isEmpty()) {
				text = null;
			}
			return text;
    	}

		public static String getRequiredInput() {
			String text;

			do{
				text = scan.nextLine();

				if(StringUtils.isNotBlank(text) == false){
					System.out.println("Invalid Input");
				}
			}while(StringUtils.isNotBlank(text));


			return text;
		}

		public static Integer getInteger() {
			Integer outValue;
			String inValue;
			do{

				inValue = getInput();

				if(NumberUtils.isDigits(inValue) == false){
					System.out.println("Invalid Input");
				}

			}while(NumberUtils.isDigits(inValue) == false);

			outValue = Integer.valueOf(inValue);
			return outValue;
    	}

		public static Date getDate() {
			String inValue;
			Date date = new Date();
			Boolean isValid = true;
			do{

				try{

						inValue = getInput();
						date = new SimpleDateFormat("YYYY-MM-DD").parse(inValue);
				}catch(ParseException e){
					System.out.println("Invalid date");
					isValid = false;
				}

			}while(isValid.equals(false));

			return date;
    	}

		public static Integer getIntegerInRange(int min, int max) {
			Integer value;
			do{
				value = getInteger();

			}while(value < min || value > max);

			return value;
		}

		public static String getMatchedNumber(String matcher) {
			String mobileNumber;

			do{
				mobileNumber = scan.nextLine();

				if(mobileNumber.matches(matcher) == false){
					System.out.println("Invalid Input");
				}
			}while(mobileNumber.matches(matcher));


			return mobileNumber;
		}

		public static String getEmailAddress() {
			String text;

			do{
				text = scan.nextLine();

				if(StringUtils.isNotBlank(text) == false){
					System.out.println("Invalid Email Address");
				}

			}while(EmailValidator.getInstance().isValid(text));


			return text;
		}



	}

}
