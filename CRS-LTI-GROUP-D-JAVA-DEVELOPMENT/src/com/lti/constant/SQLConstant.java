package com.lti.constant;

// Class for SQL Constants
public class SQLConstant {
	
	 //USER DAO
	 public static final String FIND_USER = "select * from user where email=? and password=? and role=?";
	 public static final String FIND_USER_BY_EMAIL = "select * from user where email=?";
	 public static final String GET_ALL_USERS = "select * from user";
	 public static final String INSERT_USER = "insert into user(email,password,role) values(?,?,?)";
	 public static final String INSERT_STUDENT = "insert into student(name,password,emailId,role) values(?,?,?,?)";
	 public static final String DELETE_USER = "delete from user where email=?";
	 
	 //ADMIN DAO
	 public static final String FIND_ADMIN = "select * from admin where adminEmailId=? and adminPassword=?";
	 public static final String APPROVE_STUDENT_REGISTRATION = "update student set isApprovedByAdmin= ? where studentid = ?";
	 public static final String APPROVE_STUDENT_COURSE_REGISTRATION = "update student set  isCourseApprovedByAdmin= ? where studentid = ?";
	 public static final String GET_ALL_ADMINS = "select * from admin";
	 public static final String ASSIGN_COURSE_TO_PROFESSOR = "update course set  professor= ? where id = ?";
	 public static final String UPDATE_STUDENT_LIST_INTO_COURSE = "update course set studentList = ? where id = ?";
	 
	 //COURSE DAO
	 public static final String INSERT_COURSE = "insert into course(id, courseName, professor, courseFee, department, credit,semesterId) values(?,?,?,?,?,?,?)";
	 public static final String UPDATE_COURSE = "update course set courseName=?,courseFee=?,department=?,credit=?,status=?,semesterId=?,professor=? where id = ?";
	 public static final String UPDATE_COURSE_STATUS = "update course set status=? where id = ?";
	 public static final String DELETE_COURSE = "delete from course where id=?";
	 public static final String FETCH_ALL_STUDENT_LIST_BY_COURSE_ID ="select studentList from course where id=?";
	 public static final String GET_ALL_COURSES_BY_SEMESTER_ID = "select * from course where semesterId=?";
	 public static final String GET_ALL_COURSES = "select * from course";
	 public static final String FETCH_COURSE_BY_ID = "select * from course where id=?";
	 
	 // PAYMENT DAO
	 public static final String GENERATE_BILL = "insert into payment(id,amount) values(?,?)";
	 public static final String UPDATE_FEE_STATUS = "update payment set paymentStatus=?,paymentMethod=? where id=?";
	 public static final String GET_PAYMENT_STATUS_BY_STUDENT_ID = "select * from payment where id=?";
	 
	 // PROFESSOR DAO
	 public static final String FIND_PROFESSOR = "select * from professor where professorEmailId=? and professorPassword=?";
	 public static final String GET_ALL_PROFESSORS = "select * from professor";
	 public static final String INSRET_PROFESSOR = "insert into professor(professorId, professorName, professorEmailId, professorPassword, role) values(?,?,?,?,?)";
	 public static final String DELETE_PROFESSOR = "delete from professor where professorId=?";
	 public static final String GET_PROFESSOR_BY_ID = "select * from professor where professorId=?";
	 public static final String GET_PROFESSOR_BY_EMAIL = "select * from professor where professorEmailId=?";
	 public static final String GET_COURSES_ASSIGNED_TO_PROFESSOR = "select * from course where professor = ?";
	 public static final String UPDATE_GRADES = "update semester_registration set grade = ? where studentId = ? and courseId = ?";
	 
	 // STUDENT DAO
	 public static final String FIND_STUDENT = "select * from student where emailId=? and password=?";
	 public static final String FETCH_STUDENT_BY_ID = "select * from student where studentid=?";
	 public static final String FETCH_STUDENT_BY_EMAIL = "select * from student where emailId=?";
	 public static final String FETCH_ALL_STUDENTS = "select * from student";
	 public static final String GET_STUDENT_COURSE_PRIMARY_LIST = "select courseListPrimary from student where studentid=?";
	 public static final String UPDATE_COURSE_INTO_PRIMARY_LIST ="update student set courseListPrimary = ? where studentid = ?";
	 public static final String GET_GRADES_BY_STUDENT_ID ="select * from semester_registration where studentID=?";
	 
	 // SEMESTER REGISTRATION DAO
	 public static final String REGISTER_COURSE ="insert into semester_registration(studentId, courseId, semesterId, isPrimaryCourse) values(?,?,?,?)";
	 public static final String DELETE_REGISTER_COURSE ="delete from semester_registration where studentId=? and courseId=?";
	 public static final String GET_ALL_REGISTERED_COURSES_BY_STUDENT_ID="select * from semester_registration where studentId=?";
	 public static final String APPROVE_STUDENT_REGISTERED_COURSE ="update semester_registration set isApprovedByAdmin=? where studentId=? and courseId=?";
	 public static final String GET_ALL_STUDENTS_BY_COURSE_ID = "select * from semester_registration where courseId=?";
	 public static final String GET_GRADE_OF_STUDENT_IN_COURSE="select * from semester_registration where studentId=? and courseId=?";
}
