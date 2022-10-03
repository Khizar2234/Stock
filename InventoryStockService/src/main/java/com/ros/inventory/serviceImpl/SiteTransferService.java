package com.ros.inventory.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ros.inventory.Repository.SiteTransferRepository;
import com.ros.inventory.entities.SiteTransfer;
import com.ros.inventory.entities.SiteTransferProduct;
import com.ros.inventory.service.ISiteTransferService;


@Transactional
@Service
public class SiteTransferService implements ISiteTransferService{
	
	@Autowired
	private SiteTransferRepository siteTransferRepo;

	@Override
	public List<SiteTransferProduct> getAllProducts(UUID id) throws Exception {
		Optional<SiteTransfer> siteTransfer = siteTransferRepo.findById(id);
		if(siteTransfer.isEmpty()) {
			throw new Exception("Site Transfer doesn't exist");
		}
		
		return siteTransfer.get().getSiteTransferProducts();
	}
	
	public void addAllProducts(UUID id,List<SiteTransferProduct> prods) throws Exception {
		Optional<SiteTransfer> siteTransfer = siteTransferRepo.findById(id);
		if(siteTransfer.isEmpty()) {
			throw new Exception("Site Transfer doesn't exist");
		}
		
		siteTransfer.get().setSiteTransferProducts(prods);
		
	}

	public SiteTransfer add(SiteTransfer st) {
		return this.siteTransferRepo.save(st);
		
	}
}
