package com.lti.service;

import java.util.List;

import com.lti.bean.Course;
import com.lti.bean.Student;

/**interface for student operations*/
public interface StudentInterfaceOperation extends UserInterfaceOperation {

	/**
	 * method to view all courses, present in catalouge
	 * @param st (Student class object)
	 */
	public void viewCourse();

	/**
	 * method to add course in primary/secondary list
	 * @param st (Student class object)
	 */
	public void addCourse(Student st);

	/**
	 * method to drop registered course
	 * @param st (Student class object)
	 */
	public void dropCourse(Student st);

	/**
	 * method to view registered courses
	 * @param st (Student class object)
	 */
	public void viewRegisteredCourses(Student st);

	/**
	 * method  to view grades of a student
	 * @param st (Student class object)
	 */
	void viewGrades(Student st);

	/**
	 * method to print courseList
	 * @param courseList (list of Course class object)
	 */
	public String printList(List<Course> courseList);

	/**
	 * method to pay fee for registered courses
	 * @param st (Student class object)
	 */
	public void payFee(Student st);

}