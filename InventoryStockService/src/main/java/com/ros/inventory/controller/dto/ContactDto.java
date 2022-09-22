package com.ros.inventory.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto 
{
    private String firstName;
	
	private String middleName;
	
	private String lastName;
	
    private String email;
	
	private long fax;
	
	private long mobile;
	
	private long telephone;
}
