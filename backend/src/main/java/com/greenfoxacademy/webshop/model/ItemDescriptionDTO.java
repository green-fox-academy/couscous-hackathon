package com.greenfoxacademy.webshop.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDescriptionDTO {
  private Long id;
  private String title;
  private Integer price;
  private String description;
  @JsonProperty(value = "image_url_list")
  private List<String> imageUrlList;
  private Integer amount;
  private Category category;
}
