package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents;
	
	//Define @PostConstruct to load the student data only once
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Anant","Jain"));
		theStudents.add(new Student("Arpita","Trivedi"));
		theStudents.add(new Student("Ambrish","Sekhri"));
	}
	
	
	//define endpoint for "/students" - return list of students
	@GetMapping("/students")
	public List<Student> getStudents(){
		return theStudents;
	}
	
	//Define endpoint for "students/{studentId}" - return student at index
	@GetMapping("students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		//Check the studentId against list size
		if((studentId >= theStudents.size()) || (studentId < 0)) {
			throw new StudentNotFoundException("Student Id not found - "+studentId);
		}	
		return theStudents.get(studentId);
	}
	
	//Add an exception handler using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
		
		//Create a StudentErrorResponse
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		//return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	//add another exception handler to catch any exception (catch all)
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
		
		//Create a StudentErrorResponse
				StudentErrorResponse error = new StudentErrorResponse();
				error.setStatus(HttpStatus.BAD_REQUEST.value());
				error.setMessage(exc.getMessage());
				error.setTimeStamp(System.currentTimeMillis());
				
				//return ResponseEntity
				return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}














