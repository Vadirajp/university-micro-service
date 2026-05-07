package com.univeristy.ms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {

	private String firstName;

	private String lastName;

	private String email;

//	private long addressId;
	
	private CreateAddressRequest address;

}
