package com.lti.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Course {
	int id;
	String courseName;
	String department;
	int professorId;
	List<Student> studentList=new ArrayList<Student>();
	HashMap<Integer, String> studentGrades = new HashMap<Integer, String>();
	int credit;
	boolean status;
	int courseFee;
	List<String> prequisite;
	String grade;
	int semesterId;
	
	public int getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}
	public HashMap<Integer, String> getStudentGrades() {
		return studentGrades;
	}
	public void setStudentGrades(HashMap<Integer, String> studentGrades) {
		this.studentGrades = studentGrades;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}
	public List<String> getPrequisite() {
		return prequisite;
	}
	public void setPrequisite(List<String> prequisite) {
		this.prequisite = prequisite;
	}
	

}
