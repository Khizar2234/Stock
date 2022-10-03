package com.ros.inventory.controller.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summary {

	private LocalDate stock_period_start_date;
	private LocalDate stock_period_end_date;
	private LocalDate stock_period_approved_date;
	private String approvedBy;
	private double net_sales;
	private double total_purchases;
	private double opening_stock_value;
	private double closing_stock_value;
	private double gross_profit_value;
	private double wastage;
	private double site_transfer_in_value;
	private double site_transfer_out_value;
	private double actual_cost_of_sales;
	private int gross_profit_precentage;
}
