package com.lti.exception;

public class CourseNotFoundException extends Exception {
	int courseId;

	public CourseNotFoundException(int courseId) {
		super();
		this.courseId = courseId;
	}
	
	public int getCourseId() {
		return courseId;
	}
}