package com.ros.inventory.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.CloseStockRepository;
import com.ros.inventory.Repository.OpeningStockRepository;
import com.ros.inventory.Repository.ProductRepository;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.controller.dto.CloseStockDto;
import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.controller.dto.OpeningStockDto;
import com.ros.inventory.entities.CloseStock;
import com.ros.inventory.entities.OpeningStock;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.StockStatus;
import com.ros.inventory.mapper.ClosingValueMapper;
import com.ros.inventory.service.IClosingValueService;

@Service
public class ClosingValuesServiceImpl implements IClosingValueService {

	@Autowired
	Product product;
	@Autowired
	ProductRepository repo;
	@Autowired
	ClosingValueMapper mapper;
	@Autowired
	OpeningStockRepository opening_repo;
	@Autowired
	StockServiceImpl stock_service;
	@Autowired
	CloseStockRepository closerepo;
	@Autowired
	PurchaseRepository purchaseRepo;

	double closing_value;

	@Override
	public List<ClosingValueDto> getValues() throws InventoryException {

		PurchaseOrder first_purchase;
		// It return the last exported data.
		if (closerepo.findAll().isEmpty()) {

			first_purchase = purchaseRepo.getLastSession("exported");
		} else {
			first_purchase = purchaseRepo.getStartSessionDate("exported", closerepo.OpeningDate().getStock_end_date());
		}

		// OpeningStockDto list
		List<ClosingValueDto> list = new ArrayList();

		// last_purchase should not be null
		if (first_purchase != null) {

			// get data from database
			List<Product> dataFromDB = repo.findAll();

			LocalDate start_date = first_purchase.getPurchaseOrderDate();
			LocalDate closing_dat = LocalDate.now();

			// use predicate so to check this month data is available or not
			Predicate<Product> s1 = s -> s.getProductEffectiveDate().isAfter(start_date)
					|| s.getProductEffectiveDate().isEqual(start_date);
			boolean isPresent = dataFromDB.stream().anyMatch(s1);
//			
			// if this month data is available then it allow the program to enter the if
			// condition
			if (isPresent) {

				List<Product> list1 = dataFromDB.stream()
						.filter(x -> (x.getProductEffectiveDate().isAfter(start_date)
								&& x.getProductEffectiveDate().isBefore(closing_dat)
								|| x.getProductEffectiveDate().isEqual(start_date)
								|| x.getProductEffectiveDate().equals(closing_dat)))
						.collect(Collectors.toList());

				// Inside for loop , store the objects in a list and also add open_total
				for (Product p : list1) {
					// add total

					list.add(mapper.convertToClosingValueDto(p));
				}

			} else {
				return list;
			}
			return list;
		}
		return list;
	}

//----------------------Adding values in Opening stock entity----------------------//
	@Override
	public String setValues(List<ClosingValueDto> close_stock_values) throws InventoryException {

		double closing_stock_total = 0;

		LocalDate close_date = LocalDate.now();

		Predicate<CloseStock> s2 = x -> x.getStockPeriodStatus().equals(StockStatus.draft);
		boolean check_draft = closerepo.findAll().stream().anyMatch(s2);

		if (check_draft == false) {
			for (ClosingValueDto s : close_stock_values) {

				OpeningStock open_stock = new OpeningStock();

				open_stock.setProductCode(s.getProductCode());
				open_stock.setPricePerUnit(s.getPricePerUnit());
				open_stock.setProductName(s.getProductName());
				open_stock.setQty(s.getQty());
				open_stock.setUnitMeasurement(s.getUnitMeasurement());
				open_stock.setClosing_date(close_date);
				closing_stock_total += s.getQty() * s.getPricePerUnit();

				opening_repo.save(open_stock);
			}
			this.closing_value = closing_stock_total;
			stock_service.add_close_stock("draft");

			return "Added Successfully";
		}
		return "Draft should be approve completed first";
	}

	@Override

	public List<CloseStock> getStockPeriod(Date start_Date, Date end_date) throws InventoryException {
		List<CloseStock> list = null;

		if (start_Date.compareTo(end_date) == 0) {
			list = closerepo.getLastFiveStockPeriod();
		} else {
			list = closerepo.getStockPeriod(start_Date, end_date);
		}

		return list;
	}
}
