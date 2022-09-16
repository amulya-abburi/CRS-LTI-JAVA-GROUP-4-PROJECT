package com.lti.bean;

import java.sql.Date;

public class Grade {
	int id;
	int courseId;
	String grade;
	Student student;
	Course course;
    int isCoursePrimary;
    int isApprovedByAdmin;
	int marksObtained;
	int totalMarks;
	Date examDate;
	
    public int getIsCoursePrimary() {
		return isCoursePrimary;
	}
	public void setIsCoursePrimary(int isCoursePrimary) {
		this.isCoursePrimary = isCoursePrimary;
	}
	public int getIsApprovedByAdmin() {
		return isApprovedByAdmin;
	}
	public void setIsApprovedByAdmin(int isApprovedByAdmin) {
		this.isApprovedByAdmin = isApprovedByAdmin;
	}
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
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
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	

}
