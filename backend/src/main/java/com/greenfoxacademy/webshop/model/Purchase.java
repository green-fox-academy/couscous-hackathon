package com.greenfoxacademy.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Purchase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Timestamp purchasedAt;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @OneToMany(mappedBy = "purchase")
  private List<PurchaseAmount> purchaseAmounts;
  private int totalPrice;

  public Purchase(User user, List<PurchaseAmount> purchaseAmounts){
    this.purchasedAt = new Timestamp(System.currentTimeMillis());
    this.user = user;
    this.purchaseAmounts = purchaseAmounts;
    int sumOfTotalPrices = 0;
    for (PurchaseAmount purchaseAmount : purchaseAmounts) {
      sumOfTotalPrices = sumOfTotalPrices + purchaseAmount.getItem().getPrice() * purchaseAmount.getAmount();
    }
    this.totalPrice = sumOfTotalPrices;
  }

  public Purchase() {
    this.purchasedAt = new Timestamp(System.currentTimeMillis());
    this.purchaseAmounts = new ArrayList<>();
  }

}
