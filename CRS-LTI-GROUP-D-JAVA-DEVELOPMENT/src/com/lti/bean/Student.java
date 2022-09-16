package com.lti.bean;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

	String name;
	String address;
	String mobileNumber;

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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	boolean paymentStatus;
	boolean isApprovedByAdmin = false;
	boolean isCoursesApprovedByAdmin = false;

	List<Course> courseListPrimay = new ArrayList<Course>(4);
	List<Course> courseListSecondary = new ArrayList<Course>(2);

	public boolean isApprovedByAdmin() {
		return isApprovedByAdmin;
	}

	public void setApprovedByAdmin(boolean isApprovedByAdmin) {
		this.isApprovedByAdmin = isApprovedByAdmin;
	}

	public boolean isCoursesApprovedByAdmin() {
		return isCoursesApprovedByAdmin;
	}

	public void setCoursesApprovedByAdmin(boolean isCoursesApprovedByAdmin) {
		this.isCoursesApprovedByAdmin = isCoursesApprovedByAdmin;
	}

	public boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public List<Course> getCourseListPrimay() {
		return courseListPrimay;
	}

	public void setCourseListPrimay(List<Course> courseListPrimay) {
		this.courseListPrimay = courseListPrimay;
	}

	public List<Course> getCourseListSecondary() {
		return courseListSecondary;
	}

	public void setCourseListSecondary(List<Course> courseListSecondary) {
		this.courseListSecondary = courseListSecondary;
	}

}
