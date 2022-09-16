package com.lti.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Payment;
import com.lti.bean.Student;
import com.lti.dao.CourseDao;
import com.lti.dao.CourseDaoImplementation;
import com.lti.dao.SemesterRegistrationDao;
import com.lti.dao.SemesterRegistrationDaoImplementation;
import com.lti.dao.PaymentDao;
import com.lti.dao.PaymentDaoImplementation;
import com.lti.dao.StudentDao;
import com.lti.dao.StudentDaoImplementation;
import com.lti.exception.CourseAlreadyRegisteredException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.SeatNotAvailableException;
import com.lti.exception.StudentCourseNotApprovedException;
import com.lti.exception.StudentNotFoundException;
import com.lti.utils.TableWithLines;

public class StudentService extends UserService implements StudentInterfaceOperation {

	
	@Override
	public void viewCourse() {
		CourseDao coursedao = new CourseDaoImplementation();
		List<Course> courseList = coursedao.getAllCourses();
		
		String[] columns = new String[]{"COURSE ID", "COURSE NAME", "COURSE FEE"};
        String[][] table = new String[courseList.size()+1][columns.length];
        table[0] = columns;

        for(int i=0; i<courseList.size(); i++){
            table[i+1][0] = courseList.get(i).getId()+"";
            table[i+1][1] = courseList.get(i).getCourseName();
            table[i+1][2] = courseList.get(i).getCourseFee()+"";
        }
        TableWithLines.tableWithGivenColumnsLength(table, true, "COURSES AVAILABLE", new int[] {20, 20, 20});
	}

	
	@Override
	public void addCourse(Student st) {
		try {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter semester id for which you want to view courses:");
			int semId=sc.nextInt();
			
			this.viewCourses(semId);
			
			System.out.println("Enter course id:");
			int courseId = sc.nextInt();
			
			System.out.println("Do you want this course as primary course(1/0)?");
			int isPrimary=sc.nextInt();
					
			SemesterRegistrationDao semesterRegistrationDao = new SemesterRegistrationDaoImplementation();
			semesterRegistrationDao.registerCourse(st.getId(), courseId, semId, isPrimary);
			
			List<Grade> gradeList = semesterRegistrationDao.getAllRegisteredCoursesByStudentId(st.getId());
			this.printList1(gradeList);
		}
		catch (CourseNotFoundException cnfEx) {
			System.out.printf("->course not found with this course id: %s<-\n", cnfEx.getCourseId());
        }
		catch (SeatNotAvailableException snaEx) {
			System.out.printf("->seats not available in this course with id: %d<-\n", snaEx.getCourseId());
		}
		catch (CourseAlreadyRegisteredException e) {
			System.out.printf("->student has already registered for this course with id: %d<-\n", e.getCourseId());
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// function to view all courses present in course catalouge for a particular semester
	public void viewCourses(int semesterId) {
		List<Course> courseList = new ArrayList<>();
		Scanner sc=new Scanner(System.in);
		
		CourseDao coursedao = new CourseDaoImplementation();
		courseList = coursedao.getAllCourses(semesterId);
		
		String[] columns = new String[]{"COURSE ID", "COURSE NAME", "COURSE FEE", "SEMESTER"};

        String[][] table = new String[courseList.size()+1][columns.length];
        table[0] = columns;

        for(int i=0; i<courseList.size(); i++){
            table[i+1][0] = courseList.get(i).getId()+"";
            table[i+1][1] = courseList.get(i).getCourseName();
            table[i+1][2] = courseList.get(i).getCourseFee()+"";
            table[i+1][3] = courseList.get(i).getSemesterId()+"";
        }

        // TableWithLines.tableWithLinesAndTitle(table, true, "COURSE LIST");
        TableWithLines.tableWithGivenColumnsLength(table, true, "COURSE LIST", new int[] {20,20,20,20});
        
	}
	
	// function to print courseList
	public String printList(List<Course> courseList) {
		String[] columns = new String[]{"COURSE ID", "COURSE NAME", "COURSE FEE"};
        String[][] table = new String[courseList.size()+1][columns.length];
        table[0] = columns;

        for(int i=0; i<courseList.size(); i++){
            table[i+1][0] = courseList.get(i).getId()+"";
            table[i+1][1] = courseList.get(i).getCourseName();
            table[i+1][2] = courseList.get(i).getCourseFee()+"";
        }
        TableWithLines.tableWithGivenColumnsLength(table, true, "ADDED COURSES", new int[] {10,20, 20});
		return "";
	}
	
	// function to print courseList
    public void printList1(List<Grade> gradeList) {
		String[] columns = new String[]{"COURSE ID", "COURSE NAME", "COURSE FEE", "PRIMARY", "APPROVED"};
        String[][] table = new String[gradeList.size()+1][columns.length];
        table[0] = columns;

        for(int i=0; i<gradeList.size(); i++){
            table[i+1][0] = gradeList.get(i).getCourse().getId()+"";
            table[i+1][1] = gradeList.get(i).getCourse().getCourseName();
            table[i+1][2] = gradeList.get(i).getCourse().getCourseFee()+"";
            table[i+1][3] = gradeList.get(i).getIsCoursePrimary()+"";
            table[i+1][4] = gradeList.get(i).getIsApprovedByAdmin()+"";
           }
        TableWithLines.tableWithGivenColumnsLength(table, true, "ADDED COURSES", new int[] {20,20,20,20,20});
	}
	
    
	@Override
	public void dropCourse(Student st) {
		
		try {
			SemesterRegistrationDao semesterRegistrationDao = new SemesterRegistrationDaoImplementation();
			printList1(semesterRegistrationDao.getAllRegisteredCoursesByStudentId(st.getId()));
			System.out.println("Enter course id which you want to drop:");
			
			Scanner sc=new Scanner(System.in);
			int id=sc.nextInt();
			
			semesterRegistrationDao.deleteRegisteredCourse(st.getId(),id);
			printList1(semesterRegistrationDao.getAllRegisteredCoursesByStudentId(st.getId()));
		}
		catch (CourseNotFoundException cnfEx) {
			System.out.printf("->course not found with this course id: %s<-\n", cnfEx.getCourseId());
        }
		catch (StudentNotFoundException snfEx) {
			System.out.printf("->Student not found with this id: %d<-\n", snfEx.getStudentId());
        }
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	
	@Override
	public void viewRegisteredCourses(Student st) {
		try {
			SemesterRegistrationDao semesterRegistrationDao=new SemesterRegistrationDaoImplementation();
			this.printList1(semesterRegistrationDao.getAllRegisteredCoursesByStudentId(st.getId()));
			
	        if(st.isCoursesApprovedByAdmin()) {
				System.out.println("->Approved by admin<-");
			}
			else {
				System.out.println("->Not approved by admin<-");
			}
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	@Override
	public void viewGrades(Student st) {
		try {
			StudentDao studentDao = new StudentDaoImplementation();
			List<Grade> gradeList = studentDao.getGradesByStudentId(st.getId());
			
			String[] columns = new String[]{"COURSE ID", "COURSE NAME", "COURSE GRADE"};

	        String[][] table = new String[gradeList.size()+1][columns.length];
	        table[0] = columns;
	        
	        for(int i=0; i<gradeList.size(); i++){
	            table[i+1][0] = gradeList.get(i).getCourse().getId()+"";
	            table[i+1][1] = gradeList.get(i).getCourse().getCourseName();
	            table[i+1][2] = gradeList.get(i).getGrade();
	        }
	        TableWithLines.tableWithGivenColumnsLength(table, true, "STUDENT GRADES", new int[] {20,20, 20});
		}
		catch (StudentNotFoundException snfEx) {
			System.out.printf("->Student not found with this id: %d<-\n", snfEx.getStudentId());
        }
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	@Override
	public void payFee(Student st){
		try {
			if(st.isCoursesApprovedByAdmin()==false) {
				throw new StudentCourseNotApprovedException(st.getId());
			}
			
			SemesterRegistrationDao semesterRegistrationDao = new SemesterRegistrationDaoImplementation();
			List<Grade> gradeList = semesterRegistrationDao.getAllRegisteredCoursesByStudentId(st.getId());
			printList2(gradeList);
			
	        int fee=0;
	        for (Grade g : gradeList) {
				fee += g.getCourse().getCourseFee();
			}
			System.out.println("Fee-------" + fee);
			
			Payment p = new Payment();
			
			PaymentInterfaceOperation paymentService = new PaymentService();
			paymentService.showPaymentMethods(p);
			
			String paymentMethod=paymentService.makePayment();
			
			PaymentDao paymentDao=new PaymentDaoImplementation();
			paymentDao.updateFeeStatus(st.getId(), paymentMethod);
		}
		catch (StudentCourseNotApprovedException e) {
			System.out.printf("->Pending admin approval for course registration for student id: %s<-\n", e.getStudentId());
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	public void printList2(List<Grade> gradeList) {
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
