package com.lti.exception;

public class CoursesNotApprovedException extends Exception {
	int studentId;

	public CoursesNotApprovedException(int studentId) {
		super();
		this.studentId = studentId;
	}
	
	public int getStudentId() {
		return studentId;
	}
}