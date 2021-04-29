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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PurchaseAmount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "purchase_id")
  private Purchase purchase;
  @ManyToOne
  @JoinColumn(name = "item_id")
  private Item item;
  int amount;

  public PurchaseAmount(Purchase purchase, Item item, int amount){
    this.purchase = purchase;
    this.item = item;
    this.amount = amount;
  }
}
