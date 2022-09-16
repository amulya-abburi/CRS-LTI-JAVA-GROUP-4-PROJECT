package com.lti.exception;

public class ProfessorNotDeletedException extends Exception {
	int professorId;

	public ProfessorNotDeletedException(int professorId) {
		super();
		this.professorId = professorId;
	}
	
	public int getProfessorId() {
		return professorId;
	}
}
