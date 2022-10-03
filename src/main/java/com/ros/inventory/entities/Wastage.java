package com.ros.inventory.entities;

import java.io.Serializable;
import java.time.LocalDate;
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
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Wastage implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="wastage_id")
    private UUID wastageId;	
	
	
	@Column(name="product_code")
	private long productCode;
	
	@Column(name="product_name")
	private String productName;
	
	
	@Column(name="product_price")
	private double pricePerUnit;
	
	@Column(name="product_unit_measurement")
	private String unitMeasurement;
	
	@Column(name="quantity")
	private int qty;
	
	@Column(name="wastageDate")
	private LocalDate wastageDate;

}