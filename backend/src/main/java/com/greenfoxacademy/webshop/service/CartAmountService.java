package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.CartAmount;
import com.greenfoxacademy.webshop.repository.CartAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartAmountService {

  @Autowired
  private CartAmountRepository cartAmountRepository;

  public CartAmount getCartAmountByItemId(Long itemId) throws ItemNotFoundException {
    return cartAmountRepository.findCartAmountByItem_Id(itemId).orElseThrow(() -> new ItemNotFoundException("Item is not found with this id."));
  }
}
