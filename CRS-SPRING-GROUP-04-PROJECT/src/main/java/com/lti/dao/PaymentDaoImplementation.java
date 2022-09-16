package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.lti.bean.Payment;
import com.lti.bean.Student;
import com.lti.constant.SQLConstant;
import com.lti.exception.CoursesNotApprovedException;
import com.lti.exception.DuplicatePaymentEntryException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.StudentNotGradedException;
import com.lti.utils.DBUtils;

@Repository
public class PaymentDaoImplementation implements PaymentDao {

	public void generateBill(int studentId, int fee)
			throws StudentNotFoundException, DuplicatePaymentEntryException, CoursesNotApprovedException {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		StudentDao studentDao = new StudentDaoImplementation();
		Student st = studentDao.fetchStudentById(studentId);
		if (st.isCoursesApprovedByAdmin() == false) {
			throw new CoursesNotApprovedException(studentId);
		}

		Payment p = this.getPaymentStatusByStudentId(studentId);
		if (p != null) {
			throw new DuplicatePaymentEntryException(studentId);
		}

		try {
			// STEP 4: Execute a query
			String sql = SQLConstant.GENERATE_BILL;

			// execute query
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			stmt.setInt(2, fee);
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

	public Payment updateFeeStatus(int id, String paymentMethod) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// Execute a query
			String sql = SQLConstant.UPDATE_FEE_STATUS;
			stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setString(2, paymentMethod);
			stmt.setInt(3, id);
			stmt.executeUpdate();

			// Clean-up environment
			stmt.close();
			return this.getPaymentStatusByStudentId(id);
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
		return null;
	}

	public Payment getPaymentStatusByStudentId(int studentId) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			String sql = SQLConstant.GET_PAYMENT_STATUS_BY_STUDENT_ID;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String paymentMethod = rs.getString("paymentMethod");
				int amount = rs.getInt("amount");
				Boolean paymentStatus = rs.getBoolean("paymentStatus");

				Payment p = new Payment();

				p.setStudentId(studentId);
				p.setPaymentMethod(paymentMethod);
				p.setAmount(amount);
				p.setPaymentStatus(paymentStatus);

				return p;
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
