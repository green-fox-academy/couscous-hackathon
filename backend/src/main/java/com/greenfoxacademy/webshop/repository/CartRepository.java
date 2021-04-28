package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {
}
