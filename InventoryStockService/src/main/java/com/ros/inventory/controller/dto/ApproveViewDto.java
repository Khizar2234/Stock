package com.ros.inventory.controller.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApproveViewDto {
	private UUID purchasedNumber;		
	private String purchaseDate;
	private SupplierDto1 supplier;
    //private ProductPDto product;
}
