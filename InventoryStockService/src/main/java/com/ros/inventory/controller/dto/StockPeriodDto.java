package com.ros.inventory.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPeriodDto {

	private LocalDate startDate;
	private LocalDate closeDate;

}
