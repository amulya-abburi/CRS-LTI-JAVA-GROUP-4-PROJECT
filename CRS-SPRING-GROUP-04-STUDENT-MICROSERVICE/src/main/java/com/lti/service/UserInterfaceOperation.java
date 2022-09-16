package com.lti.service;

import java.util.List;

import com.lti.bean.Admin;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.exception.AdminNotFoundException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UserNotFoundException;

/**
 * interface for user operations
 */
public interface UserInterfaceOperation {
	
	/**
	 * method for user login
	 * 
	 * @param emailId
	 * @param password
	 * @param role
	 * 
	 * @throws AdminNotFoundException
	 * @throws StudentNotFoundException
	 * @throws ProfessorNotFoundException
	 *   */
	
	public User userLogin(String emailId, String password, String role) throws AdminNotFoundException,StudentNotFoundException,ProfessorNotFoundException;

	/**
	 * method for user logout
	 */
	public void logout();

	/**
	 * method for user reset password
	 * 
	 * @param emailId
	 * @param oldPassword
	 * @param newPassword
	 * 
	 * @throws UserNotFoundException
	 */
	public User resetPassword(String emailId,String oldPassword,String newPassword) throws UserNotFoundException;
		

	/**
	 * method for updating user information
	 * 
	 * @param emailId
	 * @param password
	 * @param mobileNumber
	 * 
	 * @throws UserNotFoundException
	 */
	public User updateInfo(String emailId,String password, long mobileNumber) throws UserNotFoundException;
			
}