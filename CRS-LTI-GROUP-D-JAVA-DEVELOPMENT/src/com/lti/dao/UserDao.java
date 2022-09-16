package com.lti.dao;

import com.lti.bean.User;
import com.lti.exception.DuplicateStudentEntryException;
import com.lti.exception.DuplicateUserEntryException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UserNotDeletedException;
import com.lti.exception.UserNotFoundException;

/**interface for user dao operations*/

public interface UserDao {
	
	/**
     * method to fetch all users by studentId and password
     *
     * @return Returns a boolean value
     *
     * @param emailId
     * @param  password
     *
     */
	public boolean fetchAllUsers(String emailId, String password);
	
	/**
     * method for inserting a student into student table
     *
     * @return void
     *
     * @param name
     * @param emailId
     * @param password
     * @param role
     *
     * @throws DuplicateUserEntryException
     */
	public void userRegistration(String name, String emailId, String password, String role) throws DuplicateUserEntryException;
	
	/**
     * method to find a user in a user table
     *
     * @return Returns a user object
     *
     * @param emailId
     * @param password
     * @param role
     *
     */
	public User findUser(String emailId, String password, String role);
	
	/**
     * method for inserting a user into user table
     *
     * @return void
     *
     * @param emailId
     * @param password
     * @param role
     *
     * @throws DuplicateUserEntryException
     */
	public void insertUsert(String emailId, String password, String role) throws DuplicateUserEntryException;
	
	/**
     * method to find a user by emailId
     *
     * @return Returns a user object
     *
     * @param emailId
     *
     */
	public User findUserByEmailId(String emailId);
	
	/**
     * method for adding student into student table
     *
     * @return void
     *
     * @param name
     * @param emailId
     * @ password
     * @ role
     *
     */
	public void successUserRegistration(String name, String emailId, String password, String role);
	
	/**
     * method for deleting user
     *
     * @return void
     *
     * @param userEmailId
     *
     * @throws UserNotFoundException
     * @throws UserNotDeletedException
     */
	public void deleteUser(String userEmailId) throws UserNotFoundException, UserNotDeletedException;
}