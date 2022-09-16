package com.lti.dao;

import java.util.List;

import com.lti.bean.Course;
import com.lti.bean.Student;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.DuplicateCourseEntryException;
import com.lti.exception.ProfessorNotFoundException;

/** interface for course dao operations */

public interface CourseDao {

	/**
	 * method to insert a course into course table
	 * 
	 * @param id
	 * @param name
	 * @param professorId
	 * @param amount
	 * @param department
	 * @param credit
	 * @param semesterId
	 * 
	 * @throws DuplicateCourseEntryException
	 */
	public void insertCourse(int id, String name, int professorId, int amount, String department, int credit,
			int semesterId) throws DuplicateCourseEntryException;

	/**
	 * method to get all courses by semester id
	 * 
	 * @param id
	 */
	public List<Course> getAllCourses(int id);

	/**
	 * method to delete courses from course catalogue
	 * 
	 * @param courseId
	 * 
	 * @throws CourseNotFoundException
	 */
	public void deleteCourse(int courseId) throws CourseNotFoundException;

	/**
	 * method to fetch course details by course id
	 * 
	 * @param courseId
	 */
	public Course fetchCourseById(int id);

	/**
	 * method to set status of a course
	 * 
	 * @param id
	 * 
	 * @throws CourseNotFoundException
	 */
	public void setStatus(int id) throws CourseNotFoundException;

	/**
	 * method to update course details
	 * 
	 * @param c
	 */
	public void updateCourse(Course c);

	/** to get all courses, in course catalouge */
	public List<Course> getAllCourses();

}