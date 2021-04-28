package com.greenfoxacademy.webshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ItemDescriptionDTO {
  private Long id;
  private String title;
  private Integer price;
  private String description;
  @JsonProperty(value = "image_list")
  private List<Image> imageList;
  private Integer amount;
  private Category category;
}
