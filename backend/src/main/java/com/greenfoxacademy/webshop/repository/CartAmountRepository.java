package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.CartAmount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartAmountRepository extends CrudRepository<CartAmount, Long> {
}
