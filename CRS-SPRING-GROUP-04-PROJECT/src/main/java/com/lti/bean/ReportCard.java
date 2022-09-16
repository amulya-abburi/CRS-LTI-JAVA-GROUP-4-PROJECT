package com.lti.bean;

import java.util.List;

public class ReportCard {
	int Id;
	Student student;
	List<Grade> grades;
	String grade;
	boolean result;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<Grade> getExams() {
		return grades;
	}
	public void setExams(List<Grade> exams) {
		this.grades = exams;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}

}
