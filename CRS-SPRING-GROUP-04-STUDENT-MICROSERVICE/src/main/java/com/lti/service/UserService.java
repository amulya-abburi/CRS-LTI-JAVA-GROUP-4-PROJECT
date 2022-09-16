package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

@Repository
public class UserService implements UserInterfaceOperation {
	@Autowired
	UserDao userDao;

	@Override
	public User userLogin(String emailId, String password, String role)
			throws AdminNotFoundException, StudentNotFoundException, ProfessorNotFoundException {

		User user = userDao.findUser(emailId, password, role);
		if (user != null) {
			return user;
		} else {
			if (role.equals("admin")) {
				throw new AdminNotFoundException(emailId);
			} else if (role.equals("student")) {
				throw new StudentNotFoundException(-1);
			} else {
				throw new ProfessorNotFoundException(1);
			}
		}
	}

	// function for logging out
	@Override
	public void logout() {
		System.out.println("User logout");
	}

	// function for resetting password
	// @Param userEmailId, userOldPassword, userNewPassword
	@Override
	public User resetPassword(String emailId, String oldPassword, String newPassword) throws UserNotFoundException {

		if (userDao.fetchAllUsers(emailId, oldPassword)) {
			User user = userDao.updatePassword(emailId, newPassword);
			return user;
		} else {
			throw new UserNotFoundException(emailId);
		}

	}

	// function for updating user info
	@Override
	public User updateInfo(String emailId, String password, long mobileNumber) throws UserNotFoundException {

		if (userDao.fetchAllUsers(emailId, password)) {
			User user = userDao.updateInfo(emailId, password, mobileNumber);
			return user;
		} else {
			throw new UserNotFoundException(emailId);
		}

	}

}
