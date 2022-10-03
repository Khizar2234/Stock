package com.ros.inventory.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.Invoice;
import com.ros.inventory.entities.OpeningStock;

@Repository
public interface OpeningStockRepository extends JpaRepository<OpeningStock, Long>{

}
