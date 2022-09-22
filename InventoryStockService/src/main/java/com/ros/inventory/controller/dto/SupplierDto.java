package com.ros.inventory.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto 
{
	    private String supplierName;
	    private String supplierType;
	    private String email;
	    private long mobile;

	    
}
