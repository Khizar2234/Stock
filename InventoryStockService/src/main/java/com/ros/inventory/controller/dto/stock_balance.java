package com.ros.inventory.controller.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class stock_balance {

	
	private long productCode;
	private String productName;
	private long opening_qty;
	private int ordered_qty;
	private int wastage_qty;
	private long total;
}
