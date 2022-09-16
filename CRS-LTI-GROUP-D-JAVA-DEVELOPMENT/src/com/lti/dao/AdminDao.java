package com.lti.dao;

import java.util.List;

import com.lti.bean.Admin;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;

/**interface for admin dao operations*/

public interface AdminDao {
	/**
	 * method to approve a student registration in the system
	 * 
	 * @param id (id of the student)
	 * 
	 * @throws StudentNotFoundException
	 */
	public boolean approveStudentInDB(int id) throws StudentNotFoundException;
	
	/**
	 * method to approve student registered courses in the system
	 * 
	 * @param id (id of the student)
	 * 
	 * @throws StudentNotFoundException
	 */
	public void approveStudentCourseRegistration(int id) throws StudentNotFoundException;
	
	/**
	 * method to fetch all admins present in admin table 
	 */
	public List<Admin> fetchAllAdmins();
	
	/**
	 * method to assign a course to the professor
	 * 
	 * @param courseId
	 * @param professorId
	 * 
	 * @throws ProfessorNotFoundException
	 * @throws CourseNotFoundException
	 */
	public void assignCourseToProfessor(int courseId, int professorId) throws ProfessorNotFoundException, CourseNotFoundException;
	/**
	 * method to find a admin by emailId and password in admin table
	 * 
	 * @param emailId
	 * @param password
	 */
	public Admin findAdmin(String emailId, String password);
}