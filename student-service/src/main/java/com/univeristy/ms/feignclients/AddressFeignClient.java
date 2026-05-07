package com.univeristy.ms.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.univeristy.ms.request.CreateAddressRequest;
import com.univeristy.ms.response.AddressResponse;

//@FeignClient(url = "${address.service.url}", value = "address-feign-client", path = "/api/address")
@FeignClient(value = "address-service", path = "/api/address")
public interface AddressFeignClient {

	@GetMapping("/getById/{id}")
	public ResponseEntity<AddressResponse> getById(@PathVariable long id);

	@PostMapping("/create")
	ResponseEntity<AddressResponse> createAddress(@RequestBody CreateAddressRequest request);

}
