package com.ros.inventory.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;

import com.ros.inventory.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductDto {
	private UUID supplierId;
	private List<ProductDto> products;
	
	
	
	
//	private long productId;
//	private String productName;
//	private String productType;
//	private double productPrice;
//	private String productUnitMeasurement;
//	private LocalDate productEffectiveDate;
//	private double productVatTax;
}
