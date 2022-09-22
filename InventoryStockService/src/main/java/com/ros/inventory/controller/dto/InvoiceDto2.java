package com.ros.inventory.controller.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto2 {
	
	private String supplierName;
	private String street;
	private String area;
	private String city;
	private String state;
	private String country;
	private long pincode;

	private LocalDate invoiceDate;
	private List<InvoiceProductDto> ipd;
	//private double netBalance;
	private double vat;
	private double total;

	private String restaurantName;
	private String restaurantStreet; 
	private String restaurantCity;
	private String restaurantState;
	private String restaurantCountry;
	private long restaurantMob;
	private long restaurantTel;
	private long restaurantPin;
	


}