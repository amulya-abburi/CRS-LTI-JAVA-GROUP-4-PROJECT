package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.bean.Admin;
import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Student;
import com.lti.constant.SQLConstant;
import com.lti.exception.GradeNotAddedException;
import com.lti.exception.StudentNotFoundException;
import com.lti.utils.DBUtils;

@Repository
public class StudentDaoImplementation implements StudentDao {

	public Student fetchStudentById(int id) {

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.FETCH_STUDENT_BY_ID;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id1 = rs.getInt("studentid");
				String studentName = rs.getString("name");
				String password = rs.getString("password");
				String emailId = rs.getString("emailId");
				String role = rs.getString("role");
				Boolean isCourseApprovedByAdmin = rs.getBoolean("isCourseApprovedByAdmin");

				Student st = new Student();

				st.setId(id1);
				st.setName(studentName);
				st.setPassword(password);
				st.setEmailId(emailId);
				st.setRole(role);
				st.setCoursesApprovedByAdmin(isCourseApprovedByAdmin);
				st.setApprovedByAdmin(rs.getBoolean("isApprovedByAdmin"));
				st.setReportCardGenerated(rs.getBoolean("isReportCardGenerated"));
				return st;
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

		return null;
	}

	public Student fetchStudentByEmail(String emailId) {

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.FETCH_STUDENT_BY_EMAIL;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("studentid");
				String studentName = rs.getString("name");
				String password = rs.getString("password");
				String role = rs.getString("role");
				Boolean isCourseApprovedByAdmin = rs.getBoolean("isCourseApprovedByAdmin");

				Student st = new Student();

				st.setId(id);
				st.setName(studentName);
				st.setPassword(password);
				st.setEmailId(emailId);
				st.setRole(role);
				st.setCoursesApprovedByAdmin(isCourseApprovedByAdmin);

				return st;
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

		return null;
	}

	public List<Student> fetchAllStudents() {

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		List<Student> studentList = new ArrayList<>();

		try {
//			// Register Driver here and create connection
//			Class.forName("com.mysql.jdbc.Driver");
//
//			// make/open a connection
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// execute query
			String sql = SQLConstant.FETCH_ALL_STUDENTS;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int studentId = rs.getInt("studentid");
				String studentName = rs.getString("name");
				String password1 = rs.getString("password");
				String emailId = rs.getString("emailId");
				String role = rs.getString("role");
				int isApproved = rs.getInt("isApprovedByAdmin");

				Student st = new Student();

				st.setId(studentId);
				st.setPassword(password1);
				st.setName(studentName);
				st.setEmailId(emailId);
				st.setRole(role);
				if (isApproved == 1) {
					st.setApprovedByAdmin(true);
				} else {
					st.setApprovedByAdmin(false);
				}
				st.setCoursesApprovedByAdmin(rs.getBoolean("isCourseApprovedByAdmin"));

				studentList.add(st);
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

	public List<Grade> getGradesByStudentId(int studentId) throws StudentNotFoundException {

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		List<Grade> gradeList = new ArrayList<>();

		StudentDao studentDao = new StudentDaoImplementation();
		Student st = studentDao.fetchStudentById(studentId);
		if (st == null) {
			throw new StudentNotFoundException(studentId);
		}

		try {
			// execute query
			String sql = SQLConstant.GET_GRADES_BY_STUDENT_ID;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int courseId = rs.getInt("courseId");
				String grade = rs.getString("grade");
				int isPrimaryCourse = rs.getInt("isPrimaryCourse");
				int isApprovedByAdmin = rs.getInt("isApprovedByAdmin");

				CourseDao courseDao = new CourseDaoImplementation();

				Grade g = new Grade();
				g.setIsApprovedByAdmin(isApprovedByAdmin);
				g.setIsCoursePrimary(isPrimaryCourse);
				g.setCourse(courseDao.fetchCourseById(courseId));
				g.setStudent(studentDao.fetchStudentById(studentId));
				g.setGrade(grade);
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

	public Student findStudent(String emailId, String password) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.FIND_STUDENT;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailId);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("studentid");
				String name = rs.getString("name");
				String address = rs.getString("address");
				long mobileNumber = rs.getLong("mobileNumber");
				String role = rs.getString("role");
				Boolean isCourseApprovedByAdmin = rs.getBoolean("isCourseApprovedByAdmin");
				Boolean isApprovedByAdmin = rs.getBoolean("isApprovedByAdmin");

				Student st = new Student();

				st.setId(id);
				st.setName(name);
				st.setPassword(password);
				st.setEmailId(emailId);
				st.setRole(role);
				st.setAddress(address);
				st.setMobileNumber(mobileNumber);
				st.setApprovedByAdmin(isApprovedByAdmin);
				st.setCoursesApprovedByAdmin(isCourseApprovedByAdmin);

				return st;
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

		return null;
	}

	public boolean isStudentGraded(int studentId) throws StudentNotFoundException {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		StudentDao studentDao = new StudentDaoImplementation();
		Student st = studentDao.fetchStudentById(studentId);
		if (st == null) {
			throw new StudentNotFoundException(studentId);
		}

		try {
			// execute query
			String sql = SQLConstant.GET_GRADES_BY_STUDENT_ID;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String grade = rs.getString("grade");
				if (grade == null) {
					return false;
				}
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
		return true;
	}

}
