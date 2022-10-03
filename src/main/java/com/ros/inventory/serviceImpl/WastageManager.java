package com.ros.inventory.serviceImpl;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.WastageRepository;
import com.ros.inventory.entities.Wastage;
import com.ros.inventory.mapper.wastageDtoMapper;
import com.ros.inventory.service.IWastageManager;
import com.ros.inventory.controller.dto.wastageDto;

@Service
public class WastageManager implements IWastageManager
{
  @Autowired 
  WastageRepository wasteRepo;
  @Autowired
  wastageDtoMapper mapper;
	
  /*------------- ADD WASTAGE PRODUCT ----------------*/
	@Override
	public Wastage add(wastageDto wastage) throws InventoryException
	{
		// TODO Auto-generated method stub
	
		Wastage wastageFromRepo = wasteRepo.byId(wastage.getWastageId());
		
		if(wastageFromRepo!= null)
		{
			throw new InventoryException("item is already present ");
		}
		Wastage ws=mapper.convertToEntity(wastage);
		LocalDate ld=LocalDate.now();
		ws.setWastageDate(ld);
		
		return wasteRepo.save(ws); 
	}

//  /*----------- SHOW ALL WASTAGE ITEMS -----------------*/
//	@Override
//	public List<wastage> show() throws InventoryException
//	{
//		// TODO Auto-generated method stub
//       List<wastage> wastageFromRepo = wasteRepo.findAll();
//       
//        if(wastageFromRepo ==null || wastageFromRepo.size()==0)
//        {
//             throw new InventoryException("no items are available");
//        }
//        
//		return wastageFromRepo;
//	}

	/*--------- GET BY  PRODUCT NAME ------------------*/
	@Override
	public Wastage byName(String item) throws InventoryException 
	{
		// TODO Auto-generated method stub
	    Wastage wastageFromRepo =wasteRepo.byName(item);
	    
	    if(wastageFromRepo == null)
	    {
	    	throw new InventoryException("no item is present by this name");
	    }
	    
	    return wastageFromRepo;
	}

	/*-------- GET BY PRODUCT ID -----------------------*/
	@Override
	public Wastage byId(long code) throws InventoryException 
	{
		// TODO Auto-generated method stub
	    Wastage wastageFromRepo = wasteRepo.byCode(code);
		

	    if(wastageFromRepo == null)
	    {
	    	throw new InventoryException("no item is present by this id");
	    }
	    
	    return wastageFromRepo;
	}

	/*--------FOR DELETING THE PARTICULAR ITEM ------------*/ 
	@Override
	public Wastage delete(UUID id) throws InventoryException
	{
		// TODO Auto-generated method stub
		
		Wastage wastageFromRepo =wasteRepo.byId(id);

	    if(wastageFromRepo == null)
	    {
	    	throw new InventoryException("no item is present by this id");
	    }
	     
	    wasteRepo.deleteById(id);
	    
		return wastageFromRepo;
	}
	
	

}
