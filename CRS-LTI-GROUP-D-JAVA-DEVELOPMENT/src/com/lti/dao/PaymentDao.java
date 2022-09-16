package com.lti.dao;

import com.lti.bean.Payment;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.CoursesNotApprovedException;
import com.lti.exception.DuplicatePaymentEntryException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.StudentNotGradedException;

/**interface for payment dao operations*/

public interface PaymentDao {
	
	/**
	 * method for generate bill for student for registered courses
	 * 
	 * @param id
	 * @param fee
	 * 
	 * @throws StudentNotFoundException
	 * @throws DuplicatePaymentEntryException
	 * @throws CoursesNotApprovedException
	 */
	public void generateBill(int id,int fee) throws StudentNotFoundException, DuplicatePaymentEntryException, CoursesNotApprovedException;
	/**
	 * method for updating fee status of student (paid or not)
	 * 
	 * @param id
	 * @param paymentMethod
	 */
	public void updateFeeStatus(int id,String paymentMethod);
	
	/**
	 * method for getting payment status of a student (paid or not)
	 * 
	 * @param id
	 * @param fee
	 * 
	 * @throws StudentNotFoundException
	 * @throws DuplicatePaymentEntryException
	 * @throws CoursesNotApprovedException
	 */
	public Payment getPaymentStatusByStudentId(int studentId);
}