package com.lti.exception;

public class StudentNotFoundException extends Exception {
	int studentId;

	public StudentNotFoundException(int studentId) {
		super();
		this.studentId = studentId;
	}
	
	public int getStudentId() {
		return studentId;
	}
}