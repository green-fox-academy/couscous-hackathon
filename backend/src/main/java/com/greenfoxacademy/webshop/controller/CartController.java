package com.greenfoxacademy.webshop.controller;

import com.greenfoxacademy.webshop.exception.CartNotFoundException;
import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.exception.MissingCartIdException;
import com.greenfoxacademy.webshop.exception.PurchaseException;
import com.greenfoxacademy.webshop.model.CartItemRequestDTO;
import com.greenfoxacademy.webshop.model.CartRequestDTO;
import com.greenfoxacademy.webshop.model.CartResponseDTO;
import com.greenfoxacademy.webshop.model.Purchase;
import com.greenfoxacademy.webshop.model.PurchaseResponseDTO;
import com.greenfoxacademy.webshop.service.CartService;
import com.greenfoxacademy.webshop.service.PurchaseService;
import com.greenfoxacademy.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CartController {

  @Autowired
  private CartService cartService;
  @Autowired
  private PurchaseService purchaseService;
  @Autowired
  private UserService userService;

  @PostMapping("/cart")
  public ResponseEntity<Object> newCartItem(@RequestBody CartItemRequestDTO cartItem, HttpServletRequest request)
      throws ItemNotFoundException, MissingCartIdException {

    String cartId = cartService.getCartId(request);
    cartService.addItemToCart(cartItem, cartId);
    return ResponseEntity.ok("ok");
  }

  @GetMapping("/cart")
  public ResponseEntity<CartResponseDTO> getCart(HttpServletRequest request)
      throws CartNotFoundException, MissingCartIdException {
    String cartId = cartService.getCartId(request);
    return ResponseEntity
        .ok(cartService.toCartResponseDTO(cartService.getCartList(cartId)));

  }

  @PutMapping("/cart")
  public ResponseEntity<?> modifyCartItem(@RequestBody CartItemRequestDTO cartItem, HttpServletRequest request)
      throws ItemNotFoundException, CartNotFoundException, MissingCartIdException {

    String cartId = cartService.getCartId(request);
    cartService.addItemToCart(cartItem, cartId);
    return ResponseEntity
        .ok(cartService.toCartResponseDTO(cartService.getCartList(cartId)));
  }

  @DeleteMapping("/cart")
  public ResponseEntity<CartResponseDTO> deleteItemFromCart(@RequestBody CartRequestDTO cartRequestDTO,
                                                            HttpServletRequest request)
      throws CartNotFoundException, MissingCartIdException {

    String cartId = cartService.getCartId(request);
    cartService.deleteItemFromCart(cartRequestDTO, cartId);
    return ResponseEntity
        .ok(cartService.toCartResponseDTO(cartService.getCartList(cartId)));
  }

  @PostMapping("/payment")
  public ResponseEntity<String> postCheckout(HttpServletRequest request)
      throws CartNotFoundException, PurchaseException, MissingCartIdException, MessagingException {
    String cartId = cartService.getCartId(request);
    purchaseService.checkout(cartId, userService.getAuthenticatedUser());
    return ResponseEntity.ok("ok");
  }
}
