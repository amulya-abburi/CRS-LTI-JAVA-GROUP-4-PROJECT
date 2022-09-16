package com.lti.service;

import java.util.Scanner;

import org.springframework.stereotype.Repository;

import com.lti.bean.Student;
import com.lti.dao.UserDao;
import com.lti.dao.UserDaoImplementation;
import com.lti.exception.DuplicateStudentEntryException;
import com.lti.exception.DuplicateUserEntryException;

@Repository
public class RegistrationService implements RegistrationInterfaceOperation {

	@Override
	public boolean registerStudent(Student student) throws DuplicateUserEntryException {

		UserDao userDao = new UserDaoImplementation();
		userDao.userRegistration(student.getName(), student.getEmailId(), student.getPassword(), student.getRole());
		return true;
	}

}
