package com.ros.inventory.controller.dto;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.entities.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DraftsDto {
		private UUID purchasedNumber;		
		private String purchaseDate;
		private double value;
		 private String supplierName;
		 private String supplierType;

}
