package com.lti.dao;

import java.util.List;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Student;
import com.lti.exception.CourseAlreadyRegisteredException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.GradeAlreadyAssignedException;
import com.lti.exception.SeatNotAvailableException;
import com.lti.exception.StudentCourseNotApprovedException;
import com.lti.exception.StudentNotFoundException;

/**interface for semester registration dao operations*/

public interface SemesterRegistrationDao {
	/**
	 * method to register course for student
	 * 
	 * @param studentId
	 * @param courseId
	 * @param semesterId
	 * @param isPrimaryCourse
	 * 
	 * @throws CourseNotFoundException
	 * @throws SeatNotAvailableException
	 * @throws CourseAlreadyRegisteredException
	 */
	public void registerCourse(int studentId, int courseId, int semesterId, int isPrimaryCourse) throws CourseNotFoundException, SeatNotAvailableException, CourseAlreadyRegisteredException;
	
	/**
	 * method to unregister a course for student
	 * 
	 * @param studentId
	 * @param courseId
	 * 
	 * @throws CourseNotFoundException;
	 * @throws StudentNotFoundException
	 */
	public void deleteRegisteredCourse(int studentId, int courseId) throws CourseNotFoundException, StudentNotFoundException;
	
	/**
	 * method to get all registered courses for a student
	 * 
	 * @param studentId
	 * 
	 * @throws StudentNotFoundException;
	 */
	public List<Grade> getAllRegisteredCoursesByStudentId(int studentId) throws StudentNotFoundException;
	
	/**
	 * method for approving student course
	 * 
	 * @param studentId
	 * @param courseId
	 */
	public void approveStudentRegisteredCourse(int studentId, int courseId);
	
	/**
	 * method for getting all registered students in a course
	 * 
	 * @param courseId
	 */
	public List<Student> getAllStudentsByCourseId(int courseId);
	
	/**
	 * method to get grades of a course for student
	 * 
	 * @param studentId
	 * @param courseId
	 * 
	 */
	public String getGradeOfStudentInCourse(int studentId, int courseId);
	
	/**
	 * method to check if course is already registered
	 * 
	 * @param studentId
	 * @param courseId
	 */
	public boolean isCourseAlreadyRegistered(int studentId, int courseId);
}