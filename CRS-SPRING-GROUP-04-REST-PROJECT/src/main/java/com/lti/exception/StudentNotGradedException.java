package com.lti.exception;

public class StudentNotGradedException extends Exception {
	int studentId;

	public StudentNotGradedException(int studentId) {
		super();
		this.studentId = studentId;
	}
	
	public int getStudentId() {
		return studentId;
	}
}