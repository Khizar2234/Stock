package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.ApproveViewDto;
import com.ros.inventory.controller.dto.DeliveryDto;
import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;

@Mapper
@Component
public interface DeliverMapper {
	@Mapping(source = "product.productCode", target = "productCode")
	@Mapping(source = "product.productName", target = "productName")
	//@Mapping(source = "produt.qty" , target="qty")
	@Mapping(source = "product.qtyReceived",target="qtyReceived")
	@Mapping(source = "product.initialPrice",target="initialPrice")
	@Mapping(source = "product.finalPrice",target="finalPrice")
	
	DeliveryDto convertToDeliveryDto(Product product);
	
}
