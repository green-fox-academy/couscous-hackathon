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
public class CartItemRequestDTO {
    @JsonProperty(value = "item_id")
    private Long itemId;
    @JsonProperty(value = "item_amount")
    private Integer itemAmount;
}
