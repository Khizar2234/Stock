package com.ros.inventory.controller.dto;

import java.util.UUID;

import com.ros.inventory.entities.Address;
import com.ros.inventory.entities.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedDto {
	private UUID purchasedNumber;		
	private String purchaseDate;
	private double value;
	private OrderStatus status;
	private String supplierName;
    private String supplierType;

}
