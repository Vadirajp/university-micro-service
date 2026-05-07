package com.univeristy.ms.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univeristy.ms.entity.Address;
import com.univeristy.ms.exception.ResourceNotFoundException;
import com.univeristy.ms.repository.AddressRepository;
import com.univeristy.ms.request.CreateAddressRequest;
import com.univeristy.ms.response.AddressResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
		Address address = new Address();
		BeanUtils.copyProperties(createAddressRequest, address);

		address = addressRepository.save(address);

		AddressResponse response = new AddressResponse();
		BeanUtils.copyProperties(address, response);
		return response;
	}

	public AddressResponse getById(long id) {

		log.info("Inside getById " + id);
		
		Address address = addressRepository.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Address not found with id : " + id));


		AddressResponse response = new AddressResponse();
		BeanUtils.copyProperties(address, response);
		return response;
	}

}
