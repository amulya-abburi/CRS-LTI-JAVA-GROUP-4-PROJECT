package com.lti.application;

import java.util.Scanner;

import com.lti.bean.Admin;
import com.lti.bean.Payment;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.dao.PaymentDao;
import com.lti.dao.PaymentDaoImplementation;
import com.lti.service.RegistrationInterfaceOperation;
import com.lti.service.RegistrationService;
import com.lti.service.UserInterfaceOperation;
import com.lti.service.UserService;

public class CRSApplication {

	/** method to show main menu */
	public static void showMainMenu() {
		System.out.println("\n*****Welcome to the CRS application****");
		System.out.println("Please select from following options");
		System.out.println("1.Login");
		System.out.println("2.Registration of Student");
		System.out.println("3.Update Password");
		System.out.println("4.Exit");
		System.out.println("***************************************");
	}

	/** method to call all user operations */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserInterfaceOperation user = new UserService();
		Scanner sc = new Scanner(System.in);

		boolean flag = true;
		while (flag) {

			// show main menu
			showMainMenu();
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
				System.out.println("Enter email id");
				String email = sc.next();

				System.out.println("Enter your password");
				String password = sc.next();

				System.out.println("Enter your role (student/admin/professor)");
				String role = sc.next();

				if (role.equals("student")) {
					Student st = user.studentLogin(email, password, role);
					if (st != null) {
						StudentMenu studentMenu = new StudentMenu();
						studentMenu.studentMenu(st);
					} else {
						System.out.println("->Please enter right credentials<-");
					}
				} else if (role.equals("admin")) {
					Admin ad = user.adminLogin(email, password, role);
					if (ad != null) {
						AdminMenu adminMenu = new AdminMenu();
						adminMenu.adminMenu(ad);
					} else {
						System.out.println("->Please enter right credentials<-");
					}
				} else if (role.equals("professor")) {
					Professor pf = user.professorLogin(email, password, role);
					if (pf != null) {
						ProfessorMenu professorMenu = new ProfessorMenu();
						professorMenu.professorMenu(pf);
					} else {
						System.out.println("->Please enter right credentials<-");
					}
				} else {
					System.out.println("*******************************");
					System.out.println("Please enter right credentials!");
					System.out.println("*******************************");
				}
				break;
			case 2:
				RegistrationInterfaceOperation register = new RegistrationService();
				register.registerStudent();
				break;
			case 3:
				System.out.println("Update password ");
				break;
			case 4:
				System.out.println("Exit");
				flag = false;
				break;
			default:
				System.out.println("->please Enter valid input<-");
				break;
			}
		}

	}

}
