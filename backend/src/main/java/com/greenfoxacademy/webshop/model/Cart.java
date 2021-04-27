package com.greenfoxacademy.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Cart {
  @Id
  private String id;
  private Timestamp createdAt;
  @OneToMany(mappedBy = "cart")
  private List<CartAmount> cartAmounts;

}
