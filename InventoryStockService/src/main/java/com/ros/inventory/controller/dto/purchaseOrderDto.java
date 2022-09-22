package com.ros.inventory.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.ros.inventory.entities.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class purchaseOrderDto {

	
	private UUID purchasedId;
	private LocalDate purchaseOrderDate;
	private double totalAmount;
	private OrderStatus purchaseOrderStatus;
	private String invoice_status;
	private String supplierBusinessName;
}
