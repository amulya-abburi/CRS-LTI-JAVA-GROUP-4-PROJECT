package com.lti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Student;
import com.lti.constant.SQLConstant;
import com.lti.exception.CourseAlreadyRegisteredException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.SeatNotAvailableException;
import com.lti.exception.StudentNotFoundException;
import com.lti.utils.DBUtils;

public class SemesterRegistrationDaoImplementation implements SemesterRegistrationDao {

	public void registerCourse(int studentId, int courseId, int semesterId, int isPrimaryCourse)
			throws CourseNotFoundException, SeatNotAvailableException, CourseAlreadyRegisteredException {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		CourseDao courseDao = new CourseDaoImplementation();
		Course c = courseDao.fetchCourseById(courseId);
		if (c == null) {
			throw new CourseNotFoundException(courseId);
		}

		List<Student> studentList = this.getAllStudentsByCourseId(courseId);
		if (studentList.size() >= 10) {
			throw new SeatNotAvailableException(courseId);
		}
		
		if(this.isCourseAlreadyRegistered(studentId, courseId)) {
			throw new CourseAlreadyRegisteredException(courseId);
		}

		try {
			// Execute a query
			String sql = SQLConstant.REGISTER_COURSE;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			stmt.setInt(3, semesterId);
			stmt.setInt(4, isPrimaryCourse);
			int count = stmt.executeUpdate();

			if (count == 1) {
				System.out.println("course registered successfully...");
			} else {
				System.out.println("some error occur in data insertion");
			}

			// Clean-up environment
			stmt.close();
			// conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			if (conn != null) {
			}
			// conn.close();
		}
	}

	public void deleteRegisteredCourse(int studentId, int courseId) throws CourseNotFoundException, StudentNotFoundException {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		CourseDao courseDao = new CourseDaoImplementation();
		Course c = courseDao.fetchCourseById(courseId);

		if (c == null) {
			throw new CourseNotFoundException(courseId);
		}

		StudentDao studentDao = new StudentDaoImplementation();
		Student st = studentDao.fetchStudentById(studentId);

		if (st == null) {
			throw new StudentNotFoundException(studentId);
		}

		try {
			// Execute a query
			String sql = SQLConstant.DELETE_REGISTER_COURSE;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			int count = stmt.executeUpdate();

			if (count >= 1) {
				System.out.println("registered course deleted successfully...");
			} else {
				System.out.println("some error occur in data deletion");
			}

			// Clean-up environment
			stmt.close();
			// conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			if (conn != null) {
			}
			// conn.close();
		}
	}

	public List<Grade> getAllRegisteredCoursesByStudentId(int studentId) throws StudentNotFoundException {
		// Declare the Connection or PreparedStatement variable here
		List<Grade> gradeList = new ArrayList<>();

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		StudentDao studentDao = new StudentDaoImplementation();
		Student st = studentDao.fetchStudentById(studentId);

		if (st == null) {
			throw new StudentNotFoundException(studentId);
		}

		try {
			// execute query
			String sql = SQLConstant.GET_ALL_REGISTERED_COURSES_BY_STUDENT_ID;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int courseId = rs.getInt("courseId");
				int isPrimaryCourse = rs.getInt("isPrimaryCourse");
				int isApprovedByAdmin = rs.getInt("isApprovedByAdmin");
				CourseDao courseDao = new CourseDaoImplementation();

				Grade g = new Grade();
				g.setCourse(courseDao.fetchCourseById(courseId));
				g.setIsApprovedByAdmin(isApprovedByAdmin);
				g.setIsCoursePrimary(isPrimaryCourse);
				g.setStudent(studentDao.fetchStudentById(studentId));
				gradeList.add(g);

			}

			// Clean-up environment
			stmt.close();
			// conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			if (conn != null) {
			}
			// conn.close();
		}

		return gradeList;
	}

	public void approveStudentRegisteredCourse(int studentId, int courseId) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			stmt = conn.prepareStatement(SQLConstant.APPROVE_STUDENT_REGISTERED_COURSE);
			stmt.setInt(1, 1);
			stmt.setInt(2, studentId);
			stmt.setInt(3, courseId);
			stmt.execute();

			// Clean-up environment
			stmt.close();
			// conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			if (conn != null) {
			}
			// conn.close();
		}
	}

	public List<Student> getAllStudentsByCourseId(int courseId) {
		// Declare the Connection or PreparedStatement variable here
		List<Student> studentList = new ArrayList<>();

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.GET_ALL_STUDENTS_BY_COURSE_ID;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, courseId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int studentId = rs.getInt("studentId");
				StudentDao studentDao = new StudentDaoImplementation();
				Student s = studentDao.fetchStudentById(studentId);
				studentList.add(s);
			}

			// Clean-up environment
			stmt.close();
			// conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			if (conn != null) {
			}
			// conn.close();
		}

		return studentList;
	}

	public String getGradeOfStudentInCourse(int studentId, int courseId) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.GET_GRADE_OF_STUDENT_IN_COURSE;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String grade = rs.getString("grade");
				return grade;
			}

			// Clean-up environment
			stmt.close();
			// conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			if (conn != null) {
			}
			// conn.close();
		}
		return "";
	}

	public boolean isCourseAlreadyRegistered(int studentId, int courseId) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.GET_GRADE_OF_STUDENT_IN_COURSE;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return true;
			}

			// Clean-up environment
			stmt.close();
			// conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			if (conn != null) {
			}
			// conn.close();
		}
		return false;
	}
}
