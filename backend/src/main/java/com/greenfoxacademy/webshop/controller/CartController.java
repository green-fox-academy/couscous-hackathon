package com.greenfoxacademy.webshop.controller;

import com.greenfoxacademy.webshop.exception.CartNotFoundException;
import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.CartItemRequestDTO;
import com.greenfoxacademy.webshop.model.CartRequestDTO;
import com.greenfoxacademy.webshop.model.CartResponseDTO;
import com.greenfoxacademy.webshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class CartController {

  @Autowired
  private CartService cartService;

  @CrossOrigin
  @PostMapping("/cart")
  public ResponseEntity<Object> newCartItem(@RequestBody CartItemRequestDTO cartItem, HttpServletRequest request)
      throws ItemNotFoundException, CartNotFoundException {
    String cartId = cartService.getSessionId(request);
    cartService.addItemToCart(cartItem, cartId);
    return ResponseEntity.ok("ok");
  }

  @CrossOrigin
  @GetMapping("/cart")
  public ResponseEntity<CartResponseDTO> getCart(HttpServletRequest request)
      throws CartNotFoundException {
    return ResponseEntity
        .ok(cartService.toCartResponseDTO(cartService.getCartList(cartService.getSessionId(request))));
  }

  @CrossOrigin
  @PutMapping("/cart")
  public ResponseEntity<?> modifyCartItem(@RequestBody CartItemRequestDTO cartItem, HttpServletRequest request)
      throws ItemNotFoundException, CartNotFoundException {
    String cartId = cartService.getSessionId(request);
    cartService.addItemToCart(cartItem, cartId);
    return ResponseEntity
        .ok(cartService.toCartResponseDTO(cartService.getCartList(cartId)));
  }

  @CrossOrigin
  @DeleteMapping("/cart")
  public ResponseEntity<CartResponseDTO> deleteItemFromCart(@RequestBody CartRequestDTO cartRequestDTO, HttpServletRequest request)
      throws CartNotFoundException {
    String cartId = cartService.getSessionId(request);
    cartService.deleteItemFromCart(cartRequestDTO, cartId);
    return ResponseEntity
        .ok(cartService.toCartResponseDTO(cartService.getCartList(cartId)));
  }

  @CrossOrigin
  @PostMapping("/payment")
  public ResponseEntity<?> postPayment(CartRequestDTO cartRequestDTO) {
    return ResponseEntity.ok("dummy payment ok");
  }
}
