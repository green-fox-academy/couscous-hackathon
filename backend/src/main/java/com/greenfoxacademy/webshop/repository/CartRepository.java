package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart,String> {
  Optional<Cart> findById(String cartId);
}
