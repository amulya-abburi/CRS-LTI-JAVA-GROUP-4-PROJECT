package com.lti.exception;

public class DuplicateProfessorEntryException extends Exception {
	String professorEmail;

	public DuplicateProfessorEntryException(String professorEmail) {
		super();
		this.professorEmail = professorEmail;
	}

	public String getProfessorEmail() {
		return professorEmail;
	}
	
}