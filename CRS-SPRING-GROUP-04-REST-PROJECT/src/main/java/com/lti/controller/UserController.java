package com.lti.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.exception.AdminNotFoundException;
import com.lti.exception.CRSExceptionHandler;
import com.lti.exception.DuplicateUserEntryException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UserNotFoundException;
import com.lti.service.RegistrationInterfaceOperation;
import com.lti.service.UserInterfaceOperation;
@RestController
public class UserController{
	@Autowired
	UserInterfaceOperation userInterfaceOperation;
	
	@Autowired
	RegistrationInterfaceOperation registrationInterfaceOperation;
	
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
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/login/{emailId}/{password}/{role}")
	@ResponseBody
	public ResponseEntity login(@PathVariable String emailId,@PathVariable String password,@PathVariable String role) throws Exception{
		User user=userInterfaceOperation.userLogin(emailId, password, role);
		return new ResponseEntity(user,HttpStatus.OK);
	}
	
	/** method for user logout */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/logout")
	@ResponseBody
	public String logout() {
		return "user logging out";
	}
	
	/**
	 * method for user reset password
	 * 
	 * @param emailId
	 * @param oldPassword
	 * @param newPassword
	 * 
	 * @throws UserNotFoundException
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/resetPassword/{emailId}/{oldPassword}/{newPassword}")
	@ResponseBody
	public ResponseEntity updatePassword(@PathVariable String emailId,@PathVariable String oldPassword,@PathVariable String newPassword ) throws Exception{
		User user=userInterfaceOperation.resetPassword(emailId,oldPassword,newPassword);	
		return new ResponseEntity(user,HttpStatus.OK);
	}
	
	
	/**
	 * method for updating user information
	 * 
	 * @param emailId
	 * @param password
	 * @param mobileNumber
	 * 
	 * @throws UserNotFoundException
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/updateInfo/{emailId}/{password}/{mobileNumber}")
	@ResponseBody
	public ResponseEntity updateInfo(@PathVariable String emailId, @PathVariable String password, @PathVariable long mobileNumber) throws Exception{
		User user=userInterfaceOperation.updateInfo(emailId,password,mobileNumber);
		return new ResponseEntity(user,HttpStatus.OK);
	}
	
	/**
	 * method for registering a student
	 * @param student
	 * @throws DuplicateUserEntryException
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/registerStudent")
	@ResponseBody
	public ResponseEntity studentRegistration(@RequestBody Student student) throws Exception {
		boolean status=registrationInterfaceOperation.registerStudent(student);
		return new ResponseEntity(status,HttpStatus.OK);
	}
	
}
