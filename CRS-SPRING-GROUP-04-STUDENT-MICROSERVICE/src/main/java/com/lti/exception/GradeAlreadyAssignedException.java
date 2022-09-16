package com.lti.exception;

public class GradeAlreadyAssignedException extends Exception {
	int studentId;
	int courseId;
	String grade;
	
	public GradeAlreadyAssignedException(int studentId, int courseId, String grade) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.grade = grade;
	}

	public int getStudentId() {
		return studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public String getGrade() {
		return grade;
	}
}