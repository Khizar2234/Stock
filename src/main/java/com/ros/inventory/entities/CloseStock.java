package com.ros.inventory.entities;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CloseStock implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="stock_id", length = 8)
	private UUID stockID;
	
	@Column(name = "stock_start_date")
	private LocalDate stock_start_date;
	
	@Column(name = "stock_end_date")
	private LocalDate stock_end_date;
	
	@Column(name = "opening_stock_value")
	private double opening_stock_value;
	
	@Column(name = "closing_stock_value")
	private double closing_stock_value;
	
	@Column(name = "cost_of_sales")
	private double cost_of_sales;

	@Enumerated(EnumType.STRING)
	@Column(name = "stock_period_status")
	private StockStatus StockPeriodStatus;

}
