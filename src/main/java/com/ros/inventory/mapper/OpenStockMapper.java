package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.entities.OpeningStock;
import com.ros.inventory.entities.Product;
import com.ros.inventory.controller.dto.OpeningStockDto;


@Mapper
@Component
public interface OpenStockMapper {

	@Mapping(source = "product.productCode", target = "productCode")
	@Mapping(source = "product.productName", target = "productName")
	@Mapping(source = "product.pricePerUnit",target = "pricePerUnit")
	@Mapping(source = "product.unitMeasurement",target = "unitMeasurement")
	
	OpeningStockDto convertToOpeningStockDto(OpeningStock product);
	OpeningStock convertToOpeningStock(OpeningStockDto productDto);
}
