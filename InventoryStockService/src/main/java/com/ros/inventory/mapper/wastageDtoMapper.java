package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.Wastage;
import com.ros.inventory.controller.dto.wastageDto;

@Mapper
@Component
public interface wastageDtoMapper {
    @Mapping(source="product.wastageId",target="wastageId")
	@Mapping(source = "product.productCode", target = "productCode")
	@Mapping(source = "product.productName", target = "productName")
	@Mapping(source = "product.pricePerUnit",target = "pricePerUnit")
	@Mapping(source = "product.unitMeasurement",target = "unitMeasurement")
	@Mapping(source="product.qty",target="qty")
	wastageDto convertToDto(Wastage product);
	Wastage convertToEntity(wastageDto product);
}
