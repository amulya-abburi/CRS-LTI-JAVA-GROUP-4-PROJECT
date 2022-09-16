package com.lti.exception;

public class GradeNotAddedException extends Exception {
	int courseId;

	public GradeNotAddedException(int courseId) {
		super();
		this.courseId = courseId;
	}
	
	public int getCourseId() {
		return courseId;
	}
}