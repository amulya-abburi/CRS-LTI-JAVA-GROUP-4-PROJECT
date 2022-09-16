package com.lti.bean;

import java.util.List;

public class Professor extends User{
	
	String name;
	String address;
	long mobileNumber;
//	List<Course> Courses;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
//	public List<Course> getCourses() {
//		return Courses;
//	}
//	public void setCourses(List<Course> courses) {
//		Courses = courses;
//	}
}
