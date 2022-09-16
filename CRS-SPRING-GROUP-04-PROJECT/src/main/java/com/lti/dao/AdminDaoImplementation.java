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
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.constant.SQLConstant;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.utils.DBUtils;

@Repository
public class AdminDaoImplementation implements AdminDao {

	public boolean approveStudentInDB(int studentId) throws StudentNotFoundException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		StudentDao studentDao = new StudentDaoImplementation();
		Student st = studentDao.fetchStudentById(studentId);

		if (st == null) {
			throw new StudentNotFoundException(studentId);
		}

		try {
			// execute query
			stmt = conn.prepareStatement(SQLConstant.APPROVE_STUDENT_REGISTRATION);
			stmt.setBoolean(1, true);
			stmt.setInt(2, studentId);
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

		return false;
	}

	public boolean generateReportCard(int studentId) throws StudentNotFoundException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		StudentDao studentDao = new StudentDaoImplementation();
		Student st = studentDao.fetchStudentById(studentId);

		if (st == null) {
			throw new StudentNotFoundException(studentId);
		}

		try {
			// execute query
			stmt = conn.prepareStatement(SQLConstant.GENERATE_REPORT_CARD);
			stmt.setBoolean(1, true);
			stmt.setInt(2, studentId);
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

		return false;
	}

	public void approveStudentCourseRegistration(int studentId) throws StudentNotFoundException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		StudentDao studentDao = new StudentDaoImplementation();
		Student st = studentDao.fetchStudentById(studentId);

		if (st == null) {
			throw new StudentNotFoundException(studentId);
		}

		try {
			stmt = conn.prepareStatement(SQLConstant.APPROVE_STUDENT_COURSE_REGISTRATION);
			stmt.setBoolean(1, true);
			stmt.setInt(2, studentId);
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

	public List<Admin> fetchAllAdmins() {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		List<Admin> adminList = new ArrayList<>();

		try {
			String sql = SQLConstant.GET_ALL_ADMINS;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int adminId = rs.getInt("adminId");
				String adminName = rs.getString("adminName");
				String password = rs.getString("adminPassword");
				String emailId = rs.getString("adminEmailId");
				String role = rs.getString("role");

				Admin ad = new Admin();

				ad.setEmailId(emailId);
				ad.setId(adminId);
				ad.setName(adminName);
				ad.setPassword(password);
				ad.setRole(role);

				adminList.add(ad);
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

		return adminList;
	}

	public void assignCourseToProfessor(int courseId, int professorId)
			throws ProfessorNotFoundException, CourseNotFoundException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		CourseDao courseDao = new CourseDaoImplementation();
		Course c = courseDao.fetchCourseById(courseId);

		if (c == null) {
			throw new CourseNotFoundException(courseId);
		}

		ProfessorDao professorDao = new ProfessorDaoImplementation();
		Professor p = professorDao.getProfessorById(professorId);

		if (p == null) {
			throw new ProfessorNotFoundException(professorId);
		}

		try {
			stmt = conn.prepareStatement(SQLConstant.ASSIGN_COURSE_TO_PROFESSOR);
			stmt.setInt(1, professorId);
			stmt.setInt(2, courseId);
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

	public Admin findAdmin(String emailId, String password) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.FIND_ADMIN;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailId);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("adminId");
				String adminName = rs.getString("adminName");
				String address = rs.getString("address");
				long mobileNumber = rs.getLong("mobileNumber");
				String role = rs.getString("role");

				Admin ad = new Admin();

				ad.setId(id);
				ad.setEmailId(emailId);
				ad.setPassword(password);
				ad.setName(adminName);
				ad.setAddress(address);
				ad.setMobileNumber(mobileNumber);
				ad.setRole(role);

				return ad;
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

}
