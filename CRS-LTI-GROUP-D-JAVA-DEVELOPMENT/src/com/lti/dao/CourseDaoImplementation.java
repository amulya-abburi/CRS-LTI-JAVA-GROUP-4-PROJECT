package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.bean.Course;
import com.lti.bean.Student;
import com.lti.constant.SQLConstant;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.DuplicateCourseEntryException;
import com.lti.utils.DBUtils;

public class CourseDaoImplementation implements CourseDao {
	
	public void insertCourse(int id, String name, int professorId, int amount, String department, int credit,int semesterId) throws DuplicateCourseEntryException {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		
		Course c = this.fetchCourseById(id);
		if(c!=null) {
			throw new DuplicateCourseEntryException(id);
		}

		try {
			// Execute a query
			String sql = SQLConstant.INSERT_COURSE;
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setInt(3, professorId);
			stmt.setInt(4, amount);
			stmt.setString(5, department);
			stmt.setInt(6, credit);
			stmt.setInt(7, semesterId);

			int count = stmt.executeUpdate();

			if (count == 1) {
				System.out.println("course inserted successfully...");
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
			if (conn != null) {}
				// conn.close();
		}
	}
	
	public void updateCourse(Course c) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// execute query
			stmt = conn.prepareStatement(SQLConstant.UPDATE_COURSE);
			
			stmt.setString(1,c.getCourseName());
			stmt.setInt(2, c.getCourseFee());
			stmt.setString(3,c.getDepartment());
			stmt.setInt(4, c.getCredit());
			stmt.setBoolean(5,c.getStatus());
			stmt.setInt(6,c.getSemesterId());
			stmt.setInt(7,c.getProfessorId());
			stmt.setInt(8,c.getId());
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
			if (conn != null) {}
				// conn.close();
		}
	}

	public void setStatus(int id) throws CourseNotFoundException {
		
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		
		Course c = this.fetchCourseById(id);
		if(c==null) {
			throw new CourseNotFoundException(id);
		}

		try {
			// execute query
			stmt = conn.prepareStatement(SQLConstant.UPDATE_COURSE_STATUS);
			stmt.setBoolean(1, false);
			stmt.setInt(2, id);
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
			if (conn != null) {}
				// conn.close();
		}
	}
	
	public void deleteCourse(int courseId) throws CourseNotFoundException {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		
		Course c = this.fetchCourseById(courseId);
		if(c==null) {
			throw new CourseNotFoundException(courseId);
		}

		try {
			// Execute a query
			String sql = SQLConstant.DELETE_COURSE;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, courseId);
			int count = stmt.executeUpdate();

			if (count >= 1) {
				System.out.println("course deleted successfully...");
			} else {
				System.out.println("some error occur in data deletion");
			}

			// Clean-up environment
			stmt.close();
			//conn.close();

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
			if (conn != null) {}
				//conn.close();
		}
	}
	
	public List<Course> getAllCourses(int id) {
		List<Course> courseList = new ArrayList<>();

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// Execute a query
			String sql = SQLConstant.GET_ALL_COURSES_BY_SEMESTER_ID;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int courseId = rs.getInt("id");
				String courseName = rs.getString("courseName");
				int credit = rs.getInt("credit");
				int courseFee = rs.getInt("courseFee");
				boolean status = rs.getBoolean("status");
				int semesterId=rs.getInt("semesterId");
				Course course = new Course();
				
				course.setId(courseId);
				course.setCourseName(courseName);
				course.setCredit(credit);
				course.setStatus(status);
				course.setCourseFee(courseFee);
				course.setSemesterId(semesterId);
				
				courseList.add(course);
			}

			// Clean-up environment
			stmt.close();
			//conn.close();
			return courseList;

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
			if (conn != null) {}
				//conn.close();
		}
		return null;
	}
	
	public List<Course> getAllCourses() {
		List<Course> courseList = new ArrayList<>();

		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			// Execute a query
			String sql = SQLConstant.GET_ALL_COURSES;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int courseId = rs.getInt("id");
				String courseName = rs.getString("courseName");
				int credit = rs.getInt("credit");
				int courseFee = rs.getInt("courseFee");
				boolean status = rs.getBoolean("status");
				int professorId=rs.getInt("professor");
				int semesterId=rs.getInt("semesterId");
				
				Course course = new Course();
				
				course.setId(courseId);
				course.setCourseName(courseName);
				course.setCredit(credit);
				course.setStatus(status);
				course.setCourseFee(courseFee);
				course.setSemesterId(semesterId);
				course.setProfessorId(professorId);
				courseList.add(course);
				
			}

			// Clean-up environment
			stmt.close();
			//conn.close();
			return courseList;

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
			if (conn != null) {}
				//conn.close();
		}
		return null;
	}

	public Course fetchCourseById(int id) {
		// Declare the Connection or PreparedStatement variable here
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;
		
		try {
			// execute query
			String sql = SQLConstant.FETCH_COURSE_BY_ID;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int courseId = rs.getInt("id");
				String courseName = rs.getString("courseName");
				String department = rs.getString("department");
				int professorId = rs.getInt("professor");
				int credit = rs.getInt("credit");
				int courseFee = rs.getInt("courseFee");
				boolean status = rs.getBoolean("status");
				int semesterId=rs.getInt("semesterId");
				
				Course course = new Course();
				
				course.setId(courseId);
				course.setCourseName(courseName);
				course.setDepartment(department);
				course.setCredit(credit);
				course.setStatus(status);
				course.setCourseFee(courseFee);
				course.setProfessorId(professorId);
				course.setSemesterId(semesterId);
				
				return course;
			}
			// Clean-up environment
			stmt.close();
			//conn.close();
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
			if (conn != null) {}
				//conn.close();
		}
		
		return null;
	}
}
