package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.Cart;
import com.greenfoxacademy.webshop.model.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
