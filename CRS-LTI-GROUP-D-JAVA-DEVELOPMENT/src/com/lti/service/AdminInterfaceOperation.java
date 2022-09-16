package com.lti.service;

/**interface for admin operations*/
public interface AdminInterfaceOperation extends UserInterfaceOperation {
	
	/** method to view all the courses present in the course catalogue */
	void viewCourses();
	
	/**
	 * method to add a new course in the catalogue
	 */
	void addCourse();

	/**
	 * method to remove a course from the catalogue
	 */
	void deleteCourse();

	/**
	 * method to modify the course information/content
	 */
	void updateCourse();

	/**
	 * method to assign a professor to a course
	 */
	void assignCourseToProfessor();
	
	/**
	 * method to view all the professors
	 */
	void viewProfessorList();

	/**
	 * method to add a professor in the system
	 */
	void addProfessor();

	/**
	 * method to remove a professor from the system
	 */
	void deleteProfessor();

	/**
	 * method to approve a student registration in the system
	 */
	public void approveStudentRegistration();
	
	/**
	 * method to approve student registered courses by admin
	 */
	void approveStudentCourseRegistration();

	/**
	 * method to cancel a course for the semester if student strength < 3
	 */
	void cancelCourse();

	/**
	 * method to show report card of student
	 */
	public void generateReportCard();
	
	/**
	 * method to generate bill of student
	 */
	void generateBill();
	
}