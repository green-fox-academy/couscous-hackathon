package com.greenfoxacademy.webshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDTO {
  private Long id;
  private String title;
  private Integer price;
  private String image;
  @JsonProperty(value = "user_amount")
  private Integer userAmount;
  @JsonProperty(value = "final_price")
  private Integer finalPrice;
}
