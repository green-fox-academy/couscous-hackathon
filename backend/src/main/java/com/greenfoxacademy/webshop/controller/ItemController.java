package com.greenfoxacademy.webshop.controller;

import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.CartItemRequestDTO;
import com.greenfoxacademy.webshop.model.CartItemResponseDTO;
import com.greenfoxacademy.webshop.model.CartRequestDTO;
import com.greenfoxacademy.webshop.model.Category;
import com.greenfoxacademy.webshop.model.Image;
import com.greenfoxacademy.webshop.model.Item;
import com.greenfoxacademy.webshop.model.ItemDescriptionDTO;
import com.greenfoxacademy.webshop.model.ItemResponseDTO;
import com.greenfoxacademy.webshop.model.PriceResponseDTO;
import com.greenfoxacademy.webshop.repository.ItemRepository;
import com.greenfoxacademy.webshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
public class ItemController {

  @Autowired
  private ItemService itemService;

  @CrossOrigin
  @GetMapping("/item/{id}")
  public ResponseEntity<ItemDescriptionDTO> getItemById(@PathVariable Long id) throws ItemNotFoundException {
    return ResponseEntity.ok(itemService.itemToDescriptionDTO(itemService.getItemById(id)));
  }

  @CrossOrigin
  @GetMapping("/item")
  public ResponseEntity<List<ItemResponseDTO>> getItemList(
      @RequestParam(required = false) String search,
      @RequestParam Integer page,
      @RequestParam Integer pageSize
  ) {

    return ResponseEntity.ok(
        itemService.getItems(search, page, pageSize)
    );
  }
}

