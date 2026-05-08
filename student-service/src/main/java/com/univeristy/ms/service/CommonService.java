package com.univeristy.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univeristy.ms.feignclients.AddressFeignClient;
import com.univeristy.ms.request.CreateAddressRequest;
import com.univeristy.ms.response.AddressResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonService {
	
	@Autowired
	AddressFeignClient addressFeignClient;
	
	long count=1;


	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById") //name same as property file instance name
	public AddressResponse getAddressById(long addressId) {
		log.info("count = {}", count);
		count++;
		AddressResponse addressResponse = addressFeignClient.getById(addressId).getBody();
		return addressResponse;
	}
	
//	@CircuitBreaker(name = "addressService" , fallbackMethod = "fallbackCreateAddress") //name same as property file instance name
//	public AddressResponse createAddress(CreateAddressRequest address) {
//		log.info("count = {}", count);
//		count++;
//		AddressResponse addressResponse =
//	            addressFeignClient.createAddress(address).getBody();
//		return addressResponse;
//	}
	
	public AddressResponse fallbackGetAddressById(long addressId, Throwable th) {
		log.error("Error = {}", th.getMessage());
		return new AddressResponse();
	}
	
//	public AddressResponse fallbackCreateAddress(CreateAddressRequest address, Throwable th) {
//		log.error("Error = {}", th.getMessage());
//		return new AddressResponse();
//	}
	
}
