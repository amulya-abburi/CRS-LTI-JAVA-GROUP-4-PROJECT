package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.bean.Admin;
import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.constant.SQLConstant;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.DuplicateProfessorEntryException;
import com.lti.exception.GradeAlreadyAssignedException;
import com.lti.exception.ProfessorNotAddedException;
import com.lti.exception.ProfessorNotDeletedException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentCourseNotApprovedException;
import com.lti.exception.StudentNotFoundException;
import com.lti.utils.DBUtils;

@Repository
public class ProfessorDaoImplementation implements ProfessorDao {

	public List<Professor> getAllProfessors() {
		List<Professor> professorList = new ArrayList<>();

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// Execute a query
			String sql = SQLConstant.GET_ALL_PROFESSORS;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("professorId");
				String professorName = rs.getString("professorName");
				String professorPassword = rs.getString("professorPassword");
				String professorEmailId = rs.getString("professorEmailId");
				String role = rs.getString("role");

				Professor pf = new Professor();

				pf.setId(id);
				pf.setName(professorName);
				pf.setPassword(professorPassword);
				pf.setEmailId(professorEmailId);
				pf.setRole(role);

				professorList.add(pf);
			}

			// Clean-up environment
			stmt.close();
			// conn.close();
			return professorList;
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

	public Professor insertProfessor(int professorId, String professorName, String professorEmailId,
			String professorPassword, String role) throws DuplicateProfessorEntryException, ProfessorNotAddedException {

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		Professor p = this.getProfessorByEmail(professorEmailId);
		if (p != null) {
			throw new DuplicateProfessorEntryException(p.getEmailId());
		}

		try {
			// Execute a query
			String sql = SQLConstant.INSRET_PROFESSOR;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);
			stmt.setString(2, professorName);
			stmt.setString(3, professorEmailId);
			stmt.setString(4, professorPassword);
			stmt.setString(5, role);
			int count = stmt.executeUpdate();

			if (count == 1) {
				System.out.println("professor inserted successfully...");
			} else {
				throw new ProfessorNotAddedException(professorEmailId);
			}

			// Clean-up environment
			stmt.close();
			// conn.close();
			return this.getProfessorById(professorId);

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

	public Professor deleteProfessor(int professorId) throws ProfessorNotFoundException, ProfessorNotDeletedException {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		Professor p = this.getProfessorById(professorId);
		if (p == null) {
			throw new ProfessorNotFoundException(professorId);
		}

		try {
			// Execute a query
			String sql = SQLConstant.DELETE_PROFESSOR;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);
			int count = stmt.executeUpdate();

			if (count == 0) {
				throw new ProfessorNotDeletedException(professorId);
			}
			// Clean-up environment
			stmt.close();
			return p;
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

	public Professor getProfessorById(int professorId) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// Execute a query
			String sql = SQLConstant.GET_PROFESSOR_BY_ID;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);
			ResultSet rs = stmt.executeQuery();

			Professor pf = new Professor();

			while (rs.next()) {
				String professorName = rs.getString(2);
				String professorPassword = rs.getString(3);
				String professorEmailId = rs.getString(4);
				String role = rs.getString(6);

				pf.setId(professorId);
				pf.setName(professorName);
				pf.setPassword(professorPassword);
				pf.setEmailId(professorEmailId);
				pf.setRole(role);

				return pf;
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

	public Professor getProfessorByEmail(String professorEmailId) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// Execute a query
			String sql = SQLConstant.GET_PROFESSOR_BY_EMAIL;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, professorEmailId);
			ResultSet rs = stmt.executeQuery();

			Professor pf = new Professor();

			while (rs.next()) {
				int professorId = rs.getInt("professorId");
				String professorName = rs.getString("professorName");
				String professorPassword = rs.getString("professorPassword");
				String role = rs.getString("role");

				pf.setId(professorId);
				pf.setName(professorName);
				pf.setPassword(professorPassword);
				pf.setEmailId(professorEmailId);
				pf.setRole(role);

				return pf;
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

	public List<Course> getCoursesAssigned(int professorId) throws ProfessorNotFoundException {
		List<Course> courseList = new ArrayList<>();

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		Professor p = this.getProfessorById(professorId);
		if (p == null) {
			throw new ProfessorNotFoundException(professorId);
		}

		try {
			// Execute a query
			String sql = SQLConstant.GET_COURSES_ASSIGNED_TO_PROFESSOR;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int courseId = rs.getInt("id");
				String courseName = rs.getString("courseName");
				int credit = rs.getInt("credit");
				int courseFee = rs.getInt("courseFee");
				boolean status = rs.getBoolean("status");

				Course course = new Course();

				course.setId(courseId);
				course.setCourseName(courseName);
				course.setCredit(credit);
				course.setStatus(status);
				course.setCourseFee(courseFee);
				course.setDepartment(rs.getString("department"));
				course.setSemesterId(rs.getInt("semesterId"));

				courseList.add(course);
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

		return courseList;
	}

	public void addGrades(int studentId, int courseId, String grade) throws StudentNotFoundException,
			CourseNotFoundException, GradeAlreadyAssignedException, StudentCourseNotApprovedException {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		StudentDao studentDao = new StudentDaoImplementation();
		Student st = studentDao.fetchStudentById(studentId);
		if (st == null) {
			throw new StudentNotFoundException(studentId);
		}
		if (st.isCoursesApprovedByAdmin() == false) {
			throw new StudentCourseNotApprovedException(st.getId());
		}

		CourseDao courseDao = new CourseDaoImplementation();
		Course c = courseDao.fetchCourseById(courseId);
		if (c == null) {
			throw new CourseNotFoundException(courseId);
		}

		SemesterRegistrationDao gradeCardDao = new SemesterRegistrationDaoImplementation();
		String grade1 = gradeCardDao.getGradeOfStudentInCourse(studentId, courseId);

		if (grade1 != null) {
			throw new GradeAlreadyAssignedException(studentId, courseId, grade);
		}

		try {

			// Execute a query
			String sql = SQLConstant.UPDATE_GRADES;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, grade);
			stmt.setInt(2, studentId);
			stmt.setInt(3, courseId);

			boolean isUpdated = stmt.execute();

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

	public Professor findProfessor(String emailId, String password) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.FIND_PROFESSOR;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailId);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("professorId");
				String adminName = rs.getString("professorName");
				String address = rs.getString("address");
				long mobileNumber = rs.getLong("mobileNumber");
				String role = rs.getString("role");

				Professor pf = new Professor();

				pf.setId(id);
				pf.setEmailId(emailId);
				pf.setPassword(password);
				pf.setName(adminName);
				pf.setAddress(address);
				pf.setMobileNumber(mobileNumber);
				pf.setRole(role);

				return pf;
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
