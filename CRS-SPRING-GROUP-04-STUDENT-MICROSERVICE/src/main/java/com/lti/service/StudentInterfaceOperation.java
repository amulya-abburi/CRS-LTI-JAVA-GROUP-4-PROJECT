package com.lti.service;

import java.util.List;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Payment;
import com.lti.bean.Student;
import com.lti.exception.CourseAlreadyRegisteredException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.DuplicatePaymentEntryException;
import com.lti.exception.SeatNotAvailableException;
import com.lti.exception.StudentCourseNotApprovedException;
import com.lti.exception.StudentNotFoundException;

/** interface for student operations */
public interface StudentInterfaceOperation {

	/**
	 * method to view all courses, present in catalouge
	 */
	public void viewCourse();

	/**
	 * method to add course in primary/secondary list
	 *
	 * @param studentId
	 * @param semesterId
	 * @param courseId
	 * @param isCoursePrimary
	 * 
	 * @throws CourseNotFoundException
	 * @throws SeatNotAvailableException
	 * @throws CourseAlreadyRegisteredException
	 */
	public boolean addCourse(int studentId, int semesterId, int courseId, int isCoursePrimary)
			throws CourseNotFoundException, SeatNotAvailableException, CourseAlreadyRegisteredException;

	/**
	 * method to drop registered course
	 *
	 * @param studentId
	 * @param courseId
	 *
	 * @throws CourseNotFoundException
	 * @throws StudentNotFoundException
	 */
	public boolean dropCourse(int studentId, int courseId) throws CourseNotFoundException, StudentNotFoundException;

	/**
	 * method to view registered courses
	 *
	 * @param studentId
	 *
	 * @throws StudentNotFoundException
	 */
	public List<Grade> viewRegisteredCourses(int studentId) throws StudentNotFoundException;

	/**
	 * method to view grades of a student
	 *
	 * @param studentId
	 *
	 * @throws StudentNotFoundException
	 */
	public List<Grade> viewGrades(int studentId) throws StudentNotFoundException;

	/**
	 * method to pay fee for registered courses
	 *
	 * @param studentId
	 * @param paymentMethod
	 *
	 * @throws DuplicatePaymentEntryException
	 * @throws StudentCourseNotApprovedException
	 * @throws StudentNotFoundException
	 */
	public Payment payFee(int studentId, String paymentMethod)
			throws DuplicatePaymentEntryException, StudentCourseNotApprovedException, StudentNotFoundException;

	/**
	 * method to view courses in a semester
	 *
	 * @param semesterId
	 *
	 * @return list of courses
	 */
	public List<Course> viewCourses(int semesterId);

}