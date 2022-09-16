package com.lti.service;

import java.util.Scanner;

import com.lti.bean.Student;
import com.lti.dao.UserDao;
import com.lti.dao.UserDaoImplementation;
import com.lti.exception.DuplicateStudentEntryException;
import com.lti.exception.DuplicateUserEntryException;

public class RegistrationService implements RegistrationInterfaceOperation {

	
	@Override
	public void registerStudent() {
		
		System.out.println("Welcome to registration");
		
		Student student = new Student();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter name:");
		String name = sc.next();
		student.setName(name);
		
		System.out.println("Enter email id:");
		String emailId = sc.next();
		student.setEmailId(emailId);
		
		System.out.println("Enter password:");
		String password = sc.next();
		student.setPassword(password);
		
	    try {
	    	UserDao userDao = new UserDaoImplementation();
			userDao.userRegistration(name, emailId, password, "student");
		} catch (DuplicateUserEntryException e) {
			System.out.println("user already exists with this email id: " + e.getUserEmail() + " who is: " + e.getRole());
		}
	    
	    System.out.println("\nStudent registered successfully!!");
		System.out.println("->Pending admin approval for login<-\n");
	}

}
