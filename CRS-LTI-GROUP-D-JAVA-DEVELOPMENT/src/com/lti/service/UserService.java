package com.lti.service;

import java.util.List;
import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.dao.AdminDao;
import com.lti.dao.AdminDaoImplementation;
import com.lti.dao.ProfessorDao;
import com.lti.dao.ProfessorDaoImplementation;
import com.lti.dao.StudentDao;
import com.lti.dao.StudentDaoImplementation;
import com.lti.dao.UserDao;
import com.lti.dao.UserDaoImplementation;
import com.lti.exception.AdminNotFoundException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UserNotFoundException;
import com.lti.bean.Professor;
import com.lti.bean.Admin;

public class UserService implements UserInterfaceOperation {

	
	@Override
	public Student studentLogin(String emailId, String password, String role) {
		
		try {
			StudentDao studentDao = new StudentDaoImplementation();
			UserDao userDao = new UserDaoImplementation();
			
			User user = userDao.findUser(emailId, password, role);
			if(user!=null){
				Student st = studentDao.findStudent(emailId, password);
				if(st!=null) {
					System.out.println("->student login successfull<-");
					return st;
				}
				else {
					throw new StudentNotFoundException(-1);
				}
			}
			else{
				throw new UserNotFoundException(emailId);
			}
		}
		catch (UserNotFoundException unfEx) {
			System.out.println("user not found with these credentials");
        }
		catch (StudentNotFoundException snfEx) {
			System.out.println("student not found with these credentials");
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	
	@Override
	public Professor professorLogin(String emailId, String password, String role) {
		try {
			ProfessorDao professorDao = new ProfessorDaoImplementation();
			UserDao userDao = new UserDaoImplementation();
			
			User user = userDao.findUser(emailId, password, role);
			if(user!=null){
				Professor pf = professorDao.findProfessor(emailId, password);
				if(pf!=null) {
					System.out.println("->professor login successfull<-");
					return pf;
				}
				else {
					throw new ProfessorNotFoundException(-1);
				}
			}
			else{
				throw new UserNotFoundException(emailId);
			}
		}
		catch (UserNotFoundException unfEx) {
			System.out.println("user not found with these credentials");
        }
		catch (ProfessorNotFoundException snfEx) {
			System.out.println("professor not found with these credentials");
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	
	@Override
	public Admin adminLogin(String emailId, String password, String role) {
		try {
			AdminDao adminDao = new AdminDaoImplementation();
			UserDao userDao = new UserDaoImplementation();
			
			User user = userDao.findUser(emailId, password, role);
			if(user!=null){
				Admin ad = adminDao.findAdmin(emailId, password);
				if(ad!=null) {
					System.out.println("->Admin login successfull<-");
					return ad;
				}
				else {
					throw new AdminNotFoundException(emailId);
				}
			}
			else{
				throw new UserNotFoundException(emailId);
			}
		}
		catch (UserNotFoundException unfEx) {
			System.out.println("user not found with these credentials");
        }
		catch (AdminNotFoundException snfEx) {
			System.out.println("admin not found with these credentials");
        }
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return null;
	}

	// function for logging out
	@Override
	public void logout() {
		System.out.println("User logout");
	}

	// function for resetting password
	// @Param userEmailId, userOldPassword, userNewPassword
	@Override
	public void resetPassword() {
		System.out.println("reset password");
	}
	
	// function for updating user info
	@Override
	public void updateInfo() {
		System.out.println("user to update personal details");
	}

}
