package com.lti.service;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;

import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.GradeAlreadyAssignedException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentCourseNotApprovedException;
import com.lti.exception.StudentNotFoundException;

//interface for professor operations
public interface ProfessorInterfaceOperation {

	/**
	 * method to view courses assigned to professor
	 * @param professorId
	 * 
	 * @throws ProfessorNotFoundException
	 */
	public List<Course> viewCoursesAssigned(int professorId) throws ProfessorNotFoundException;


	/**
	 * method to view enrolled students in a course
	 * 
	 * @param professorId
	 * @param courseId
	 * 
	 * @throws CourseNotFoundException
	 * @throws ProfessorNotFoundException
	 */
	public List<Student> viewEnrolledStudent(int professorId,int courseId) throws CourseNotFoundException,ProfessorNotFoundException;

		
	/**
	 * method to add grades of student in a course
	 * 
	 * @param studentId
	 * @param courseId
	 * @param grade
	 * 
	 * @throws StudentCourseNotApprovedException
	 * @throws GradeAlreadyAssignedException
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 */
	public boolean addGrades(int studentId,int courseId,String grade) throws StudentNotFoundException, CourseNotFoundException, GradeAlreadyAssignedException, StudentCourseNotApprovedException;
}