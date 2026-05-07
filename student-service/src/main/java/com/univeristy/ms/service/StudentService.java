package com.univeristy.ms.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.univeristy.ms.entity.Student;
import com.univeristy.ms.exception.ResourceNotFoundException;
import com.univeristy.ms.feignclients.AddressFeignClient;
import com.univeristy.ms.repository.StudentRepository;
import com.univeristy.ms.request.CreateStudentRequest;
import com.univeristy.ms.response.AddressResponse;
import com.univeristy.ms.response.StudentResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	WebClient webClient;
	
	@Autowired
	AddressFeignClient addressFeignClient;

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
		
		AddressResponse addressResponse =
	            addressFeignClient.createAddress(createStudentRequest.getAddress()).getBody();
		
		Student student = new Student();
		BeanUtils.copyProperties(createStudentRequest, student);
		student.setAddressId(addressResponse.getId());
		student = studentRepository.save(student);
		
		
		StudentResponse studentResponse = new StudentResponse();
		BeanUtils.copyProperties(student, studentResponse);
//		studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
//		studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()).getBody());
		studentResponse.setAddressResponse(addressResponse);
		
		return studentResponse;
	}
	
	public StudentResponse getById (long id) {
		
		log.info("Inside getById " + id);
		
		Student student = studentRepository.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Student not found with id : " + id));

		
		StudentResponse studentResponse = new StudentResponse();
		BeanUtils.copyProperties(student, studentResponse);
		
//		studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
		studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()).getBody());
		
		return studentResponse;
	}
	
	public AddressResponse getAddressById(long addressId) {
		Mono<AddressResponse> addressResponse = webClient.get().uri("/getById/" + addressId).retrieve()
				.bodyToMono(AddressResponse.class);
		return addressResponse.block();
	}

}
