package com.lti.exception;

public class NoCourseAvailableException extends Exception {
	int semesterId;

	public NoCourseAvailableException(int semesterId) {
		super();
		this.semesterId = semesterId;
	}

	public int getSemesterId() {
		return semesterId;
	}
}