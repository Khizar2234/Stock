package com.ros.inventory.serviceImpl;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.Repository.StockPeriodRepo;
import com.ros.inventory.service.StockPeriod;

@Service
public class StockPeriodImpl implements StockPeriod {
	@Autowired
	StockPeriodRepo repo;

	public LocalDate getStockPeriodStartDate() throws NoOpenStockPeriodFound {
		List<com.ros.inventory.entities.StockPeriod> sps= null;
		sps= repo.findAll();
		LocalDate date= null;
		for(com.ros.inventory.entities.StockPeriod  sp:sps){
			if(sp.getCloseDate()==null) {
				date= sp.getStartDate();
				break;
			}
		}
		if(date==null)
			throw new NoOpenStockPeriodFound("no Open stock period found");
		
		return date;
		
		
	}

}
