package com.ros.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.entities.ClosingStock;
import com.ros.inventory.service.StockPeriod;

@RestController
@RequestMapping("/stockPeriod")
@CrossOrigin("*")
public class StockPeriodController {

    @Autowired
    StockPeriod service;

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
    
    @GetMapping("/getClosedStockList")
    public ResponseEntity<?> getClosedStockList() {
        ResponseEntity<?> response = null;
        try {
            response = new ResponseEntity<>(service.getClosedStockList(), HttpStatus.OK);
        } catch (NoOpenStockPeriodFound e) {
            // TODO Auto-generated catch block
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return response;
    }
    
}
