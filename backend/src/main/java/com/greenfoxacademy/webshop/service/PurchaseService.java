package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.exception.CartNotFoundException;
import com.greenfoxacademy.webshop.exception.PurchaseException;
import com.greenfoxacademy.webshop.model.Cart;
import com.greenfoxacademy.webshop.model.CartAmount;
import com.greenfoxacademy.webshop.model.CartItemResponseDTO;
import com.greenfoxacademy.webshop.model.Item;
import com.greenfoxacademy.webshop.model.Purchase;
import com.greenfoxacademy.webshop.model.PurchaseAmount;
import com.greenfoxacademy.webshop.model.PurchaseResponseDTO;
import com.greenfoxacademy.webshop.model.User;
import com.greenfoxacademy.webshop.repository.CartAmountRepository;
import com.greenfoxacademy.webshop.repository.PurchaseAmountRepository;
import com.greenfoxacademy.webshop.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {
  @Autowired
  private PurchaseRepository purchaseRepository;
  @Autowired
  private PurchaseAmountRepository purchaseAmountRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private CartService cartService;
  @Autowired
  private EmailService emailService;
  @Autowired
  private CartAmountRepository cartAmountRepository;

  public Purchase savePurchase(Purchase purchase) {
    return purchaseRepository.save(purchase);
  }

  public Purchase cartToPurchase(String cartId, User user)
      throws CartNotFoundException {

    List<PurchaseAmount> purchaseAmountList = new ArrayList<>();
//    fillPurchaseAmount(cartId, purchaseAmountList);
    Purchase purchase = new Purchase(user, purchaseAmountList);

    Integer finalPrice =
        cartService.toCartResponseDTO(cartService.getCartList(cartId)).getFinalPrice();
    purchase.setTotalPrice(finalPrice);

    return purchase;
  }

  public void checkout(String cartId, User user) throws CartNotFoundException, PurchaseException, MessagingException {
    Purchase purchase = savePurchase(
        cartToPurchase(cartId, user));
    purchase.setPurchaseAmounts(getPurchaseList(purchase, cartId));
    purchaseRepository.save(purchase);
    emailService.sendCheckoutMessage(user, purchase);


    cartService.deleteCart(cartId);
    return;
  }

  public void fillPurchaseAmount(String cartId, List<PurchaseAmount> purchaseAmountList) {
    Cart cart = cartService.getCartById(cartId);

    for (int i = 0; i < cart.getCartAmounts().size(); i++) {
      purchaseAmountList.add(cartAmountToPurchaseAmount(cart.getCartAmounts().get(i)));
    }
  }

  public PurchaseAmount cartAmountToPurchaseAmount(CartAmount cartAmount) {

    Purchase purchase = new Purchase();
    purchase.setTotalPrice(
        cartAmount.getAmount() * cartAmount.getItem().getPrice()
    );
    purchase.setUser(userService.getAuthenticatedUser());
    savePurchase(purchase);
    return purchaseAmountRepository.save(new PurchaseAmount(purchase, cartAmount.getItem(), cartAmount.getAmount()));
  }

//  public PurchaseResponseDTO toPurchaseResponseDTO(Purchase purchase) {
//    int finalPrice = purchase.getTotalPrice();
////    for (CartItemResponseDTO cartItemResponseDTO : list) {
////      finalPrice = finalPrice + cartItemResponseDTO.getFinalPrice();
////    }
//
//    return new PurchaseResponseDTO(list, finalPrice);
//  }

  public List<PurchaseAmount> getPurchaseList(Purchase purchase, String cartId)
      throws CartNotFoundException, PurchaseException {
    List<PurchaseAmount> purchaseList = new ArrayList<>();
    Cart cart = cartService.getCartById(cartId);
//    cart.getCartAmounts().forEach(x -> purchaseList.add(
//        purchaseAmountRepository.save(new PurchaseAmount(purchase, x.getItem(), x.getAmount())
//        )));
    for (int i = 0; i < cart.getCartAmounts().size(); i++){
      purchaseAmountRepository.save(new PurchaseAmount(purchase, cart.getCartAmounts().get(i).getItem(), cart.getCartAmounts().get(i).getAmount()));
      cartAmountRepository.delete(cart.getCartAmounts().get(i));
    }
    return purchaseList;
  }

  public Purchase getPurchaseById(Long purchaseId) throws PurchaseException {
    return purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> new PurchaseException("No purchase with this id is found."));
  }

  public CartItemResponseDTO itemToCartItemResponseDTO(Item item, Long id)
      throws CartNotFoundException, PurchaseException {
    CartItemResponseDTO dto = new CartItemResponseDTO(
        item.getId(),
        item.getTitle(),
        item.getPrice(),
        item.getImages().get(0).getUrl(),
        getPurchaseAmountByItemAndCartId(item.getId(), id).getAmount(),
        getPurchaseAmountByItemAndCartId(item.getId(), id).getAmount() * item.getPrice()
    );
    return dto;
  }

  public PurchaseAmount getPurchaseAmountByItemAndCartId(Long itemId, Long id) throws PurchaseException {
    return purchaseAmountRepository.findByItem_IdAndPurchase_Id(itemId, id)
        .orElseThrow(() -> new PurchaseException("no purchase found with this id."));
  }
}
