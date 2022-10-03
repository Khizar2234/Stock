package com.ros.inventory.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDescriptionDto 
{
	private String supplierType;
	private String supplierName;
	private String supplierTradeName;
	private String image;
	private String street;
	private String area;
	private String city;
	private String state;
	private String country;
	private long pincode;
    private BankDto bank;
//    private List<BankDto> bank;
    private ContactDto contact;
}
