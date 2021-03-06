package com.greenfoxacademy.webshop.controller;

import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.ItemDescriptionDTO;
import com.greenfoxacademy.webshop.model.ItemResponseDTO;
import com.greenfoxacademy.webshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDescriptionDTO> getItemById(@PathVariable Long id)
            throws ItemNotFoundException {
        return ResponseEntity.ok(itemService
                .itemToDescriptionDTO(itemService.getItemById(id)));
    }

    @GetMapping("/item")
    public ResponseEntity<List<ItemResponseDTO>> getItemList(
            @RequestParam(required = false) String search,
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            HttpServletRequest request) {

        return ResponseEntity.ok(
                itemService.getItems(search, page, pageSize, request));
    }

}
