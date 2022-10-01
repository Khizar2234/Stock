package com.ros.inventory.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.CloseStock;

@Repository
public interface CloseStockRepository extends JpaRepository<CloseStock,UUID> {

   	@Query(value="SELECT * FROM close_stock ORDER BY stock_end_date DESC limit 1",nativeQuery=true)
	public CloseStock OpeningDate();
   	@Query(value = "SELECT * from close_stock where stock_start_date >= :start_date and stock_start_date <= :end_date",nativeQuery = true)
    public List<CloseStock> getStockPeriod(@Param("start_date") Date start_date, @Param("end_date") Date end_date );

}
