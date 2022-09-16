package com.lti.exception;

public class ProfessorNotFoundException extends Exception {
	int professorId;

	public ProfessorNotFoundException(int professorId) {
		super();
		this.professorId = professorId;
	}
	
	public int getProfessorId() {
		return professorId;
	}
}