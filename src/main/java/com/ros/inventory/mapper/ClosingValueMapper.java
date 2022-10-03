package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.controller.dto.OpeningStockDto;
import com.ros.inventory.entities.OpeningStock;
import com.ros.inventory.entities.Product;

@Mapper
@Component
public interface ClosingValueMapper {

	@Mapping(source = "product.productCode", target = "productCode")
	@Mapping(source = "product.productName", target = "productName")
	@Mapping(source = "product.pricePerUnit",target = "pricePerUnit")
	@Mapping(source = "product.unitMeasurement",target = "unitMeasurement")
	
    ClosingValueDto convertToClosingValueDto(Product product);
	
}
