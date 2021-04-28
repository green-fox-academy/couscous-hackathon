package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.Cart;
import com.greenfoxacademy.webshop.model.CartAmount;
import com.greenfoxacademy.webshop.model.CartItemRequestDTO;
import com.greenfoxacademy.webshop.repository.CartAmountRepository;
import com.greenfoxacademy.webshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CartService {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartAmountRepository cartAmountRepository;

  @Autowired
  ItemService itemService;

  public void addItemToCart(CartItemRequestDTO request, String cartId) throws ItemNotFoundException {
    Cart cart = cartRepository.findById(cartId)
        .orElse(cartRepository.save(new Cart(cartId, new Timestamp(System.currentTimeMillis()), null)));
    Optional<CartAmount> optionalCartAmount =
        cartAmountRepository.findCartAmountByItem_IdAndCart_Id(request.getItemId(), cartId);
    CartAmount cartAmount;
    if (optionalCartAmount.isPresent()) {
      cartAmount = optionalCartAmount.get();
      cartAmount.setAmount(cartAmount.getAmount() + request.getItemAmount());
    } else {
      cartAmount = new CartAmount(cart, itemService.getItemById(request.getItemId()), request.getItemAmount());
    }
    cartAmountRepository.save(cartAmount);
  }
}
