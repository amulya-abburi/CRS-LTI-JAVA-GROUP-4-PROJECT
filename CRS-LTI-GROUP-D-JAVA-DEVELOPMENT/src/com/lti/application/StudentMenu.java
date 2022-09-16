package com.lti.application;

import java.util.List;
import java.util.Scanner;

import com.lti.bean.Course;
import com.lti.bean.Student;
import com.lti.service.StudentInterfaceOperation;
import com.lti.service.StudentService;

public class StudentMenu {
	
	/** method to show student menu */
	public static void showStudentMenu() {
		System.out.println("\n********Welcome to Student Menu********");
		System.out.println("Please select from following options");
		System.out.println("1. View Courses");
		System.out.println("2. Add Course");
		System.out.println("3. Drop Course");
		System.out.println("4. View Registered Courses");
		System.out.println("5. View Grade");
		System.out.println("6. payFee");
		System.out.println("7. Logout");
		System.out.println("***************************************");
	}
	
	/** method to call all student operations */
	public void studentMenu(Student st) {
		
		StudentInterfaceOperation student = new StudentService();
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		while (flag) {
			
			// show student menu
			showStudentMenu();
			System.out.println("Please enter your choice:");

			// check if input is integer
			if (sc.hasNextInt() == false) {
				System.out.println("->Input should be integer<-");
				sc.nextLine();
				continue;
			}
			int input = sc.nextInt();
			
			switch (input) {
			case 1:
				student.viewCourse();
				break;
			case 2:
				student.addCourse(st);
				break;
			case 3:
				student.dropCourse(st);
				break;
			case 4:
				student.viewRegisteredCourses(st);
				break;
			case 5:
				student.viewGrades(st);
				break;
			case 6:
				student.payFee(st);
				break;
			case 7:
				student.logout();
				flag = false;
				break;
			default:
				System.out.println("->Please enter valid choice<-");
				break;
			}
		}
	}
	
}
