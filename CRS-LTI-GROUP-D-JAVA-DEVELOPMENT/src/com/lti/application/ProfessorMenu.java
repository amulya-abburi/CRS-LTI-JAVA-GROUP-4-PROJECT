package com.lti.application;

import java.util.List;
import java.util.Scanner;

import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.service.ProfessorInterfaceOperation;
import com.lti.service.ProfessorService;

public class ProfessorMenu {
	
	/** Method to show professor menu */
	public static void showProfessorMenu() {
		System.out.println("\n*******Welcome to Professor Menu*******");
		System.out.println("Please select from following options");
		System.out.println("1. View Courses Assigned ");
		System.out.println("2. View enrolled students");
		System.out.println("3. Add Grades");
		System.out.println("4. Logout");
		System.out.println("***************************************");
	}
	
	/** method to call all professor operations */
	public void professorMenu(Professor pf) {
		
		ProfessorInterfaceOperation professor = new ProfessorService();
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		while (flag) {
			
			// show professor menu
			showProfessorMenu();
			System.out.println("please enter your choice:");
			
			//check if input is integer
			if(sc.hasNextInt()==false) {
				System.out.println("->Input should be integer<-");
				sc.nextLine();
				continue;
			}
			int input = sc.nextInt();
			
			switch (input) {
			case 1:
				professor.viewCoursesAssigned(pf);
				break;
			case 2:
				professor.viewEnrolledStudent(pf);
				break;
			case 3:
				professor.addGrades(pf);
				break;
			case 4:
				professor.logout();
				flag = false;
				break;
			default:
				System.out.println("->Please enter valid choice<-");
				break;
			}

		}
	}
}
