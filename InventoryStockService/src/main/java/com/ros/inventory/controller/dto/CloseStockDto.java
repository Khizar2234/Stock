package com.ros.inventory.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloseStockDto {
	
	private UUID stockID;
	private LocalDate stock_start_date;
	private LocalDate stock_end_date;
	private double opening_stock_value;
	private double closing_stock_value;
	private double cost_of_sales;
	

}
