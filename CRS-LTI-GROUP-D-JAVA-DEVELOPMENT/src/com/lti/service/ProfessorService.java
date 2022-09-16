package com.lti.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.dao.CourseDao;
import com.lti.dao.CourseDaoImplementation;
import com.lti.dao.SemesterRegistrationDao;
import com.lti.dao.SemesterRegistrationDaoImplementation;
import com.lti.dao.ProfessorDao;
import com.lti.dao.ProfessorDaoImplementation;
import com.lti.dao.StudentDao;
import com.lti.dao.StudentDaoImplementation;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.GradeAlreadyAssignedException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentCourseNotApprovedException;
import com.lti.exception.StudentNotFoundException;
import com.lti.utils.TableWithLines;
import com.lti.bean.Course;

public class ProfessorService extends UserService implements ProfessorInterfaceOperation {
	
	
	@Override
	public void viewCoursesAssigned(Professor pf) {
		try {
			ProfessorDao professorDao = new ProfessorDaoImplementation();
			List<Course> coursesAssigned = professorDao.getCoursesAssigned(pf.getId());
			
			String[] columns = new String[]{"COURSE ID", "COURSE NAME"};
	        String[][] table = new String[coursesAssigned.size()+1][columns.length];
	        table[0] = columns;

	        for(int i=0; i<coursesAssigned.size(); i++){
	            table[i+1][0] = coursesAssigned.get(i).getId()+"";
	            table[i+1][1] = coursesAssigned.get(i).getCourseName();
	        }
	        TableWithLines.tableWithGivenColumnsLength(table, true, "ASSIGNED COURSES", new int[] {20,20});
		}
		catch (ProfessorNotFoundException pnfEx) {
			System.out.printf("->professor not found with this id: %s<-\n", pnfEx.getProfessorId());
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	@Override
	public void viewEnrolledStudent(Professor pf) {
		try {
			viewCoursesAssigned(pf);
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Course id:");
			int courseId = sc.nextInt();
			
			SemesterRegistrationDao semesterRegistrationDao = new SemesterRegistrationDaoImplementation();
			List<Student> studentList = semesterRegistrationDao.getAllStudentsByCourseId(courseId);
			
			if(studentList.size()==0) {
				System.out.printf("->student list is empty in course with course id: %s<-\n", courseId);
			}
			showEnrolledStudentsByProfessor(studentList);
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	@Override
	public void addGrades(Professor pf) {
		try {
			System.out.println("professor adding grades of student...");
			
			this.viewCoursesAssigned(pf);
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Course id:");
			int courseId = sc.nextInt();
			
			SemesterRegistrationDao semesterRegistrationDao = new SemesterRegistrationDaoImplementation();
			List<Student> studentList = semesterRegistrationDao.getAllStudentsByCourseId(courseId);
			showEnrolledStudentsByProfessor(studentList);
			
			System.out.println("Student id:");
			int studentId = sc.nextInt();
			
			System.out.println("Student grade:");
			String studentGrade = sc.next();
			
			ProfessorDao professorDao = new ProfessorDaoImplementation();
			professorDao.addGrades(studentId, courseId, studentGrade);
			
			StudentDao stu=new StudentDaoImplementation();
			
			StudentService studentService=new StudentService();
			studentService.viewGrades(stu.fetchStudentById(studentId));
		}
		catch (StudentNotFoundException snfEx) {
			System.out.printf("->Student not found with this id: %d<-\n", snfEx.getStudentId());
        }
		catch (CourseNotFoundException cnfEx) {
			System.out.printf("->course not found with this course id: %s<-\n", cnfEx.getCourseId());
        }
		catch (StudentCourseNotApprovedException e) {
			System.out.printf("->Pending admin approval for course registration for student id: %s<-\n", e.getStudentId());
		}
		catch (GradeAlreadyAssignedException gaaEx) {
			System.out.printf("->student id: %d is already graded for this course id: %d<-\n", gaaEx.getStudentId(), gaaEx.getCourseId());
		}
		catch (InputMismatchException inEx) {
			System.out.println("->input type is not acceptable<-");
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	// function to show enrolled students into a course
	private void showEnrolledStudents(Course c) {
		
		List<Student> studentsList = c.getStudentList();
		
		String[] columns = new String[]{"ID", "NAME", "EMAIL", "GRADE"};
        String[][] table = new String[studentsList.size()+1][columns.length];
        table[0] = columns;

        for(int i=0; i<studentsList.size(); i++){
            table[i+1][0] = studentsList.get(i).getId()+"";
            table[i+1][1] = studentsList.get(i).getName();
            table[i+1][2] = studentsList.get(i).getEmailId();
            table[i+1][3] = c.getStudentGrades().get(studentsList.get(i).getId())+"";
        }
        TableWithLines.tableWithGivenColumnsLength(table, true, "ENROLLED STUDENTS (" + c.getCourseName() + ")", new int[] {20,20,20,20});		
	}
	
	// function to show enrolled students into a course
	private void showEnrolledStudentsByProfessor(List<Student> studentList) {
		String[] columns = new String[]{"ID", "NAME", "EMAIL"};
        String[][] table = new String[studentList.size()+1][columns.length];
        table[0] = columns;

        for(int i=0; i<studentList.size(); i++){
            table[i+1][0] = studentList.get(i).getId()+"";
            table[i+1][1] = studentList.get(i).getName();
            table[i+1][2] = studentList.get(i).getEmailId();
        }
        TableWithLines.tableWithGivenColumnsLength(table, true, "ENROLLED STUDENTS", new int[] {20,20,20});		
	}

}
