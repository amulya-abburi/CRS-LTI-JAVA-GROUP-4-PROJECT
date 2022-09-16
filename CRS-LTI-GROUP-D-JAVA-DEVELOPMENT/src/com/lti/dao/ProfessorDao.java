package com.lti.dao;

import java.util.List;


import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.CoursesNotApprovedException;
import com.lti.exception.DuplicatePaymentEntryException;
import com.lti.exception.DuplicateProfessorEntryException;
import com.lti.exception.GradeAlreadyAssignedException;
import com.lti.exception.ProfessorNotAddedException;
import com.lti.exception.ProfessorNotDeletedException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentCourseNotApprovedException;
import com.lti.exception.StudentNotFoundException;

/**interface for professor dao operations*/

public interface ProfessorDao {
	/**
	 * method for getting all the professors from professor table
	 */
	public List<Professor> getAllProfessors();
	
	/**
	 * method to insert professor into professor table
	 * 
	 * @param professorId
	 * @param professorName
	 * @param professorEmailId
	 * @param professorPassword
	 * 
	 * @throws DuplicateProfessorEntryException
	 * @throws ProfessorNotAddedException
	 */
	public void insertProfessor(int professorId, String professorName, String professorEmailId, String professorPassword, String role) throws DuplicateProfessorEntryException, ProfessorNotAddedException;
	
	/**
	 * method to delete professor from professor table
	 * 
	 * @param professorId
	 * 
	 * @throws ProfessorNotFoundException
	 * @throws ProfessorNotDeletedException
	 */
	public void deleteProfessor(int professorId) throws ProfessorNotFoundException, ProfessorNotDeletedException;
	
	/**
	 * method for getting professor details by id
	 * 
	 * @param professorId
	 * 
	 */
	public Professor getProfessorById(int professorId) ;
	
	/**
	 * method for getting assigned courses of a professor
	 * 
	 * @param professorId
	 * 
	 * @throws ProfessorNotFoundException
	 */
	public List<Course> getCoursesAssigned(int professorId) throws ProfessorNotFoundException;
	
	/**
	 * method for adding grades of a student
	 * 
	 * @param studentId
	 * @param courseId
	 * @param grade
	 * 
	 * @throws StudentNotFoundException;
	 * @throws CourseNotFoundException
	 * @throws GradeAlreadyAssignedException
	 * @throws StudentCourseNotApprovedException
	 */
	public void addGrades(int studentId, int courseId, String grade) throws StudentNotFoundException, CourseNotFoundException, GradeAlreadyAssignedException, StudentCourseNotApprovedException;
	
	/**
	 * method to find professor by emaildId and password
	 * 
	 * @param emailId
	 * @param password
	 */
	public Professor findProfessor(String emailId, String password);
}