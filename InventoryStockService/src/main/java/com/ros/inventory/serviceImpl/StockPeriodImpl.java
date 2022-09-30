package com.ros.inventory.serviceImpl;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
	
	public Map<UUID, Double> getClosingStockValue() throws NoOpenStockPeriodFound{
	    List<com.ros.inventory.entities.StockPeriod> sps = null;
	    sps = repo.findAll();
	    double value=0;
	    Map<UUID, Double> closingValueTotal = new HashMap<>();
//	    check if sps is null
//	    LocalDate date = null;
	    for(com.ros.inventory.entities.StockPeriod sp: sps) {
	        if(sp.getCloseDate()!=null) {
	            List<com.ros.inventory.entities.ClosingStock> css = null;            
	            css = closingStockRepo.findByStockPeriodId(sp.getStockPeriodId());
	            value=0;
	            for(com.ros.inventory.entities.ClosingStock cs: css) {
	                value+=cs.getQty()*cs.getPricePerUnit();
//	                System.out.println(value);
	            }
	            closingValueTotal.put(sp.getStockPeriodId(), value);
	        }
	        
	    }
	    return closingValueTotal;
	}

	public Map<UUID, Double> getOpeningStockValue(){
	    List<com.ros.inventory.entities.StockPeriod> sps = null;
        sps = repo.findAll();
        double value=0;
        Map<UUID, Double> openingValueTotal = new HashMap<>();
		for(com.ros.inventory.entities.StockPeriod sp: sps) {
            if(sp.getCloseDate()!=null) {
                List<com.ros.inventory.entities.OpeningStock> css = null;            
                css = osRepo.findByStockPeriodId(sp.getStockPeriodId());
                value=0;
                for(com.ros.inventory.entities.OpeningStock cs: css) {
                    value+=cs.getQty()*cs.getPricePerUnit();
//                  System.out.println(value);
                }
                openingValueTotal.put(sp.getStockPeriodId(), value);
            }
            
        }
        return openingValueTotal;
	}

}
