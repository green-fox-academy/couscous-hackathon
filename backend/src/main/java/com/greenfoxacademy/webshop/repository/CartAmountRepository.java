package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.CartAmount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartAmountRepository extends CrudRepository<CartAmount, Long> {
  Optional<CartAmount> findCartAmountByItem_Id(long item_id);
  Optional<CartAmount> findCartAmountByItem_IdAndCart_Id(long itemId,String cartId);
}
