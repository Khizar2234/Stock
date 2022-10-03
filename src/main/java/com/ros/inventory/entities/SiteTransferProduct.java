package com.ros.inventory.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SiteTransferProduct {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "site_transfer_product_id", length = 8)
	private UUID siteTransferProductId;

	@Column(name = "name")
	private String productName;


	@Column(name = "product_price")
	private double pricePerUnit;

	@Column(name = "unit_of_measurement")
	private String unitOfMeasurement;

	@Column(name = "quantity")
	private int qty;

}
