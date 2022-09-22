package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.InvoicePDto;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;

@Mapper
@Component
public interface InvoicePMapper {
	@Mappings({
		@Mapping(source = "product.productCode", target = "productCode"),
		@Mapping(source = "product.productName", target = "productName"),
		@Mapping(source = "product.pricePerUnit",target = "pricePerUnit"),
		@Mapping(source = "product.unitMeasurement",target = "unitMeasurement"),
		@Mapping(source = "product.qty",target = "qty"),
		@Mapping(source = "product.productVatTax",target="vat"),
		@Mapping(source = "product.productNetPrice",target="netPrice")
	})
	
	InvoicePDto convertToInvoicePDto(Product product);
	
}
