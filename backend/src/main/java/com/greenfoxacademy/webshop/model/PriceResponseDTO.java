package com.greenfoxacademy.webshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponseDTO {
  @JsonProperty(value = "item_price")
  private Integer itemPrice;
  @JsonProperty(value = "final_price")
  private Integer finalPrice;
}
