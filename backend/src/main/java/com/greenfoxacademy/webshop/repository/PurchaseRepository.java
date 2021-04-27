package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
