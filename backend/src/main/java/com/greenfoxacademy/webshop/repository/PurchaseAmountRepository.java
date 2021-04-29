package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.PurchaseAmount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseAmountRepository extends CrudRepository<PurchaseAmount, Long> {
}
