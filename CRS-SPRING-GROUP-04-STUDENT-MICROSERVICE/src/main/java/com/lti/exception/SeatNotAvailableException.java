package com.lti.exception;

public class SeatNotAvailableException extends Exception {
	int courseId;

	public SeatNotAvailableException(int courseId) {
		super();
		this.courseId = courseId;
	}
	
	public int getCourseId() {
		return courseId;
	}
}
