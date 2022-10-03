package com.ros.inventory.controller.dto;

import java.util.List;

import com.ros.inventory.controller.dto.Summary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloseStockDetailDto {

	private Summary summary;
	private List<OpeningStockDto> open_stock;
	private List<purchaseOrderDto> purchase_order;
	private List<wastageDto> wastage;
	private List<stock_balance> stock_balance;
	private List<SiTeTransfersDto> site_transfer;
}
