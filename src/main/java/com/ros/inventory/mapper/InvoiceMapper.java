package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.InvoiceDto;
import com.ros.inventory.entities.Invoice;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;
@Mapper
@Component
public interface InvoiceMapper {
	//basic detail
	@Mapping(source = "p.supplier.supplierBasic.supplierBusinessName", target = "supplierName")
	@Mapping(source = "p.purchaseOrderDate", target = "invoiceDate")
	@Mapping(source = "p.totalAmount", target = "total")
	@Mapping(source = "p.purchasedId", target = "PONumber")
	InvoiceDto convertToInvoiceDto(PurchaseOrder p);
}

