package com.lti.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Payment;
import com.lti.bean.Student;
import com.lti.dao.CourseDao;
import com.lti.dao.CourseDaoImplementation;
import com.lti.dao.SemesterRegistrationDao;
import com.lti.dao.SemesterRegistrationDaoImplementation;
import com.lti.dao.PaymentDao;
import com.lti.dao.PaymentDaoImplementation;
import com.lti.dao.StudentDao;
import com.lti.dao.StudentDaoImplementation;
import com.lti.exception.CourseAlreadyRegisteredException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.DuplicatePaymentEntryException;
import com.lti.exception.SeatNotAvailableException;
import com.lti.exception.StudentCourseNotApprovedException;
import com.lti.exception.StudentNotFoundException;
import com.lti.utils.TableWithLines;

@Repository
public class StudentService implements StudentInterfaceOperation {

	@Autowired
	CourseDao courseDao;

	@Autowired
	SemesterRegistrationDao semesterRegistrationDao;

	@Autowired
	StudentDao studentDao;

	@Autowired
	PaymentDao paymentDao;

	@Override
	public void viewCourse() {
		CourseDao coursedao = new CourseDaoImplementation();
		List<Course> courseList = coursedao.getAllCourses();

		String[] columns = new String[] { "COURSE ID", "COURSE NAME", "COURSE FEE" };
		String[][] table = new String[courseList.size() + 1][columns.length];
		table[0] = columns;

		for (int i = 0; i < courseList.size(); i++) {
			table[i + 1][0] = courseList.get(i).getId() + "";
			table[i + 1][1] = courseList.get(i).getCourseName();
			table[i + 1][2] = courseList.get(i).getCourseFee() + "";
		}
		TableWithLines.tableWithGivenColumnsLength(table, true, "COURSES AVAILABLE", new int[] { 20, 20, 20 });
	}

	@Override
	public boolean addCourse(int studentId, int semesterId, int courseId, int isCoursePrimary)
			throws CourseNotFoundException, SeatNotAvailableException, CourseAlreadyRegisteredException {

		semesterRegistrationDao.registerCourse(studentId, courseId, semesterId, isCoursePrimary);
		return true;

	}

	// function to view all courses present in course catalouge for a particular
	// semester
	public List<Course> viewCourses(int semesterId) {
		List<Course> courseList = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		courseList = courseDao.getAllCourses(semesterId);

		return courseList;
	}

	@Override
	public boolean dropCourse(int studentId, int courseId) throws CourseNotFoundException, StudentNotFoundException {

		semesterRegistrationDao.deleteRegisteredCourse(studentId, courseId);
		return true;
	}

	@Override
	public List<Grade> viewRegisteredCourses(int studentId) throws StudentNotFoundException {
		List<Grade> gradeList = new ArrayList<>();

		gradeList = semesterRegistrationDao.getAllRegisteredCoursesByStudentId(studentId);

		return gradeList;
	}

	@Override
	public List<Grade> viewGrades(int studentId) throws StudentNotFoundException {
		List<Grade> gradeList = new ArrayList<>();

		gradeList = studentDao.getGradesByStudentId(studentId);

		return gradeList;
	}

	@Override
	public Payment payFee(int studentId, String paymentMethod)
			throws DuplicatePaymentEntryException, StudentCourseNotApprovedException, StudentNotFoundException {
		Payment p;

		if (studentDao.fetchStudentById(studentId).isCoursesApprovedByAdmin() == false) {
			throw new StudentCourseNotApprovedException(studentId);
		}

		List<Grade> gradeList = semesterRegistrationDao.getAllRegisteredCoursesByStudentId(studentId);
		if (paymentDao.getPaymentStatusByStudentId(studentId).getPaymentStatus()) {
			throw new DuplicatePaymentEntryException(studentId);
		}
		p = paymentDao.updateFeeStatus(studentId, paymentMethod);
		return p;

	}

}
