package com.lti.exception;

public class StudentCourseNotApprovedException extends Exception {
	int studentId;

	public StudentCourseNotApprovedException(int studentId) {
		super();
		this.studentId = studentId;
	}
	
	public int getStudentId() {
		return studentId;
	}
}
