package com.lti.exception;

public class CourseAlreadyRegisteredException extends Exception {
	int courseId;

	public CourseAlreadyRegisteredException(int courseId) {
		super();
		this.courseId = courseId;
	}
	
	public int getCourseId() {
		return courseId;
	}
}
