package com.lti.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.dao.CourseDao;
import com.lti.dao.CourseDaoImplementation;
import com.lti.dao.SemesterRegistrationDao;
import com.lti.dao.SemesterRegistrationDaoImplementation;
import com.lti.dao.ProfessorDao;
import com.lti.dao.ProfessorDaoImplementation;
import com.lti.dao.StudentDao;
import com.lti.dao.StudentDaoImplementation;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.GradeAlreadyAssignedException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentCourseNotApprovedException;
import com.lti.exception.StudentNotFoundException;
import com.lti.utils.TableWithLines;
import com.lti.bean.Course;

@Repository
public class ProfessorService implements ProfessorInterfaceOperation {

	@Autowired
	ProfessorDao professorDao;

	@Autowired
	SemesterRegistrationDao semesterRegistrationDao;

	@Autowired
	CourseDao courseDao;

	@Override
	public List<Course> viewCoursesAssigned(int professorId) throws ProfessorNotFoundException {
		List<Course> coursesAssigned = new ArrayList<>();
		ProfessorDao professorDao = new ProfessorDaoImplementation();
		coursesAssigned = professorDao.getCoursesAssigned(professorId);

		return coursesAssigned;
	}

	@Override
	public List<Student> viewEnrolledStudent(int professorId, int courseId)
			throws CourseNotFoundException, ProfessorNotFoundException {
		List<Student> studentList = new ArrayList<>();

		Course course = courseDao.fetchCourseById(courseId);
		if (course == null) {
			throw new CourseNotFoundException(courseId);
		}
		if (professorDao.getProfessorById(professorId) == null) {
			throw new ProfessorNotFoundException(professorId);
		}
		if (course.getProfessorId() == professorId) {
			studentList = semesterRegistrationDao.getAllStudentsByCourseId(courseId);
		}
		if (studentList.size() == 0) {
			System.out.printf("->student list is empty in course with course id: %s<-\n", courseId);
		}
//		showEnrolledStudentsByProfessor(studentList);
		return studentList;
	}

	@Override
	public boolean addGrades(int studentId, int courseId, String grade) throws StudentNotFoundException,
			CourseNotFoundException, GradeAlreadyAssignedException, StudentCourseNotApprovedException {
		professorDao.addGrades(studentId, courseId, grade);
		return true;

	}

}
