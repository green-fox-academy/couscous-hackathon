package com.greenfoxacademy.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  private int price;
  private String description;
  private Category category;
  @OneToMany(mappedBy ="item")
  private List<Image> images;

  @OneToMany(mappedBy = "item")
  private List<CartAmount> cartAmounts;
  @OneToMany(mappedBy = "item")
  private List<PurchaseAmount> purchaseAmounts;

}
