package com.ros.inventory.entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class OpeningStock {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="opening_id", length = 8)
	private UUID OpeningID;
	
	@Column(name="product_code")
	private long productCode;
	@Column(name="product_name")
	private String productName;
	@Column(name="unit_measurement")
	private String unitMeasurement;
	@Column(name="price_per_unit")
	private double pricePerUnit;
	@Column(name="Quantity")
	private int qty;
	@Column(name="closing_Date")
	private LocalDate closing_date;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_period_id")
	private StockPeriod stockPeriod;
}
