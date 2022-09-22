package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.SupplierDto;
import com.ros.inventory.entities.Supplier;

@Mapper
@Component
public interface SupplierMapper {

	@Mapping(source = "supplier.supplierBasic.supplierBusinessName", target = "supplierName")
	@Mapping(source = "supplier.supplierType", target = "supplierType")
	@Mapping(source = "supplier.supplierContact.email", target = "email")
	@Mapping(source = "supplier.supplierContact.mobile", target = "mobile")
	SupplierDto convertToSupplierDto(Supplier supplier);
}
