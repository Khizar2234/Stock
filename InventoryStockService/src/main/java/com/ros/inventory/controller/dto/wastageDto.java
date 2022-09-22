package com.ros.inventory.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class wastageDto {

	private UUID wastageId;	
	private long productCode;
	private String productName;
	private String unitMeasurement;
	private double pricePerUnit;
	private int qty;
}
