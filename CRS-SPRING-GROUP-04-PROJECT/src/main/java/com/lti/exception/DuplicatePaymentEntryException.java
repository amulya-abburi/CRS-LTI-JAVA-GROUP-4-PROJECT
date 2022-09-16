package com.lti.exception;

public class DuplicatePaymentEntryException extends Exception {
	int studentId;

	public DuplicatePaymentEntryException(int studentId) {
		super();
		this.studentId = studentId;
	}
	
	public int getStudentId() {
		return studentId;
	}
}