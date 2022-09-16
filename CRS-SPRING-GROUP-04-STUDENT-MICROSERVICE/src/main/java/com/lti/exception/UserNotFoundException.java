package com.lti.exception;

public class UserNotFoundException extends Exception {
	String userEmailId;

	public UserNotFoundException(String userEmailId) {
		super();
		this.userEmailId = userEmailId;
	}

	public String getUserEmailId() {
		return userEmailId;
	}
	
}
