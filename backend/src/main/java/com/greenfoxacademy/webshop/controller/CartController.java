package com.greenfoxacademy.webshop.controller;

import com.greenfoxacademy.webshop.exception.CartNotFoundException;
import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.CartItemRequestDTO;
import com.greenfoxacademy.webshop.model.CartItemResponseDTO;
import com.greenfoxacademy.webshop.model.CartRequestDTO;
import com.greenfoxacademy.webshop.model.CartResponseDTO;
import com.greenfoxacademy.webshop.model.PriceResponseDTO;
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
import java.util.Arrays;
import java.util.List;

@RestController
public class CartController {

  @Autowired
  private CartService cartService;

  @CrossOrigin
  @PostMapping("/cart")
  public ResponseEntity<Object> newCartItem(@RequestBody CartItemRequestDTO cartItem, HttpServletRequest request)
      throws ItemNotFoundException {
    String cartId = request.getSession().getId();
    cartService.addItemToCart(cartItem, cartId);
    return ResponseEntity.ok("ok");
  }

  @CrossOrigin
  @GetMapping("/cart")
  public ResponseEntity<CartResponseDTO> getCart(CartRequestDTO cartRequestDTO) throws CartNotFoundException {
    return ResponseEntity.ok(cartService.toCartResponseDTO(cartService.getCartList(cartRequestDTO)));
  }

  @CrossOrigin
  @PutMapping("/cart")
  public ResponseEntity<?> modifyCartItem(CartItemRequestDTO cartItemRequestDTO) {
    return ResponseEntity.ok(new PriceResponseDTO(300, 1200));
  }

  @CrossOrigin
  @DeleteMapping("/cart")
  public ResponseEntity<List<CartItemResponseDTO>> deleteItemFromCart(CartRequestDTO cartRequestDTO) {
    return ResponseEntity.ok(Arrays.asList(
        new CartItemResponseDTO(1L, "TestTitle", 200, "https://i.pravatar.cc", 2, 400),
        new CartItemResponseDTO(2L, "TestTitle2", 200, "https://i.pravatar.cc", 2, 400),
        new CartItemResponseDTO(3L, "TestTitle3", 200, "https://i.pravatar.cc", 2, 400)
    ));
  }

  @CrossOrigin
  @PostMapping("/payment")
  public ResponseEntity<?> postPayment(CartRequestDTO cartRequestDTO) {
    return ResponseEntity.ok("dummy payment ok");
  }
}
