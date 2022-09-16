package com.lti.bean;

import java.util.ArrayList;
import java.util.List;

public class Payment {
	int studentId;
	String paymentMethod;
	int amount;
	boolean paymentStatus;
	List<String> paymentMethods = new ArrayList<String>();

	public Payment() {
		super();
		this.paymentMethods.add("credit card");
		this.paymentMethods.add("debit card");
		this.paymentMethods.add("netbanking");
		this.paymentMethods.add("UPI");
	}
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public List<String> getPaymentMethods() {
		return paymentMethods;
	}
	public void setPaymentMethods(List<String> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	@Override
	public String toString() {
		return "Payment [studentId=" + studentId + ", paymentMethod=" + paymentMethod + ", amount=" + amount
				+ ", paymentStatus=" + paymentStatus + "]";
	}
}