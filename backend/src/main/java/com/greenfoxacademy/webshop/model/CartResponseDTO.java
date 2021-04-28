package com.greenfoxacademy.webshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartResponseDTO {
  @JsonProperty(value = "item_list")
  List<CartItemResponseDTO> cartItemResponseDTOList;
  @JsonProperty(value = "final_price")
  Integer finalPrice;
}
