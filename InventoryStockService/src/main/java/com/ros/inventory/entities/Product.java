package com.ros.inventory.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id", length = 8)
	private UUID productId;

	@Column(name = "product_code")
	private long productCode;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "product_price")
	private double pricePerUnit;

	@Column(name = "product_unit_measurement")
	private String unitMeasurement;

	@Column(name = "quantity")
	private int qty;

	@Column(name = "product_date")
	private LocalDate productEffectiveDate;

	@Column(name = "product_vat_tax")
	private double productVatTax;

	@Column(name = "product_net_price")
	private double productNetPrice;

	@Column(name = "purchase_qtyReceived")
	private int qtyReceived;

	@Column(name = "product_intialPrice")
	private double initialPrice;

	@Column(name = "product_finalPrice")
	private double finalPrice;

	
}
