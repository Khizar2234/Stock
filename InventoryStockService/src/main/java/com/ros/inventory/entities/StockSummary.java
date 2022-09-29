package com.ros.inventory.entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class StockSummary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="stock_summary_id", length = 8)
	private UUID StockSummaryID;
	
	@Column(name = "opening_stock_value")
	private double opening_stock_value;
	
	@Column(name = "closing_stock_value")
	private double closing_stock_value;
	
	@Column(name = "cost_of_sales")
	private double cost_of_sales;
	
	@Column(name = "total_wastage")
	private double totalWastage;
	
	@Column(name = "total_purchase")
	private double totalPurchase;
	
	@Column(name = "siteTransfer_in_value")
	private double siteTransferInValue;
	
	@Column(name = "siteTransfer_out_value")
	private double siteTransferOutValue;

	@Column(name = "stock_period_approved_date")
	private String stockApprovalDate;
	
	@Column(name = "stock_approved_by")
	private String stockApprovedBy;
	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "stock_period_id")
    // private StockPeriod stockPeriodId;
}
