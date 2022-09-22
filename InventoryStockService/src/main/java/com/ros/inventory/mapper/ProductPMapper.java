package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.entities.Product;


@Mapper
@Component
public interface ProductPMapper {
	@Mappings({
		//@Mapping(source = "product.supplerBasicInfo.supplierInfoId",target="supplierBusinessName"),
		@Mapping(source = "product.productCode", target = "productCode"),
		@Mapping(source = "product.productName", target = "productName"),
		@Mapping(source = "product.productType",target = "productType"),
		@Mapping(source = "product.pricePerUnit",target = "pricePerUnit"),
		@Mapping(source = "product.unitMeasurement",target = "unitMeasurement"),
		@Mapping(source = "product.qty",target = "qty")
	})

	Product convertToProduct(ProductPDto product);
	ProductPDto convertToPurchasePDto(Product product);
}