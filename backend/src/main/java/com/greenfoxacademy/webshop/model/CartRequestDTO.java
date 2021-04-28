package com.greenfoxacademy.webshop.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartRequestDTO {
  @JsonProperty(value = "cart_id")
  private String cartId;
  @JsonProperty(value = "item_id")
  private Long itemId;
}
