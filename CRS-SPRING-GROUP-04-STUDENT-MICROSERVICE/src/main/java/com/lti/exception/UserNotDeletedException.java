package com.lti.exception;

public class UserNotDeletedException extends Exception {
	String userEmailId;

	public UserNotDeletedException(String userEmailId) {
		super();
		this.userEmailId = userEmailId;
	}

	public String getUserEmailId() {
		return userEmailId;
	}
}
