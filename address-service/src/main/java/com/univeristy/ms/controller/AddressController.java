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

import com.univeristy.ms.request.CreateAddressRequest;
import com.univeristy.ms.response.AddressResponse;
import com.univeristy.ms.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	AddressService addressService;

	@Operation(summary = "Create new address")
	@PostMapping("/create")
	public ResponseEntity<AddressResponse> createAddress(@RequestBody CreateAddressRequest createAddressRequest) {
		return new ResponseEntity<>(addressService.createAddress(createAddressRequest), HttpStatus.CREATED);
	}

	@Operation(summary = "Get address by id")
	@GetMapping("/getById/{id}")
	public ResponseEntity<AddressResponse> getById(@PathVariable long id) {
		return new ResponseEntity<>(addressService.getById(id), HttpStatus.OK);
	}

}
