package com.ros.inventory.Repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.CloseStock;

@Repository
public interface CloseStockRepository extends JpaRepository<CloseStock,UUID> {

   	@Query(value="SELECT * FROM close_stock ORDER BY stock_end_date DESC limit 1",nativeQuery=true)
	public CloseStock OpeningDate();

}
