package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.exception.CartNotFoundException;
import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.CartAmount;
import com.greenfoxacademy.webshop.repository.CartAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartAmountService {

  @Autowired
  private CartAmountRepository cartAmountRepository;

  public CartAmount getCartAmountByItemAndCartId(Long itemId, String cartId) throws CartNotFoundException {
    return cartAmountRepository.findCartAmountByItem_IdAndCart_Id(itemId, cartId).orElseThrow(() -> new CartNotFoundException("Index cannot be found with specified item_id and/or cart_id."));
  }
}
