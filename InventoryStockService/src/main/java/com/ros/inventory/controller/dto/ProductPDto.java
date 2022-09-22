package com.ros.inventory.controller.dto;

import java.util.List;

import com.ros.inventory.entities.SupplierBasicInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPDto {
	private long productCode;
	private String productName;
	private String productType;
	private String unitMeasurement;
	private double pricePerUnit;
	private int qty;	
	
	
	
}
