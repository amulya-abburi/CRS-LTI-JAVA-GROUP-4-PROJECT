package com.lti.bean;

import java.sql.Date;

// Admin class which extends to user
public class Admin extends User {

	String name;
	String address;
	long mobileNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

}
