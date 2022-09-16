package com.lti.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Payment;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.dao.AdminDao;
import com.lti.dao.AdminDaoImplementation;
import com.lti.dao.CourseDao;
import com.lti.dao.CourseDaoImplementation;
import com.lti.dao.SemesterRegistrationDao;
import com.lti.dao.SemesterRegistrationDaoImplementation;
import com.lti.dao.PaymentDao;
import com.lti.dao.PaymentDaoImplementation;
import com.lti.dao.ProfessorDao;
import com.lti.dao.ProfessorDaoImplementation;
import com.lti.dao.StudentDao;
import com.lti.dao.StudentDaoImplementation;
import com.lti.dao.UserDao;
import com.lti.dao.UserDaoImplementation;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.CoursesNotApprovedException;
import com.lti.exception.DuplicateCourseEntryException;
import com.lti.exception.DuplicatePaymentEntryException;
import com.lti.exception.DuplicateProfessorEntryException;
import com.lti.exception.DuplicateUserEntryException;
import com.lti.exception.PaymentNotMadeException;
import com.lti.exception.ProfessorNotAddedException;
import com.lti.exception.ProfessorNotDeletedException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.StudentNotGradedException;
import com.lti.utils.TableWithLines;

public class AdminService extends UserService implements AdminInterfaceOperation {
	
	// Function to view all the courses present in the course catalogue
	@Override
	public void viewCourses() {
		CourseDao coursedao = new CourseDaoImplementation();
		List<Course> courseList = coursedao.getAllCourses();
		
		if(courseList.size()==0) {
			System.out.println("->course table is empty<-");
			return;
		}

		String[] columns = new String[] { "COURSE ID", "COURSE NAME", "COURSE FEE", "SEMESTER", "PROFESSOR ID" };
		String[][] table = new String[courseList.size() + 1][columns.length];
		table[0] = columns;

		for (int i = 0; i < courseList.size(); i++) {
			table[i + 1][0] = courseList.get(i).getId() + "";
			table[i + 1][1] = courseList.get(i).getCourseName();
			table[i + 1][2] = courseList.get(i).getCourseFee() + "";
			table[i + 1][3] = courseList.get(i).getSemesterId() + "";
			table[i + 1][4] = courseList.get(i).getProfessorId() + "";
		}
		TableWithLines.tableWithGivenColumnsLength(table, true, "COURSE LIST", new int[] { 20, 20, 20, 20, 20 });
	}
	
	// Function to view all the courses present in the course catalogue for a particular semester
	// @Param semesterId
	public void viewCourses(int semesterId) {
		CourseDao coursedao = new CourseDaoImplementation();
		List<Course> courseList = coursedao.getAllCourses(semesterId);
		
		if(courseList.size()==0) {
			System.out.printf("->course table is empty for semester id %d<-\n", semesterId);
			return;
		}

		String[] columns = new String[] { "COURSE ID", "COURSE NAME", "COURSE FEE", "SEMESTER" };
		String[][] table = new String[courseList.size() + 1][columns.length];
		table[0] = columns;

		for (int i = 0; i < courseList.size(); i++) {
			table[i + 1][0] = courseList.get(i).getId() + "";
			table[i + 1][1] = courseList.get(i).getCourseName();
			table[i + 1][2] = courseList.get(i).getCourseFee() + "";
			table[i + 1][3] = courseList.get(i).getSemesterId() + "";
		}

		TableWithLines.tableWithGivenColumnsLength(table, true, "COURSE LIST", new int[] { 20, 20, 20, 20 });
	}

	// Function to add a new course in the catalogue
	// @Param courseId, courseName, courseFee, courseDepartment, courseCredit, semesterId
	// handle DuplicateCourseEntry
	@Override
	public void addCourse() {
		try {
			this.viewCourses();
			
			CourseDao coursedao = new CourseDaoImplementation();

			System.out.println("Enter course info (Add Course):");
			Scanner sc = new Scanner(System.in);

			System.out.println("Course id:");
			int id = sc.nextInt();

			System.out.println("Course name:");
			String name = sc.next();

			System.out.println("Course fee:");
			int fee = sc.nextInt();

			System.out.println("Course department:");
			String department = sc.next();

			System.out.println("Course credit:");
			int credit = sc.nextInt();

			System.out.println("Enter semester id for which you want to add the course:");
			int semesterId = sc.nextInt();

			coursedao.insertCourse(id, name, -1, fee, department, credit, semesterId);

			this.viewCourses(semesterId);
		}
		catch (DuplicateCourseEntryException dceEx) {
			System.out.printf("->course already exists with this course id: %s<-\n", dceEx.getCourseId());
        }
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}
	}
	
	// Function to remove a course from the catalogue
	// @Param courseId
	// handle CourseNotFound
	@Override
	public void deleteCourse() {

		try {
			this.viewCourses();

			System.out.println("Enter course info (Delete Course):");
			Scanner sc = new Scanner(System.in);

			System.out.println("Course id:");
			int id = sc.nextInt();

			CourseDao coursedao = new CourseDaoImplementation();
			coursedao.deleteCourse(id);

			this.viewCourses();
		}
		catch (CourseNotFoundException cnfEx) {
			System.out.printf("->course not found with this course id: %s<-\n", cnfEx.getCourseId());
        }
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}

	}
	
	// Function to modify the course information/content
	// @Param courseId, courseName, courseFee, courseDepartment, courseCredit, status, professorId semesterId
	// handle CourseNotFound
	@Override
	public void updateCourse() {
		
		try {
			this.viewCourses();
			Scanner sc = new Scanner(System.in);

			System.out.println("Course id:");
			int id = sc.nextInt();

			CourseDao coursedao = new CourseDaoImplementation();
			Course c = coursedao.fetchCourseById(id);
			
			if(c==null) {
				System.out.printf("->course not found with this course id: %s<-\n", id);
			}

			System.out.println("Enter your choice to update specific field in the selected course:");
			System.out.println("1.Course name");
			System.out.println("2.Course fee");
			System.out.println("3.Course department");
			System.out.println("4.Course credit");
			System.out.println("5.Course Status");
			System.out.println("6.Professor id");
			System.out.println("7.Semester");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter updated Course name:");
				String name = sc.next();
				c.setCourseName(name);
				break;

			case 2:
				System.out.println("Enter updated Course fee:");
				int fee = sc.nextInt();
				c.setCourseFee(fee);
				break;

			case 3:
				System.out.println("Enter updated Course department:");
				String department = sc.next();
				c.setDepartment(department);
				break;

			case 4:
				System.out.println("Enter updated Course credit:");
				int credit = sc.nextInt();
				c.setCredit(credit);
				break;

			case 5:
				System.out.println("Enter updated Course status:");
				boolean status = sc.nextBoolean();
				c.setStatus(status);
				break;

			case 6:
				System.out.println("Enter updated Professor id:");
				int professorId = sc.nextInt();
				c.setProfessorId(professorId);
				break;

			case 7:
				System.out.println("Enter updated Semester:");
				int semesterId = sc.nextInt();
				c.setSemesterId(semesterId);
				break;
			}

			coursedao.updateCourse(c);
			this.viewCourses();
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}
		
	}
	
	// Function to assign a professor to a course
	// @Param courseId, professorId
	// handle CourseNotFound, ProfessorNotFound
	@Override
	public void assignCourseToProfessor() {

		try {
			this.viewCourses();
			
			System.out.println("admin assigning course to professor");
			Scanner sc = new Scanner(System.in);

			System.out.println("Course id:");
			int courseId = sc.nextInt();

			System.out.println("Professor id:");
			int professorId = sc.nextInt();

			AdminDao adminDao = new AdminDaoImplementation();
			adminDao.assignCourseToProfessor(courseId, professorId);

			this.viewCourses();
		}
		catch (CourseNotFoundException cnfEx) {
			System.out.printf("->course not found with this course id: %s<-\n", cnfEx.getCourseId());
        }
		catch (ProfessorNotFoundException pnfEx) {
			System.out.printf("->professor not found with this id: %s<-\n", pnfEx.getProfessorId());
        }
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}
	}
	
	// Function to view all the professors
	@Override
	public void viewProfessorList() {
		ProfessorDao professordao = new ProfessorDaoImplementation();
		List<Professor> professorList = professordao.getAllProfessors();
		
		if(professorList.size()==0) {
			System.out.println("->Professor table is empty<-");
			return;
		}

		String[] columns = new String[] { "ID", "NAME", "EMAIL" };
		String[][] table = new String[professorList.size() + 1][columns.length];
		table[0] = columns;

		for (int i = 0; i < professorList.size(); i++) {
			table[i + 1][0] = professorList.get(i).getId() + "";
			table[i + 1][1] = professorList.get(i).getName();
			table[i + 1][2] = professorList.get(i).getEmailId();
		}
		TableWithLines.tableWithGivenColumnsLength(table, true, "PROFESSOR LIST", new int[] { 20, 20, 20 });
	}
	
	// Function to add a professor in the system
	// @Param professorId, professorName, professorEmail, professorPassword
	// handle DuplicateProfessorEntry
	@Override
	public void addProfessor() {

		try {
			this.viewProfessorList();

			System.out.println("Enter professor info (Add Professor):");
			Scanner sc = new Scanner(System.in);

			System.out.println("professor id:");
			int id = sc.nextInt();

			System.out.println("professor name:");
			String name = sc.next();

			System.out.println("professor email:");
			String email = sc.next();

			System.out.println("professor password:");
			String password = sc.next();

			String role = "professor";
			
			UserDao userDao = new UserDaoImplementation();
			userDao.insertUsert(email, password, role);
			
			ProfessorDao professordao = new ProfessorDaoImplementation();
			professordao.insertProfessor(id, name, email, password, role);
			this.viewProfessorList();
		}
		catch (ProfessorNotAddedException pnaEx) {
			System.out.printf("->professor not added with this email: %s<-\n", pnaEx.getProfessorEmail());
		}
		catch (DuplicateUserEntryException dueEx) {
			System.out.printf("->user already exists with this email: %s<-\n", dueEx.getUserEmail());
		}
		catch (DuplicateProfessorEntryException dpeEx) {
			System.out.printf("->professor already exists with this email: %s<-\n", dpeEx.getProfessorEmail());
        }
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}
	}
	
	// Function to remove a professor from the system
	// @Param ProfessorId
	// handle ProfessorNotFound
	@Override
	public void deleteProfessor() {

		try {
			this.viewProfessorList();
			
			System.out.println("Enter professor info (Delete Professor):");
			Scanner sc = new Scanner(System.in);

			System.out.println("professor id:");
			int professorId = sc.nextInt();
			
			ProfessorDao professordao = new ProfessorDaoImplementation();
			Professor p = professordao.getProfessorById(professorId);
			
			UserDao userDao = new UserDaoImplementation();
			userDao.deleteUser(p.getEmailId());
			
			professordao.deleteProfessor(professorId);
			this.viewProfessorList();
		}
		catch (ProfessorNotFoundException pnfEx) {
			System.out.printf("->Professor not found with this id: %d<-\n", pnfEx.getProfessorId());
        }
		catch(ProfessorNotDeletedException pndEx) {
			System.out.printf("->Professor not deleted with this id: %d<-\n", pndEx.getProfessorId());
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}
		
		
	}

	public void viewStudentList() {
		StudentDao studentDao = new StudentDaoImplementation();
		List<Student> studentList = studentDao.fetchAllStudents();

		String[] columns = new String[] { "ID", "NAME", "EMAIL", "REGI APPROVED" };
		String[][] table = new String[studentList.size() + 1][columns.length];
		table[0] = columns;

		for (int i = 0; i < studentList.size(); i++) {
			table[i + 1][0] = studentList.get(i).getId() + "";
			table[i + 1][1] = studentList.get(i).getName();
			table[i + 1][2] = studentList.get(i).getEmailId();
			if (studentList.get(i).isApprovedByAdmin()) {
				table[i + 1][3] = "Yes";
			} else {
				table[i + 1][3] = "No";
			}
		}
		TableWithLines.tableWithGivenColumnsLength(table, true, "STUDENT LIST", new int[] { 20, 20, 20, 20 });
	}

	public void viewPendingCourseApprovalStudentList() {
		StudentDao studentDao = new StudentDaoImplementation();
		List<Student> studentList = studentDao.fetchAllStudents();

		String[] columns = new String[] { "ID", "NAME", "EMAIL", "COURSES APPROVED" };
		String[][] table = new String[studentList.size() + 1][columns.length];
		table[0] = columns;

		for (int i = 0; i < studentList.size(); i++) {
			table[i + 1][0] = studentList.get(i).getId() + "";
			table[i + 1][1] = studentList.get(i).getName();
			table[i + 1][2] = studentList.get(i).getEmailId();
			if (studentList.get(i).isCoursesApprovedByAdmin()) {
				table[i + 1][3] = "Yes";
			} else {
				table[i + 1][3] = "No";
			}
		}
		TableWithLines.tableWithGivenColumnsLength(table, true, "STUDENT LIST", new int[] { 20, 20, 20, 20 });
	}
	
	// Function to approve a student registration in the system
	// @param studentId
	// handle StudentNotFound, DuplicateUserEntry
	@Override
	public void approveStudentRegistration() {
		try {
			System.out.println("admin approving student registration");
			viewStudentList();

			System.out.println("Enter Student info (Approve Student registration):");
			Scanner sc = new Scanner(System.in);

			System.out.println("student id:");
			int studentId = sc.nextInt();

			AdminDao admin = new AdminDaoImplementation();
			admin.approveStudentInDB(studentId);
			
			StudentDao studentDao = new StudentDaoImplementation();
			Student st = studentDao.fetchStudentById(studentId);
			
			UserDao userDao = new UserDaoImplementation();
			userDao.insertUsert(st.getEmailId(), st.getPassword(), st.getRole());

			viewStudentList();
		}
		catch (StudentNotFoundException snfEx) {
			System.out.printf("->Student not found with this id: %d<-\n", snfEx.getStudentId());
        }
		catch (DuplicateUserEntryException dueEx) {
			System.out.println("user already exists with this email id: " + dueEx.getUserEmail() + " who is: " + dueEx.getRole());
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}
	}

	// Function to approve student registered courses by admin
	// @param studentId
	// handle StudentNotFound, NoCourseFoundForStudent
	@Override
	public void approveStudentCourseRegistration() {
		try {
			System.out.println("admin approving student courses");
			viewPendingCourseApprovalStudentList();

			System.out.println("Enter Student info (Approve Student Course registration):");
			Scanner sc = new Scanner(System.in);

			System.out.println("student id:");
			int studentId = sc.nextInt();

			AdminDao adminDao = new AdminDaoImplementation();
			adminDao.approveStudentCourseRegistration(studentId);

			SemesterRegistrationDao semesterRegistrationDao = new SemesterRegistrationDaoImplementation();
			List<Grade> gradeList = semesterRegistrationDao.getAllRegisteredCoursesByStudentId(studentId);
			
			for(Grade grade : gradeList) {
				System.out.println("student is getting registered for: " + grade.getCourse().getCourseName());
				semesterRegistrationDao.approveStudentRegisteredCourse(studentId, grade.getCourse().getId());
			}
			viewPendingCourseApprovalStudentList();
		}
		catch (StudentNotFoundException snfEx) {
			System.out.printf("->Student not found with this id: %d<-\n", snfEx.getStudentId());
        }
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}
	}
	
	// Function to cancel a course for the semester if student strength < 3
	// @param courseId
	// handle CourseNotFound, NoStudentFoundIntoCourse
	@Override
	public void cancelCourse() {
		try {
			CourseDao coursedao = new CourseDaoImplementation();
			this.viewCourses();

			System.out.println("Enter course id which you want to cancel:");

			Scanner sc = new Scanner(System.in);
			int courseId = sc.nextInt();
			
			SemesterRegistrationDao semesterRegistrationDao = new SemesterRegistrationDaoImplementation();
			List<Student> studentList = semesterRegistrationDao.getAllStudentsByCourseId(courseId);

			int number_of_students = studentList.size();
			if (number_of_students >= 3) {
				System.out.println("You cannot cancel this course: number of students >= 3");
			} else {
				coursedao.setStatus(courseId);
				System.out.println("admin cancelling course: number of students < 3");
			}
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (CourseNotFoundException cnfEx) {
			System.out.printf("->course not found with this course id: %s<-\n", cnfEx.getCourseId());
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}
	}

	public Student findStudent(int id, List<Student> studentList) {
		for (Student s : studentList) {
			if (s.getId() == id) {
				return s;
			}
		}
		return null;
	}
	
	// Function to show report card of student
	// @param studentId
	// handle StudentNotFound
	public void showReportCard(Student st) {
		
		try {
			String title = "College Name (2022-23)";
			int[] colsLength = new int[] { 20, 20, 20 };

			// fill map with columns length
			Map<Integer, Integer> columnLengths = TableWithLines.fillMapWithColumnsLength(colsLength);
			String line = TableWithLines.getBorderLine(columnLengths);
			String titleLine = TableWithLines.getTitleLine(title, line.length() - 1);

			// print college title and student information row
			System.out.print(line);
			System.out.print(titleLine);
			System.out.print(line);

			String[] columns1 = new String[] { "SID: " + st.getId(), "", "NAME: " + st.getName() };
			System.out.printf("| %-20s   %-20s   %-20s |\n", (Object[]) columns1);
			
			StudentDao studentDao = new StudentDaoImplementation();
			List<Grade> gradeList = studentDao.getGradesByStudentId(st.getId());

			// print grade table
			String[] columns = new String[] { "COURSE ID", "COURSE NAME", "COURSE GRADE" };
			String[][] table = new String[gradeList.size() + 1][columns.length];
			table[0] = columns;

			for(int i=0; i<gradeList.size(); i++){
	            table[i+1][0] = gradeList.get(i).getCourse().getId()+"";
	            table[i+1][1] = gradeList.get(i).getCourse().getCourseName();
	            table[i+1][2] = gradeList.get(i).getGrade();
	        }
			TableWithLines.tableWithGivenColumnsLength(table, true, "STUDENT GRADES", colsLength);
		}
		catch (StudentNotFoundException snfEx) {
			System.out.printf("->Student not found with this id: %d<-\n", snfEx.getStudentId());
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	// Function to generate the report card for students for the semester
	// @param studentId
	// handle StudentNotFound
	@Override
	public void generateReportCard() {
		
		try {
			System.out.println("admin generating report Card...");

			viewStudentList();

			System.out.println("Enter Student info (Generate Report Card):");

			Scanner sc = new Scanner(System.in);

			System.out.println("student id:");
			int studentId = sc.nextInt();
			
			StudentDao studentDao = new StudentDaoImplementation();
			
			if(studentDao.isStudentGraded(studentId)==false) {
				throw new StudentNotGradedException(studentId);
			}
			
			PaymentDao paymentDao = new PaymentDaoImplementation();
			boolean paymentStatus = false;
			Payment p = paymentDao.getPaymentStatusByStudentId(studentId);
			if(p!=null && p.isPaymentStatus()==true) {
				paymentStatus = true;
			}
			Student st = new Student();
			st = studentDao.fetchStudentById(studentId);
			
			if(st==null) {
				throw new StudentNotFoundException(studentId);
			}
			else if(st.isCoursesApprovedByAdmin()==false) {
				throw new CoursesNotApprovedException(studentId);
			}
			else if(paymentStatus==false) {
				throw new PaymentNotMadeException(studentId);
			}
			else {
				showReportCard(st);
			}
		}
		catch (StudentNotFoundException snfEx) {
			System.out.printf("->Student not found with this id: %d<-\n", snfEx.getStudentId());
        }
		catch (CoursesNotApprovedException e) {
			System.out.printf("->courses not approved for student id: %d<-\n", e.getStudentId());
		}
		catch (StudentNotGradedException e) {
			System.out.printf("->Student with id: %d not graded in all courses<-\n", e.getStudentId());
		}
		catch (PaymentNotMadeException e) {
			System.out.printf("->Student with id: %d has not made payment yet<-\n", e.getStudentId());
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}

	}
	
	// Function to generate bill of student
	// @param studentId
	// handle StudentNotFound, NoCourseFoundForStudent, DuplicatePaymentEntry
	@Override
	public void generateBill() {
		
		try {
			StudentDao stu = new StudentDaoImplementation();
			this.viewStudentList();

			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter student id for whom you want to generate bill:");
			int studentId = sc.nextInt();

			SemesterRegistrationDao semesterRegistrationDao = new SemesterRegistrationDaoImplementation();
			List<Grade> gradeList = semesterRegistrationDao.getAllRegisteredCoursesByStudentId(studentId);
			
			System.out.println("Please pay the following amount:-");
			
			int fee = 0;
			for (Grade g : gradeList) {
				fee += g.getCourse().getCourseFee();
			}

			PaymentDao paymentdao = new PaymentDaoImplementation();
			paymentdao.generateBill(studentId, fee);
			
			printList(gradeList);
			System.out.println("Fee-------" + fee);
		}
		catch (StudentNotFoundException snfEx) {
			System.out.printf("->Student not found with this id: %d<-\n", snfEx.getStudentId());
        }
		catch (DuplicatePaymentEntryException dpeEx) {
			System.out.printf("->already generated bill for this student: %s<-\n", dpeEx.getStudentId());
		}
		catch (CoursesNotApprovedException e) {
			System.out.printf("->courses not approved for student id: %d<-\n", e.getStudentId());
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println("->unknown error<-");
		}
		
	}

	public void printList(List<Grade> gradeList) {
		String[] columns = new String[] { "COURSE ID", "COURSE NAME", "COURSE FEE" };

		String[][] table = new String[gradeList.size() + 1][columns.length];
		table[0] = columns;

		for (int i = 0; i < gradeList.size(); i++) {
            table[i+1][0] = gradeList.get(i).getCourse().getId()+"";
            table[i+1][1] = gradeList.get(i).getCourse().getCourseName();
            table[i+1][2] = gradeList.get(i).getCourse().getCourseFee()+"";
		}

		TableWithLines.tableWithGivenColumnsLength(table, true, "REGISTERED COURSES", new int[] { 20, 20, 20 });
	}
}
