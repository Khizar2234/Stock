package com.ros.inventory.controller.dto;

//import java.time.LocalDate;
//import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpeningStockDto {

	private long productCode;
	private String productName;
	private String unitMeasurement;
	private double pricePerUnit;
	private int qty;
	
}
