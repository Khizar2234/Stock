package com.ros.inventory.serviceImpl;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.Exception.OpeningStockNotFound;
import com.ros.inventory.Repository.ClosingStockRepository;
import com.ros.inventory.Repository.OpeningStockRepository;
import com.ros.inventory.Repository.StockPeriodRepo;
import com.ros.inventory.entities.OpeningStock;
import com.ros.inventory.service.StockPeriod;

@Service
public class StockPeriodImpl implements StockPeriod {
	@Autowired
	StockPeriodRepo repo;
	
	
	@Autowired
    ClosingStockRepository closingStockRepo;

	@Autowired
	OpeningStockRepository osRepo;

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
	
	public double getClosingStockValue() throws NoOpenStockPeriodFound{
	    List<com.ros.inventory.entities.StockPeriod> sps = null;
	    sps = repo.findAll();
	    double value=0;
//	    check if sps is null
//	    LocalDate date = null;
	    for(com.ros.inventory.entities.StockPeriod sp: sps) {
	        if(sp.getCloseDate()!=null) {
	            List<com.ros.inventory.entities.ClosingStock> css = null;            
	            css = closingStockRepo.findByStockPeriodId(sp.getStockPeriodId());
	            for(com.ros.inventory.entities.ClosingStock cs: css) {
	                value+=cs.getQty()*cs.getPricePerUnit();
	            }
	        }
	    }
	    return value;
	}

	public float getOpeningStockValue() throws OpeningStockNotFound {
		float value =0  ;
		List<OpeningStock> openingStocks = osRepo.findAll(); // find by restaurant id at some point
		if(openingStocks == null) 
		{
			throw new OpeningStockNotFound("No opening stocks found!");
		}
		for(OpeningStock stock : openingStocks) {
			value += stock.getQty() * stock.getPricePerUnit();
		}
		return value;
	}

}
