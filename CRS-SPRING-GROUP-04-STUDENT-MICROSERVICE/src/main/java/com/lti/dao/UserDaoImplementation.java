package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.constant.SQLConstant;
import com.lti.exception.DuplicateStudentEntryException;
import com.lti.exception.DuplicateUserEntryException;
import com.lti.exception.ProfessorNotDeletedException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UserNotDeletedException;
import com.lti.exception.UserNotFoundException;
import com.lti.utils.DBUtils;

@Repository
public class UserDaoImplementation implements UserDao {

	public User updatePassword(String emailId, String newPassword) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			stmt = conn.prepareStatement(SQLConstant.RESET_PASSWORD);
			stmt.setString(1, newPassword);
			stmt.setString(2, emailId);
			stmt.execute();
			// Clean-up environment
			stmt.close();
			return this.findUserByEmailId(emailId);
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

	public boolean fetchAllUsers(String emailId, String password) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = SQLConstant.GET_ALL_USERS;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String emailId1 = rs.getString("email");
				String password1 = rs.getString("password");

				if (emailId.equals(emailId1) && password.equals(password1)) {
					return true;
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
		return false;

	}

	public void successUserRegistration(String name, String emailId, String password, String role) {

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// Execute a query
			String sql = SQLConstant.INSERT_USER;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, password);
			stmt.setString(3, emailId);
			stmt.setString(4, role);
			stmt.executeUpdate();

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
		} // end try
	}// end main

	public User updateInfo(String emailId, String password, long mobileNumber) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		User u = this.findUserByEmailId(emailId);
		try {
			// execute query
			stmt = conn.prepareStatement(SQLConstant.UPDATE_MOBILE);
			stmt.setLong(1, mobileNumber);
			stmt.setString(2, emailId);
			stmt.execute();
			// Clean-up environment
			stmt.close();
			return this.findUserByEmailId(emailId);
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

	public void userRegistration(String name, String emailId, String password, String role)
			throws DuplicateUserEntryException {

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		User u = this.findUserByEmailId(emailId);
		if (u != null) {
			throw new DuplicateUserEntryException(u.getEmailId(), u.getRole());
		}

		try {
			// Execute a query
			String sql = SQLConstant.INSERT_STUDENT;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, password);
			stmt.setString(3, emailId);
			stmt.setString(4, role);
			stmt.executeUpdate();

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
		} // end try
		System.out.println("Goodbye!");
	}// end main

	public User findUser(String emailId, String password, String role) {

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.FIND_USER;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailId);
			stmt.setString(2, password);
			stmt.setString(3, role);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");

				User st = new User();

				st.setId(id);
				st.setPassword(password);
				st.setEmailId(emailId);
				st.setRole(role);

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

	public void insertUser(String emailId, String password, String role) throws DuplicateUserEntryException {

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		User u = this.findUserByEmailId(emailId);
		if (u != null) {
			throw new DuplicateUserEntryException(emailId, role);
		}

		try {

			// Execute a query
			String sql = SQLConstant.INSERT_USER;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailId);
			stmt.setString(2, password);
			stmt.setString(3, role);
			stmt.executeUpdate();

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
		} // end try
	}// end main

	public User findUserByEmailId(String emailId) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.FIND_USER_BY_EMAIL;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String role = rs.getString("role");
				String password = rs.getString("password");
				long mobileNumber = rs.getLong("mobileNumber");

				User u = new User();
				u.setId(id);
				u.setEmailId(emailId);
				u.setPassword(password);
				u.setRole(role);
				u.setMobileNumber(mobileNumber);

				return u;
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

	public void deleteUser(String userEmailId) throws UserNotFoundException, UserNotDeletedException {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		User u = this.findUserByEmailId(userEmailId);
		if (u == null) {
			throw new UserNotFoundException(userEmailId);
		}

		try {
			// Execute a query
			String sql = SQLConstant.DELETE_USER;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userEmailId);
			int count = stmt.executeUpdate();

			if (count == 0) {
				throw new UserNotDeletedException(userEmailId);
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

}
