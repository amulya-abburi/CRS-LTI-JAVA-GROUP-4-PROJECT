package com.lti.service;

import java.util.List;

import com.lti.bean.Admin;
import com.lti.bean.Professor;
import com.lti.bean.Student;

/**
 * interface for user operations
 */
public interface UserInterfaceOperation {
	/**
	 * method for user logout
	 */
	public void logout();

	/**
	 * method for user reset password
	 */
	public void resetPassword();

	/**
	 * method for updating user information
	 */
	public void updateInfo();

	/**
	 * method for student login
	 * @param emailId
	 * @param password
	 * @param role
	 */
	public Student studentLogin(String email, String password, String role);

	/**
	 * method for professor login
	 * @param emailId
	 * @param password
	 * @param role
	 */
	public Professor professorLogin(String email, String password, String role);

	/**
	 * method for admin login
	 * @param emailId
	 * @param password
	 * @param role
	 */
	public Admin adminLogin(String email, String password, String role);
}