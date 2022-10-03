package com.ros.inventory.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.Repository.ClosingStockRepository;
import com.ros.inventory.Repository.OpeningStockRepository;
import com.ros.inventory.Repository.StockPeriodRepo;
import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.controller.dto.EditInfo;
import com.ros.inventory.controller.dto.SiteTransferDto;
import com.ros.inventory.entities.ClosingStock;
import com.ros.inventory.entities.OpeningStock;
import com.ros.inventory.entities.SiteTransfer;
import com.ros.inventory.entities.StockPeriod;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.entities.Wastage;
import com.ros.inventory.mapper.SiteTransferMapper;
import com.ros.inventory.service.IStockPeriod;

@Service
public class StockPeriodImpl implements IStockPeriod {
    @Autowired
    StockPeriodRepo repo;
    
    @Autowired
    SiteTransferMapper spMapper;

    @Autowired
    ClosingStockRepository closingStockRepo;

    @Autowired
    OpeningStockRepository osRepo;

    public LocalDate getStockPeriodStartDate() throws NoOpenStockPeriodFound {
        List<com.ros.inventory.entities.StockPeriod> sps = null;
        sps = repo.findAll();
        LocalDate date = null;
        for (com.ros.inventory.entities.StockPeriod sp : sps) {
            if (sp.getCloseDate() == null) {
                date = sp.getStartDate();
                break;
            }
        }
        if (date == null)
            throw new NoOpenStockPeriodFound("no Open stock period found");

        return date;

    }
    
    
    
    @Transactional
    public StockPeriod add(StockPeriod sp) {
    	
    	return repo.save(sp);
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

	public List<Wastage> getAllWastedProducts(UUID id) {

		return repo.getOne(id).getWastageProducts();
	}

	public List<SiteTransferDto> getAllSiteTransfers(UUID id) {
        List<SiteTransfer> sts = repo.getOne(id).getSiteTransfers();
        List<SiteTransferDto> dtos=new ArrayList<SiteTransferDto>();
        for(SiteTransfer st:sts) {
        	
        	SiteTransferDto dto=new SiteTransferDto();
        	dto.setDate(st.getDate());
        	dto.setSiteTransferDtoId(st.getSiteTransferId());
        	double amount = st.getSiteTransferProducts().stream().mapToDouble(product -> product.getPricePerUnit()*product.getQty()).sum();
        	dto.setNoOfProducts(st.getSiteTransferProducts().size());
        	Supplier sp=st.getSupplier();
        	if(sp==null) {
        		dto.setSupplierName("UNKNOWN");
        	}
        	else {
        	dto.setSupplierName(st.getSupplier().getRestaurantName());
        	}
        	dto.setTotalValue(amount);
        	dto.setTransferType(st.getTransferType());
        	System.out.print(dto.getSiteTransferDtoId());
        	System.out.print(dto.getSupplierName());
        	System.out.print(dto.getNoOfProducts());
        	System.out.print(dto.getTotalValue());
        	System.out.print(dto.getDate());
 
        	System.out.print(dto.getTransferType());
        	dtos.add(dto);
        }
		return dtos;
	}



	public EditInfo getEditInfo(UUID id) {
		LocalDate date=this.repo.findById(id).get().getCloseDate();
		EditInfo e=new EditInfo();
		if(date==null) {
			e.setEdit(false);
		}
		else {
			e.setEdit(true);
		}
		return e;
	}

	

}
