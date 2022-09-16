package com.lti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.*;
@ControllerAdvice
public class CRSExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({AdminNotFoundException.class})
	public ResponseEntity<String> handleAdminNotFound(AdminNotFoundException adminNotFoundException){
		return new ResponseEntity<String>("Admin is not found",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ProfessorNotFoundException.class)
	public ResponseEntity<String> handleProfessorNotFound(ProfessorNotFoundException professorNotFoundException){
		return new ResponseEntity<String>("Professor is not found",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<String> handleAdminNotFound(StudentNotFoundException studentNotFoundException){
		return new ResponseEntity<String>("Student is not found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CourseAlreadyRegisteredException.class)
	public ResponseEntity<String> handleCourseAlreadyRegisteredException(CourseAlreadyRegisteredException courseAlreadyRegisteredException){
		return new ResponseEntity<String>("Course is already registered by Student",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException courseNotFoundException){
		return new ResponseEntity<String>("Course not found",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CoursesNotApprovedException.class)
	public ResponseEntity<String> handleCoursesNotApprovedException(CoursesNotApprovedException coursesNotApprovedException){
		return new ResponseEntity<String>("Course not approved",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DuplicateCourseEntryException.class)
	public ResponseEntity<String> handleDuplicateCourseEntryException(DuplicateCourseEntryException duplicateCourseEntryException){
		return new ResponseEntity<String>("Duplicate course entry",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicatePaymentEntryException.class)
	public ResponseEntity<String> handleDuplicatePaymentEntryException(DuplicatePaymentEntryException duplicatePaymentEntryException){
		return new ResponseEntity<String>("Duplicate payment entry",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DuplicateProfessorEntryException.class)
	public ResponseEntity<String> handleDuplicateProfessorEntryException(DuplicateProfessorEntryException duplicateProfessorEntryException){
		return new ResponseEntity<String>("Duplicate professor entry",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateStudentEntryException.class)
	public ResponseEntity<String> handleDuplicateStudentEntryException(DuplicateStudentEntryException duplicateStudentEntryException){
		return new ResponseEntity<String>("Duplicate student entry",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateUserEntryException.class)
	public ResponseEntity<String> handleDuplicateUserEntryException(DuplicateUserEntryException duplicateUserEntryException){
		return new ResponseEntity<String>("Duplicate user entry",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(GradeAlreadyAssignedException.class)
	public ResponseEntity<String> handleGradeAlreadyAssignedException(GradeAlreadyAssignedException gradeAlreadyAssignedException){
		return new ResponseEntity<String>("Grade already assigned",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(GradeNotAddedException.class)
	public ResponseEntity<String> handleGradeNotAddedException(GradeNotAddedException gradeNotAddedException){
		return new ResponseEntity<String>("Grade not added",HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(NoCourseAvailableException.class)
	public ResponseEntity<String> handleNoCourseAvailableException(NoCourseAvailableException noCourseAvailableException){
		return new ResponseEntity<String>("No course available",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PaymentNotMadeException.class)
	public ResponseEntity<String> handlePaymentNotMadeException(PaymentNotMadeException paymentNotMadeException){
		return new ResponseEntity<String>("Payment not made",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ProfessorNotAddedException.class})
    public ResponseEntity<String> handleProfessorNotAddedException(ProfessorNotAddedException professorNotAddedException){
        return new ResponseEntity<String>("professor not added due to some error",HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler({SeatNotAvailableException.class})
    public ResponseEntity<String> handleSeatNotAvailableException(SeatNotAvailableException seatNotAvailableException){
        return new ResponseEntity<String>("seat not availabe in course",HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler({ProfessorNotDeletedException.class})
    public ResponseEntity<String> handleProfessorNotDeletedException(ProfessorNotDeletedException professorNotDeletedException){
        return new ResponseEntity<String>("professor not deleted due to some error",HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({StudentCourseNotApprovedException.class})
    public ResponseEntity<String> handleStudentCourseNotApprovedException(StudentCourseNotApprovedException studentCourseNotApprovedException){
        return new ResponseEntity<String>("student course not approved",HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler({StudentNotGradedException.class})
    public ResponseEntity<String> handlestudentNotGradedException(StudentNotGradedException studentNotGradedException){
        return new ResponseEntity<String>("student not graded",HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler({UserNotDeletedException.class})
    public ResponseEntity<String> handleUserNotDeletedException(UserNotDeletedException userNotFoundException){
        return new ResponseEntity<String>("user not deleted due to some error",HttpStatus.NOT_FOUND);
    }
    
    
    
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException userNotFoundException){
        return new ResponseEntity<String>("User not found",HttpStatus.NOT_FOUND);
    }
	
	
	
	
}
