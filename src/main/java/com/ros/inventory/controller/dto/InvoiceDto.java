package com.ros.inventory.controller.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

	
	private String supplierName;
	private LocalDate invoiceDate;
	private UUID PONumber;
	private double total;
	
}
