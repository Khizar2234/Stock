package com.ros.inventory.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceProductDto {

	private long productCode;
	private String productName;
	private String productType;
	private String unitMeasurement;
	private int qtyReceived;
	private double pricePerUnit;
	private double Vat;
	private double netPrice;

}
