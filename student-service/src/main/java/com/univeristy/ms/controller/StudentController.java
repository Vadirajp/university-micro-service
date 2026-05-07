package com.univeristy.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univeristy.ms.request.CreateStudentRequest;
import com.univeristy.ms.response.StudentResponse;
import com.univeristy.ms.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@Operation(summary = "Create a new student")
	@PostMapping("/create")
	public ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
		return new ResponseEntity<>(studentService.createStudent(createStudentRequest), HttpStatus.CREATED);
	}

	@Operation(summary = "Get student by id")
	@GetMapping("getById/{id}")
	public ResponseEntity<StudentResponse> getById(@PathVariable long id) {
		return new ResponseEntity<>(studentService.getById(id), HttpStatus.OK);
	}

}
