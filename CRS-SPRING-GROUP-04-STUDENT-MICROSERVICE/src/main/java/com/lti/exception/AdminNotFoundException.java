package com.lti.exception;

public class AdminNotFoundException extends Exception {
	String adminEmail;

	public AdminNotFoundException(String adminEmail) {
		super();
		this.adminEmail = adminEmail;
	}
	
	public String getAdminEmail() {
		return adminEmail;
	}
}
