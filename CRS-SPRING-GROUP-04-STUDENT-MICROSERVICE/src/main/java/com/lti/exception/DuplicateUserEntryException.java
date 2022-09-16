package com.lti.exception;

public class DuplicateUserEntryException extends Exception {
	String userEmail;
	String role;

	public DuplicateUserEntryException(String userEmail, String role) {
		super();
		this.userEmail = userEmail;
		this.role = role;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getRole() {
		return role;
	}
	
}