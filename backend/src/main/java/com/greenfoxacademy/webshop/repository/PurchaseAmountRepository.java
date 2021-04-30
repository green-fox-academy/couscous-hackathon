package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.PurchaseAmount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseAmountRepository extends CrudRepository<PurchaseAmount, Long> {
  Optional<PurchaseAmount> findByItem_IdAndPurchase_Id(Long itemId, Long purchaseId);
}
