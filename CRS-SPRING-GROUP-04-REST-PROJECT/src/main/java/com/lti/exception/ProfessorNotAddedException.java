package com.lti.exception;

public class ProfessorNotAddedException extends Exception {
	String professorEmail;

	public ProfessorNotAddedException(String professorEmail) {
		super();
		this.professorEmail = professorEmail;
	}

	public String getProfessorEmail() {
		return professorEmail;
	}
}
