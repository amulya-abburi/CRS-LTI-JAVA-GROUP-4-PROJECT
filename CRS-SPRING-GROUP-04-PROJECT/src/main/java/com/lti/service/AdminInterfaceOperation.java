package com.lti.service;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.CoursesNotApprovedException;
import com.lti.exception.DuplicateCourseEntryException;
import com.lti.exception.DuplicatePaymentEntryException;
import com.lti.exception.DuplicateProfessorEntryException;
import com.lti.exception.DuplicateUserEntryException;
import com.lti.exception.PaymentNotMadeException;
import com.lti.exception.ProfessorNotAddedException;
import com.lti.exception.ProfessorNotDeletedException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.StudentNotGradedException;
import com.lti.exception.UserNotDeletedException;
import com.lti.exception.UserNotFoundException;

/**interface for admin operations*/
public interface AdminInterfaceOperation {

	/**
	 * method to view all the courses present in the course catalogue
	 * 
	 * @return List of courses
	 */
	List<Course> viewCourses();

	/**
	 * method to view all the courses present in the course catalogue for a
	 * particular semester
	 * @param semesterId
	 */	void viewCourses(int semesterId);

	/**
	 * method to add a new course in the catalogue
	 * 
	 * @param course
	 * 
	 * @throws DuplicateCourseEntryException
	 */
	void addCourse(Course course) throws DuplicateCourseEntryException;

	/**
	 * method to remove a course from the catalogue
	 * 
	 * @param courseId
	 * 
	 * @throws CourseNotFoundException
	 */
	Course deleteCourse(int id) throws CourseNotFoundException;

	/**
	 * method to modify the course information/content
	 * 
	 * @param course
	 * 
	 * @throws CourseNotFoundException
	 */
	Course updateCourse(Course course) throws CourseNotFoundException;

	/**
	 * method to assign a professor to a course
	 * 
	 * @param courseId
	 * @param professorId
	 * 
	 * @throws CourseNotFoundException
	 * @throws ProfessorNotFoundException
	 * 
	 * 
	 */
	boolean assignCourseToProfessor(int professorId, int courseId)
			throws CourseNotFoundException, ProfessorNotFoundException;

	/** method to view all the professors */
	List<Professor> viewProfessorList();

	/**
	 * method to add a professor in the system
	 * 
	 * @param professor
	 * 
	 * @throws DuplicateProfessorEntryException
	 * @throws DuplicateUserEntryException
	 * @throws ProfessorNotAddedException
	 */
	Professor addProfessor(Professor professor)
			throws DuplicateUserEntryException, DuplicateProfessorEntryException, ProfessorNotAddedException;

	/**
	 * method to remove a professor from the system
	 * 
	 * @param ProfessorId
	 * 
	 * @throws UserNotFoundException
	 * @throws UserNotDeletedException
	 * @throws ProfessorNotFoundException
	 * @throws ProfessorNotDeletedException
	 */
	Professor deleteProfessor(int professorId) throws UserNotFoundException, UserNotDeletedException,
			ProfessorNotFoundException, ProfessorNotDeletedException;

	void viewStudentList();

	void viewPendingCourseApprovalStudentList();

	/**
	 * method to approve a student registration in the system
	 * 
	 * @param studentId
	 * 
	 * @throws DuplicateUserEntryException
	 * @throws StudentNotFoundException
	 */
	Student approveStudentRegistration(int studentId) throws DuplicateUserEntryException, StudentNotFoundException;

	/**
	 * method to approve student registered courses by admin
	 * 
	 * @param studentId
	 * 
	 * @throws StudentNotFoundException
	 */
	Student approveStudentCourseRegistration(int studentId) throws StudentNotFoundException;

	/**
	 * method to cancel a course for the semester if student strength < 3
	 * 
	 * @param courseId
	 * 
	 * @throws CourseNotFoundException
	 */
	Course cancelCourse(int courseId) throws CourseNotFoundException;

	Student findStudent(int id, List<Student> studentList);

	/**
	 * method to generate the report card for students for the semester
	 * 
	 * @param studentId
	 * 
	 * @throws StudentNotFoundException
	 * @throws CoursesNotApprovedException
	 * @throws StudentNotGradedException
	 * @throws PaymentNotMadeException
	 */
	Student generateReportCard(int studentId) throws StudentNotFoundException, CoursesNotApprovedException,
			StudentNotGradedException, PaymentNotMadeException;

	/**
	 * method to generate bill of student
	 * 
	 * @param studentId
	 * 
	 * @throws StudentNotFoundException
	 * @throws DuplicatePaymentEntryException
	 * @throws CoursesNotApprovedException
	 */
	int generateBill(int studentId)
			throws StudentNotFoundException, DuplicatePaymentEntryException, CoursesNotApprovedException;

	public void showReportCard(Student st);
	
	void printList(List<Grade> gradeList);

}