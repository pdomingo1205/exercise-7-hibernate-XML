package com.model.person;

import java.util.*;

public class Address{

	private Integer addressId;
	private Person person;
	private Integer streetNo;
	private String barangay;
	private String municipality;
	private String zipCode;


	public Address(){

	}

	public Integer getStreetNo(){
		return streetNo;
	}

	public String getBarangay(){
		return barangay;
	}

	public String getMunicipality(){
		return municipality;
	}

	public String getZipCode(){
		return ZipCode;
	}

	public void setStreetNo(Integer newStreetNo){
		this.streetNo = newStreetNo;
	}

	public void setBarangay(String newBarangay){
		this.streetNo = newStreetNo;
	}

	public void setMunicipality(String newMunicipality){
		this.streetNo = newStreetNo;
	}

	public void setZipCode(Integer newZipCode){
		this.streetNo = newStreetNo;
	}


}
