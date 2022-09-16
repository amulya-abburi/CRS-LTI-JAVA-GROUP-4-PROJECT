package com.lti.application;

import java.util.List;
import java.util.Scanner;

import com.lti.bean.Admin;
import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.service.AdminInterfaceOperation;
import com.lti.service.AdminService;

public class AdminMenu {
	
	/** method to show admin menu */
	public static void showAdminMenu() {
		System.out.println("\n********Welcome to Admin Menu********");
		System.out.println("Please select from following options");
		System.out.println("1. View Courses");
		System.out.println("2. Add Course ");
		System.out.println("3. Delete Course");
		System.out.println("4. Update Course");
		System.out.println("5. Assign Course to Professor");
		System.out.println("6. View Professor List");
		System.out.println("7. Add Professor");
		System.out.println("8. Delete Professor");
		System.out.println("9. Approve student registration");
		System.out.println("10. Cancel course");
		System.out.println("11. Generate reportCard");
		System.out.println("12. Generate bill for student");
		System.out.println("13. Approve student course registration");
		System.out.println("14. Logout");
		System.out.println("***************************************");
	}
	
	/** method to call all admin operations */
	public void adminMenu(Admin ad) {
		
		AdminInterfaceOperation admin = new AdminService();
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		while (flag) {
			
			// show admin menu
			showAdminMenu();
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
				admin.viewCourses();
				break;
			case 2:
				admin.addCourse();
				break;
			case 3:
				admin.deleteCourse();
				break;
			case 4:
				admin.updateCourse();
				break;
			case 5:
				admin.assignCourseToProfessor();
				break;
			case 6:
				admin.viewProfessorList();
				break;
			case 7:
				admin.addProfessor();
				break;
			case 8:
				admin.deleteProfessor();
				break;
			case 9:
				admin.approveStudentRegistration();
				break;
			case 10:
				admin.cancelCourse();
				break;
			case 11:
				admin.generateReportCard();
				break;
			case 12:
				admin.generateBill();
				break;
			case 13:
				admin.approveStudentCourseRegistration();
				break;
			case 14:
				admin.logout();
				flag = false;
				break;
			default:
				System.out.println("->please enter valid choice<-");
				break;
			}

		}
	}
}
