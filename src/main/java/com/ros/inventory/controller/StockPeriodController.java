package com.ros.inventory.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.entities.ClosingStock;
import com.ros.inventory.entities.SiteTransfer;
import com.ros.inventory.entities.SiteTransferProduct;
import com.ros.inventory.entities.StockPeriod;
import com.ros.inventory.service.IStockPeriod;
import com.ros.inventory.serviceImpl.SiteTransferService;

@RestController
@RequestMapping("/stockPeriod")
@CrossOrigin("*")
public class StockPeriodController {

    @Autowired
    IStockPeriod service;
    
    @Autowired
    SiteTransferService siteService;

    @GetMapping("/getStartDate")
    public ResponseEntity<?> getStockPeriodStartDate() {
        ResponseEntity<?> response = null;
        try {
            response = new ResponseEntity<>(service.getStockPeriodStartDate(), HttpStatus.OK);
        } catch (NoOpenStockPeriodFound e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/getClosingStockValue")
    public ResponseEntity<?> getClosingStockValue() {
        ResponseEntity<?> response = null;
        try {
            response = new ResponseEntity<>(service.getClosingStockValue(), HttpStatus.OK);
        } catch (NoOpenStockPeriodFound e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/getTotalClosingStockValue")
    public ResponseEntity<?> getToatlClosingStockValue() {
        ResponseEntity<?> response = null;
        response = new ResponseEntity<>(service.totalClosedStockValue(), HttpStatus.OK);
        return response;
    }

    @GetMapping("/getOpeningStockValue")
    public ResponseEntity<?> getOpeningStockValue() {
        ResponseEntity<?> response = null;
        try {
            response = new ResponseEntity<>(service.getOpeningStockValue(), HttpStatus.OK);
        } catch (NoOpenStockPeriodFound e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/saveClosingStockValue")
    public ResponseEntity<?> savingClosingStocks(@RequestBody List<ClosingValueDto> cvd) {
        ResponseEntity<?> response = null;
        try {
            response = new ResponseEntity<>(service.saveClosingValue(cvd), HttpStatus.OK);
        } catch (NoOpenStockPeriodFound e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody StockPeriod sp) {
        ResponseEntity<?> response = null;
        try {
            response = new ResponseEntity<>(service.add(sp), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }
    
    @GetMapping("/getAllWastedProducts/{id}")
    public ResponseEntity<?> getAllWastedProducts(@PathVariable UUID id) {
        ResponseEntity<?> response = null;
        try {
            response = new ResponseEntity<>(service.getAllWastedProducts(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }
    
    @PostMapping("/addAllSiteTransferProducts/{id}")
    public ResponseEntity<?> getAllWastedProducts(@PathVariable UUID id,@RequestBody List<SiteTransferProduct> prods) {
        ResponseEntity<?> response = null;
        try {
        	siteService.addAllProducts(id, prods);
            response = new ResponseEntity<>("Added succesfully", HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }
    @PostMapping("/addSite")
    public ResponseEntity<?> addSite(@RequestBody SiteTransfer st) {
        ResponseEntity<?> response = null;
        try {
        	
            response = new ResponseEntity<>(siteService.add(st), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }
    @GetMapping("/getAllSiteTransfers/{id}")
    public ResponseEntity<?> getAllSiteTransfers(@PathVariable UUID id) {
        ResponseEntity<?> response = null;
        try {
            response = new ResponseEntity<>(service.getAllSiteTransfers(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }
    
    @GetMapping("/editinfo/{id}")
    public ResponseEntity<?> getEditInfo(@PathVariable UUID id) {
        ResponseEntity<?> response = null;
        try {
            response = new ResponseEntity<>(service.getEditInfo(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }

    
}

