package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.model.Item;
import com.greenfoxacademy.webshop.model.ItemResponseDTO;
import com.greenfoxacademy.webshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  public List<ItemResponseDTO> getItems (String title,String description,String priceRange) {
    List<Item> items = itemRepository.findAll();
    List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();
    for (Item item: items
         ) {
      ItemResponseDTO itemResponseDTO = new ItemResponseDTO(item.getId(),item.getTitle(),item.getPrice(),item.getImages().get(0).getUrl(), item.getCategory());
      itemResponseDTOS.add(itemResponseDTO);
    }
    return itemResponseDTOS;
  }
}
