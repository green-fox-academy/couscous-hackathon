package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.model.CartAmount;
import com.greenfoxacademy.webshop.model.Purchase;
import com.greenfoxacademy.webshop.model.PurchaseAmount;
import com.greenfoxacademy.webshop.repository.PurchaseAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseAmountService {
  @Autowired
  private PurchaseAmountRepository purchaseAmountRepository;

  public PurchaseAmount savePurchaseAmount(PurchaseAmount purchaseAmount) {
    return purchaseAmountRepository.save(purchaseAmount);
  }

  public PurchaseAmount cartAmountToPurchaseAmount(CartAmount cartAmount) {

    Purchase purchase = new Purchase();
    purchase.setTotalPrice(
        cartAmount.getAmount() * cartAmount.getItem().getPrice()
    );
    return savePurchaseAmount(new PurchaseAmount(purchase, cartAmount.getItem(), cartAmount.getAmount()));
  }
}
