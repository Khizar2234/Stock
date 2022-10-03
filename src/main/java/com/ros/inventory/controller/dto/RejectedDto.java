package com.ros.inventory.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RejectedDto {
	private UUID purchasedNumber;		
	private String purchaseDate;
	private double value;
	private LocalDate RejectionDate;
	private String supplierName;
	private String supplierType;

		
}
