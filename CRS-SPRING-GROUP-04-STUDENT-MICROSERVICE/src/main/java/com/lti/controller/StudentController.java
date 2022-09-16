package com.lti.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Payment;
import com.lti.exception.CourseAlreadyRegisteredException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.DuplicatePaymentEntryException;
import com.lti.exception.SeatNotAvailableException;
import com.lti.exception.StudentCourseNotApprovedException;
import com.lti.exception.StudentNotFoundException;
import com.lti.service.StudentInterfaceOperation;
@RestController
public class StudentController {
	
	@Autowired
	StudentInterfaceOperation studentInterfaceOperation;
	
	/**
     * method to view courses in a semester
     *
     * @param semesterId
     *
     * @return list of courses
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/getAllCourses/{semesterId}")
	@ResponseBody
	public List getAllCourses(@PathVariable int semesterId){
		List<Course> courseList=studentInterfaceOperation.viewCourses(semesterId);
		return courseList;
	}
	 /**
     * method to add course in primary/secondary list
     *
     * @param studentId
     * @param semesterId
     * @param courseId
     * @param isCoursePrimary
     *  
     * @throws CourseNotFoundException
     * @throws SeatNotAvailableException
     * @throws CourseAlreadyRegisteredException
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/registerCourse/{studentId}/{semesterId}/{courseId}/{isCoursePrimary}")
	@ResponseBody
	public ResponseEntity addCourse(@PathVariable int studentId,@PathVariable int semesterId,@PathVariable int courseId,@PathVariable int isCoursePrimary)throws Exception {
		boolean status=studentInterfaceOperation.addCourse(studentId,semesterId,courseId,isCoursePrimary);
		return new ResponseEntity(status,HttpStatus.OK);
	}
	
	/**
     * method to drop registered course
     *
     * @param studentId
     * @param courseId
     *
     * @throws CourseNotFoundException
     * @throws StudentNotFoundException
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE, value = "/dropCourse/{studentId}/{courseId}")
	@ResponseBody
	public ResponseEntity dropCourse(@PathVariable int studentId,@PathVariable int courseId) throws Exception{
		boolean status=studentInterfaceOperation.dropCourse(studentId,courseId);
		return new ResponseEntity(status,HttpStatus.OK);
	}
	/**
     * method to view registered courses
     *
     * @param studentId
     *
     * @throws StudentNotFoundException
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/getRegisteredCourses/{studentId}")
	@ResponseBody
	public List getRegisteredCourses(@PathVariable int studentId) throws Exception{
		List<Grade> gradeList=studentInterfaceOperation.viewRegisteredCourses(studentId);
		return gradeList;
	}
	/**
     * method  to view grades of a student
     *
     * @param studentId
     *
     * @throws StudentNotFoundException
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/getGrades/{studentId}")
	@ResponseBody
	public List getGrades(@PathVariable int studentId) throws Exception{
		List<Grade> gradeList=studentInterfaceOperation.viewGrades(studentId);
		return gradeList;
	}
	
	
	/**
     * method to pay fee for registered courses
     *
     * @param studentId
     * @param paymentMethod
     *
     * @throws DuplicatePaymentEntryException
     * @throws StudentCourseNotApprovedException
     * @throws StudentNotFoundException
     */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/payFee/{studentId}/{paymentMethod}")
	@ResponseBody
	public ResponseEntity payFee(@PathVariable int studentId,@PathVariable String paymentMethod) throws Exception{
		Payment payment=studentInterfaceOperation.payFee(studentId,paymentMethod);
		if(payment==null)
		{
			return new ResponseEntity("Payment failed for student with id -"+studentId,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity(payment,HttpStatus.OK);
	}
	
	/**
	 *  method for student logout */
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/logout")
	@ResponseBody
	public String logout()throws Exception {
		return "student logging out";
	}
}
