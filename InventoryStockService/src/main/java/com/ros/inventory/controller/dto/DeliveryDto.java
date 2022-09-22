package com.ros.inventory.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryDto {
	
	private int qty;	
	private int qtyReceived;
	private double initialPrice;
	private double finalPrice;
	private int productCode;
	private String productName;
}
