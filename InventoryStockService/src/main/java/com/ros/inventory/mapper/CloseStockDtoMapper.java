package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.entities.CloseStock;
import com.ros.inventory.controller.dto.CloseStockDto;


@Mapper
@Component
public interface CloseStockDtoMapper {
//
//	@Mapping(source="close.stock_start_date",target="stock_start_date")
//	@Mapping(source="close.stock_end_date",target="stock_end_date")
//	@Mapping(source="close.opening_stock_value",target="opening_stock_value")
//	@Mapping(source="close.closing_stock_value",target="closing_stock_value")
//	@Mapping(source="close.cost_of_sales",target="cost_of_sales")
//	
//	CloseStockDto convertToCloseStockDto(CloseStock close);
	@Mapping(source="close.stockID",target="stockID")
	@Mapping(source="close.stock_start_date",target="stock_start_date")
	@Mapping(source="close.stock_end_date",target="stock_end_date")
	@Mapping(source="close.opening_stock_value",target="opening_stock_value")
	@Mapping(source="close.closing_stock_value",target="closing_stock_value")
	@Mapping(source="close.cost_of_sales",target="cost_of_sales")
	
	CloseStockDto convertToCloseStockDto(CloseStock close);
	CloseStock convertToEntity(CloseStockDto closeDto);
}
