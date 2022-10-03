package com.ros.inventory.controller.dto;

import java.sql.Date;
import java.util.UUID;


import com.ros.inventory.entities.TransferType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteTransferDto {
	
	private UUID siteTransferDtoId;
	private String supplierName;
	private Date date;
	private TransferType transferType;
	private int noOfProducts;
	private double totalValue;
}
