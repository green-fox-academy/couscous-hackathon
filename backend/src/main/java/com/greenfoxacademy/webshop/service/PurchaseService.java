package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.exception.CartNotFoundException;
import com.greenfoxacademy.webshop.exception.MissingCartIdException;
import com.greenfoxacademy.webshop.exception.PurchaseException;
import com.greenfoxacademy.webshop.model.Cart;
import com.greenfoxacademy.webshop.model.Purchase;
import com.greenfoxacademy.webshop.model.CartItemResponseDTO;
import com.greenfoxacademy.webshop.model.Item;
import com.greenfoxacademy.webshop.model.Purchase;
import com.greenfoxacademy.webshop.model.PurchaseAmount;
import com.greenfoxacademy.webshop.model.PurchaseResponseDTO;
import com.greenfoxacademy.webshop.model.User;
import com.greenfoxacademy.webshop.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {
  @Autowired
  private PurchaseRepository purchaseRepository;
  @Autowired
  private PurchaseAmountService purchaseAmountService;
  @Autowired
  private UserService userService;
  @Autowired
  private CartService cartService;
  @Autowired
  private CartAmountService cartAmountService;

  public Purchase savePurchase(Purchase purchase) {
    return purchaseRepository.save(purchase);
  }

  public Purchase cartToPurchase(String cartId)
      throws CartNotFoundException {

    User user = userService.getAuthenticatedUser();
    List<PurchaseAmount> purchaseAmountList = new ArrayList<>();
    Cart cart = cartService.getCartById(cartId);

    for (int i = 0; i < cart.getCartAmounts().size(); i++) {
      purchaseAmountList.add(purchaseAmountService.cartAmountToPurchaseAmount(cart.getCartAmounts().get(i)));
    }

    Purchase purchase = new Purchase(user, purchaseAmountList);

    Integer finalPrice =
        cartService.toCartResponseDTO(cartService.getCartList(cartId)).getFinalPrice();
    purchase.setTotalPrice(finalPrice);

    return purchase;
  }

  public PurchaseResponseDTO toPurchaseResponseDTO(List<CartItemResponseDTO> list) {
    int finalPrice = 0;
    for (CartItemResponseDTO cartItemResponseDTO : list) {
      finalPrice = finalPrice + cartItemResponseDTO.getFinalPrice();
    }
    return new PurchaseResponseDTO(list, finalPrice);
  }

  public List<CartItemResponseDTO> getPurchaseList(Long id, String cartId)
      throws CartNotFoundException, PurchaseException {
    List<CartItemResponseDTO> purchaseList = new ArrayList<>();
    Purchase purchase = getPurchaseById(id);
    for (PurchaseAmount purchaseAmount : purchase.getPurchaseAmounts()) {
      purchaseList.add(itemToCartItemResponseDTO(purchaseAmount.getItem(), cartId));
    }
    return purchaseList;
  }

  public Purchase getPurchaseById(Long purchaseId) throws PurchaseException {
    return purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> new PurchaseException("No purchase with this id is found."));
  }

  public CartItemResponseDTO itemToCartItemResponseDTO(Item item, String cartId) throws CartNotFoundException {
    CartItemResponseDTO dto = new CartItemResponseDTO(
        item.getId(),
        item.getTitle(),
        item.getPrice(),
        item.getImages().get(0).getUrl(),
        cartAmountService.getCartAmountByItemAndCartId(item.getId(), cartId).getAmount(),
        cartAmountService.getCartAmountByItemAndCartId(item.getId(), cartId).getAmount() * item.getPrice()
    );
    return dto;
  }
}
