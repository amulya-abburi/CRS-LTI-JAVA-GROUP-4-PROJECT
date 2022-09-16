package com.lti.exception;

public class PaymentNotMadeException extends Exception {
	int studentId;

	public PaymentNotMadeException(int studentId) {
		super();
		this.studentId = studentId;
	}
	
	public int getStudentId() {
		return studentId;
	}
}
