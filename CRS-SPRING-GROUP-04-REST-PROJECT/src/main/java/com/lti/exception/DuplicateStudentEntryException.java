package com.lti.exception;

public class DuplicateStudentEntryException extends Exception {
	String studentEmail;

	public DuplicateStudentEntryException(String studentEmail) {
		super();
		this.studentEmail = studentEmail;
	}

	public String getStudentEmail() {
		return studentEmail;
	}
	
}