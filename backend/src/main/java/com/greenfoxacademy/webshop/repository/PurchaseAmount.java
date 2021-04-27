package com.greenfoxacademy.webshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseAmount extends CrudRepository<PurchaseAmount, Long> {
}
