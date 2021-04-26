package com.greenfoxacademy.webshop.controller;

import com.greenfoxacademy.webshop.model.CartItemRequestDTO;
import com.greenfoxacademy.webshop.model.ItemResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ItemController {

    @CrossOrigin
    @GetMapping("/item/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(new ItemResponseDTO(
                1L, "TestItem", 50, "Description of TestItem", Arrays.asList("https://i.pravatar.cc"), "electonic"));
    }

    @CrossOrigin
    @GetMapping("/item")
    public ResponseEntity<List<ItemResponseDTO>> getItemList() {
        return ResponseEntity.ok(Arrays.asList(
                new ItemResponseDTO(
                        1L, "TestItem", 50, "Description of TestItem", Arrays.asList("https://i.pravatar.cc"), "electonic"),
                new ItemResponseDTO(
                        2L, "TestItem2", 80, "Description of second TestItem", Arrays.asList("https://i.pravatar.cc"), "electonic"),
                new ItemResponseDTO(
                        3L, "TestItem3", 70, "Description of third TestItem", Arrays.asList("https://i.pravatar.cc"), "hygiene")));
    }

    @CrossOrigin
    @PostMapping("/cart")
    public ResponseEntity<Object> newCart(List<CartItemRequestDTO> cartList) {
        return ResponseEntity.ok("ok");
    }
}
