package com.ros.inventory.controller.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CloseStockDateDto {

    private Date start_date;
    private Date end_date;
}

