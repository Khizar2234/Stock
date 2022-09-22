package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.SupplierDescriptionDto;
import com.ros.inventory.entities.Supplier;

@Mapper
@Component
public interface SupplierDescriptionMapper
{	
	@Mapping(source = "supplier.supplierType", target = "supplierType")
	
	@Mapping(source = "supplier.supplierBasic.supplierBusinessName", target = "supplierName")
	@Mapping(source = "supplier.supplierBasic.supplierTradeName", target = "supplierTradeName")	
	@Mapping(source = "supplier.supplierBasic.image", target = "image")
	
	@Mapping(source = "supplier.supplierContact", target = "contact")
	
	@Mapping(source = "supplier.supplierAddress.street", target = "street")
	@Mapping(source = "supplier.supplierAddress.area", target = "area")
	@Mapping(source = "supplier.supplierAddress.city", target = "city")
	@Mapping(source = "supplier.supplierAddress.state", target = "state")
	@Mapping(source = "supplier.supplierAddress.country", target = "country")
	@Mapping(source = "supplier.supplierAddress.pincode", target = "pincode")
	
	@Mapping(source = "supplier.supplierBank", target = "bank")
	
       SupplierDescriptionDto convertToDto(Supplier supplier);
}
