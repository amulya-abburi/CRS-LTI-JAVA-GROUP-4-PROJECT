package com.lti.service;

import java.util.List;

import com.lti.bean.Course;
import com.lti.bean.Professor;

//interface for professor operations
public interface ProfessorInterfaceOperation extends UserInterfaceOperation {

	/**
	 * method to view courses assigned to professor
	 * @param pf (Professor class object)
	 */
	void viewCoursesAssigned(Professor pf);

	/**
	 * method to view enrolled students in a course
	 * @param pf (Professor class object)
	 */
	void viewEnrolledStudent(Professor pf);

	/**
	 * method to add grades of student in a course
	 * @param pf (Professor class object)
	 */
	void addGrades(Professor pf);

}