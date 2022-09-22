package com.ros.inventory.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ros.inventory.entities.Attachments;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,UUID>
{
  @Query(value="SELECT * FROM product p LEFT JOIN supplier s on p.supplier_id = s.supplier_id "
  		+ " WHERE s.supplier_id =:supplier_id",nativeQuery=true)
  List<Product> getAll(@Param("supplier_id") UUID id);
  
  @Query(value = "SELECT * FROM product where product_id=:product_id", nativeQuery = true)
	Product getById(@Param("product_id") UUID productId);
/*------------------------------------------------------------------------*/

@Query(value="select * from product p where product_id=:product_id",nativeQuery=true)
  Product getByID(@Param("product_id") UUID product_id);

 @Query(value="select * from product p where product_date>'2020-02-12'",nativeQuery=true)
  List<Product> getdata();

 @Query(value="select * from product p where product_code=:product_code",nativeQuery=true)
  Product getByIDCode(@Param("product_code") long code);
 
  
  
  //Get all the details available in product list
  @Query(value = "SELECT * FROM product", nativeQuery = true)
	List<Product> getAll();
  //saving  as draft
  @Query(value = "SELECT * FROM purchase_order WHERE purchase_status = :purchase_status",nativeQuery=true)
   List<PurchaseOrder> showByStatus(@Param("purchase_status")String purchaseOrderStatus);
  
}
