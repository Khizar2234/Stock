package com.ros.inventory.serviceImpl;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.aspectj.apache.bcel.classfile.Module.Open;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.Exception.OpeningStockNotFound;
import com.ros.inventory.Repository.CloseStockRepository;
import com.ros.inventory.Repository.ClosingStockRepository;
import com.ros.inventory.Repository.OpeningStockRepository;
import com.ros.inventory.Repository.StockPeriodRepo;
import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.entities.CloseStock;
import com.ros.inventory.entities.ClosingStock;
import com.ros.inventory.entities.OpeningStock;
import com.ros.inventory.entities.StockStatus;
import com.ros.inventory.service.StockPeriod;

@Service
public class StockPeriodImpl implements StockPeriod {
    @Autowired
    StockPeriodRepo repo;

    @Autowired
    ClosingStockRepository closingStockRepo;

    @Autowired
    OpeningStockRepository osRepo;
    
    
    @Autowired
    CloseStockRepository closeStockRepo;

    public Map<UUID, LocalDate> getStockPeriodStartDate() throws NoOpenStockPeriodFound {
        List<com.ros.inventory.entities.StockPeriod> sps = null;
        Map<UUID, LocalDate> openStockDetail = new HashMap<>();
        sps = repo.findAll();
        LocalDate date = null;
        for (com.ros.inventory.entities.StockPeriod sp : sps) {
            if (sp.getCloseDate() == null) {
                date = sp.getStartDate();
                openStockDetail.put(sp.getStockPeriodId(), date);
                break;
            }
        }
        if (date == null)
            throw new NoOpenStockPeriodFound("no Open stock period found");

        return openStockDetail;

    }

    public Map<UUID, Double> getClosingStockValue() throws NoOpenStockPeriodFound {
        List<com.ros.inventory.entities.StockPeriod> sps = null;
        sps = repo.findAll();
        double value = 0;
        Map<UUID, Double> closingValueTotal = new HashMap<>();
//	    check if sps is null
//	    LocalDate date = null;
        for (com.ros.inventory.entities.StockPeriod sp : sps) {
            if (sp.getCloseDate() != null) {
                List<com.ros.inventory.entities.ClosingStock> css = null;
                css = closingStockRepo.findByStockPeriodId(sp.getStockPeriodId());
                value = 0;
                for (com.ros.inventory.entities.ClosingStock cs : css) {
                    value += cs.getQty() * cs.getPricePerUnit();
//	                System.out.println(value);
                }
                closingValueTotal.put(sp.getStockPeriodId(), value);
            }

        }
        return closingValueTotal;
    }

    public Map<UUID, Double> getOpeningStockValue() {
        List<com.ros.inventory.entities.StockPeriod> sps = null;
        sps = repo.findAll();
        double value = 0;
        Map<UUID, Double> openingValueTotal = new HashMap<>();
        for (com.ros.inventory.entities.StockPeriod sp : sps) {
            if (sp.getCloseDate() != null) {
                List<com.ros.inventory.entities.OpeningStock> css = null;
                css = osRepo.findByStockPeriodId(sp.getStockPeriodId());
                value = 0;
                for (com.ros.inventory.entities.OpeningStock cs : css) {
                    value += cs.getQty() * cs.getPricePerUnit();
//                  System.out.println(value);
                }
                openingValueTotal.put(sp.getStockPeriodId(), value);
            }

        }
        return openingValueTotal;
    }

    public float totalClosedStockValue() {
        List<com.ros.inventory.entities.StockPeriod> sps = null;
        sps = repo.findAll();
        float value = 0;
        for (com.ros.inventory.entities.StockPeriod sp : sps) {
            if (sp.getCloseDate() != null) {
                List<com.ros.inventory.entities.ClosingStock> css = null;
                css = closingStockRepo.findByStockPeriodId(sp.getStockPeriodId());
                for (com.ros.inventory.entities.ClosingStock cs : css) {
                    value += cs.getQty() * cs.getPricePerUnit();
                }
            }
        }
        return value;
    }

    @Override
    public String saveClosingValue(List<ClosingValueDto> cvds) throws NoOpenStockPeriodFound {  
//        creating object to store stock period values
        List<com.ros.inventory.entities.StockPeriod> sps = null;
//        finding stock period
        sps = repo.findAll();
        LocalDate date = LocalDate.now();
        com.ros.inventory.entities.StockPeriod currectStockPeriod = null;
//        finding open stock period
        for (com.ros.inventory.entities.StockPeriod sp : sps) {
            if (sp.getCloseDate() == null) {
//                date = sp.getStartDate();
                currectStockPeriod = sp;
                break;
            }
        }
//        throwing error if no open stock periods are found
        if (currectStockPeriod == null) {
            throw new NoOpenStockPeriodFound("no open stock period found");
        }
//        closing the open stock period
        currectStockPeriod.setCloseDate(date);
        repo.save(currectStockPeriod);

//        creating new stock period
        LocalDate newDate = LocalDate.now();
        com.ros.inventory.entities.StockPeriod newStockPeriod = new com.ros.inventory.entities.StockPeriod();
        newStockPeriod.setStartDate(newDate.plusDays(1));
        repo.save(newStockPeriod);

//        finding new stock period
        com.ros.inventory.entities.StockPeriod newCurrentStockPeriod = null;
        sps = repo.findAll();
        for (com.ros.inventory.entities.StockPeriod sp : sps) {
            if (sp.getCloseDate() == null) {
                newCurrentStockPeriod = sp;
                break;
            }
        }
//        saving closingStock values and openingStock values for new period 
        for (ClosingValueDto cvd : cvds) {
            ClosingStock cl = new ClosingStock();
            cl.setClosing_date(date);
            cl.setPricePerUnit(cvd.getPricePerUnit());
            cl.setProductCode(cvd.getProductCode());
            cl.setProductName(cvd.getProductName());
            cl.setQty(cvd.getQty());
            cl.setStockPeriod(currectStockPeriod);
            cl.setUnitMeasurement(cvd.getUnitMeasurement());
            closingStockRepo.save(cl);

            OpeningStock os = new OpeningStock();
            os.setPricePerUnit(cvd.getPricePerUnit());
            os.setProductCode(cvd.getProductCode());
            os.setProductName(cvd.getProductName());
            os.setQty(cvd.getQty());
            os.setStockPeriod(newCurrentStockPeriod);
            os.setUnitMeasurement(cvd.getUnitMeasurement());
            osRepo.save(os);
        }
        
        return "Saved successfuly";
    }
    
    
    @Override
    public List<CloseStock> getClosedStockList() throws NoOpenStockPeriodFound{
        List<CloseStock> closedStockList = new ArrayList<>();
        List<com.ros.inventory.entities.StockPeriod> sps = null;
        sps = repo.findAll();
        Map<UUID, Double> closingStockValueMap = new HashMap<>();
        closingStockValueMap = getClosingStockValue();
        Map<UUID, Double> openingStockValueMap = new HashMap<>();
        openingStockValueMap = getOpeningStockValue();
        for (com.ros.inventory.entities.StockPeriod sp : sps) {
            if (sp.getCloseDate() != null) {
                CloseStock closeStock = new CloseStock();
                closeStock.setStock_start_date(sp.getStartDate());
                closeStock.setStock_end_date(sp.getCloseDate());
                closeStock.setOpening_stock_value(openingStockValueMap.get(sp.getStockPeriodId()));
                closeStock.setClosing_stock_value(closingStockValueMap.get(sp.getStockPeriodId()));
                closeStock.setCost_of_sales(1000);
                closeStock.setStockPeriodStatus(StockStatus.approved);
                closeStockRepo.save(closeStock);
                closedStockList.add(closeStock);
            }
            
        }
        return closedStockList;
    }
    
    public boolean closeStockApprove(CloseStock closedStock) {
    	CloseStock cs= closeStockRepo.findById(closedStock.getStockID()).get();
    	cs.setStockPeriodStatus(StockStatus.approved);
    	closeStockRepo.save(cs);
    	return true;
    }
}
