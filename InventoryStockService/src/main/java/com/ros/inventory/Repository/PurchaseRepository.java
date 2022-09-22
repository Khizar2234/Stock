package com.ros.inventory.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.Attachments;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.Supplier;


@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseOrder,UUID>
{
	
	@Query(value="SELECT * FROM purchase_order WHERE purchase_id = :purchase_id",nativeQuery=true)
	PurchaseOrder  getById(@Param("purchase_id") UUID purchasedId);
	
	
	
	@Query(value = "SELECT * FROM purchase_order", nativeQuery = true)
	List<PurchaseOrder> getAll();
	
	@Query(value = "SELECT * FROM purchase_order WHERE purchase_order_status = :purchase_order_status",nativeQuery=true)
	List<PurchaseOrder> showByStatus(@Param("purchase_order_status")String purchaseOrderStatus);


	@Query(value="select * from purchase_order where purchase_order_state=:purchase_order_state",nativeQuery=true)
	List<PurchaseOrder> getAll(@Param("purchase_order_state") String purchaseOrderStatus);



	//return attachments details
	@Query(value = "SELECT * FROM attachments", nativeQuery = true)
	List<Attachments> getAllAttachments();

	@Query(value = "SELECT * FROM puchase_order p LEFT JOIN invoice i on p.purchase_id=i.invoice_id"
			+ " where i.invoice_id=:invoice_id ", nativeQuery = true)
	PurchaseOrder getByInv(@Param("invoice_id") UUID id);
	
	@Query(value="select * from purchase_order where purchase_order_status =:status Order By purchase_order_status ASC limit 1",nativeQuery=true)
    PurchaseOrder getLastSession(@Param("status") String status);

	@Query(value="select * from purchase_order where purchase_order_status =:status and purchase_order_date >:date Order By purchase_order_status ASC limit 1",nativeQuery=true)
    PurchaseOrder getStartSessionDate(@Param("status") String status,@Param("date") LocalDate date);

   


}
