package com.lti.dao;

import java.util.List;

import com.lti.bean.Admin;
import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Student;
import com.lti.exception.GradeNotAddedException;
import com.lti.exception.StudentNotFoundException;

/**interface for student dao operations*/

public interface StudentDao {
	 /**
     * method to fetch all Students
     *
     * @return Returns list of all the students
     */
	public List<Student> fetchAllStudents();
	
	 /**
     * method to fetch student details by id
     *
     * @return returns the Student object
     *
     * @param id
     *
     */
	public Student fetchStudentById(int id);
	
	/**
     * method to fetch student details by student emailId
     *
     * @return returns the Student object
     *
     * @param emailId
     *
     */
	public Student fetchStudentByEmail(String emailId);
	
	 /**
     * method for get grades in all courses of student based on studentId
     *
     * @return returns the List of Grades
     *
     * @param studentId
     *
     * @throws StudentNotFoundException
     */
	public List<Grade> getGradesByStudentId(int studentId) throws StudentNotFoundException;
	
	/**
     * method for finding a student by emailId and password
     *
     * @return Returns a Student object
     *
     * @param emailId
     * @param password
     *
     */
	public Student findStudent(String emailId, String password);
	
	/**
     * method for checking if student graded in each course
     *
     * @return Returns a boolean value
     *
     * @param studentId
     *
     * @throws StudentNotFoundException
     */
	public boolean isStudentGraded(int studentId) throws StudentNotFoundException;
}