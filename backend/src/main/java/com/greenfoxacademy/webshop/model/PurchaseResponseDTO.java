package com.greenfoxacademy.webshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponseDTO {
  @JsonProperty(value = "purchased_list")
  List<CartItemResponseDTO> purchasedItemResponseDTOList;
  @JsonProperty(value = "final_price")
  Integer finalPrice;
}
