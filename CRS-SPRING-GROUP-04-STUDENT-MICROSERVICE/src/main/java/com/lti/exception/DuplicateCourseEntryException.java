package com.lti.exception;

public class DuplicateCourseEntryException extends Exception {
	int courseId;

	public DuplicateCourseEntryException(int courseId) {
		super();
		this.courseId = courseId;
	}
	
	public int getCourseId() {
		return courseId;
	}
}