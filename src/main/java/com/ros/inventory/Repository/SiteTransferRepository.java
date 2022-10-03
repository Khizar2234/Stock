package com.ros.inventory.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.SiteTransfer;
@Repository
public interface SiteTransferRepository extends JpaRepository<SiteTransfer, UUID>{

}