package com.ros.inventory.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

	private UUID productId;
	private long productCode;
	private String productName;
	private String productType;
	private double pricePerUnit;
	private String unitMeasurement;
	private int qty;
	private LocalDate productEffectiveDate;
	private double productVatTax;
}
