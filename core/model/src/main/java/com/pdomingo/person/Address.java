package com.pdomingo.model.person;

import java.util.*;

public class Address{

	private Integer streetNo;
	private String barangay;
	private String municipality;
	private String zipCode;


	public Address(){
	}

	public Address(Integer newStreetNo, String newBarangay, String newMunicipality, String newZipCode){
		this.streetNo = newStreetNo;
		this.barangay = newBarangay;
		this.municipality = newMunicipality;
		this.zipCode = newZipCode;
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
		return zipCode;
	}

	public void setStreetNo(Integer newStreetNo){
		this.streetNo = newStreetNo;
	}

	public void setBarangay(String newBarangay){
		this.barangay = newBarangay;
	}

	public void setMunicipality(String newMunicipality){
		this.municipality = newMunicipality;
	}

	public void setZipCode(String newZipCode){
		this.zipCode = newZipCode;
	}

	@Override
	public String toString(){
		return String.format("%s %s %s %s ", streetNo, barangay, municipality, zipCode);
	}

}
