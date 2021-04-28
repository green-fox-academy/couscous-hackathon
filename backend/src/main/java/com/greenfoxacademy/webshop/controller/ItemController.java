package com.greenfoxacademy.webshop.controller;

import com.greenfoxacademy.webshop.exception.CartNotFoundException;
import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.ItemDescriptionDTO;
import com.greenfoxacademy.webshop.model.ItemResponseDTO;
import com.greenfoxacademy.webshop.model.PriceResponseDTO;
import com.greenfoxacademy.webshop.service.EmailService;
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

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

  @CrossOrigin
  @GetMapping("/item/{id}")
  public ResponseEntity<ItemDescriptionDTO> getItemById(@PathVariable Long id, HttpServletRequest request)
      throws ItemNotFoundException, CartNotFoundException {
    return ResponseEntity.ok(itemService
        .itemToDescriptionDTO(itemService.getItemById(id), request.getSession().getId()));
  }



    @CrossOrigin
    @GetMapping("/item")
    public ResponseEntity<List<ItemResponseDTO>> getItemList(
            @RequestParam(required = false) String search,
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {

        return ResponseEntity.ok(itemService.getItems(search, page, pageSize));
    }

    @CrossOrigin
    @PostMapping("/cart")
    public ResponseEntity<Object> newCart(List<CartItemRequestDTO> cartList) {
        return ResponseEntity.ok("ok");
    }

    @CrossOrigin
    @GetMapping("/cart")
    public ResponseEntity<List<CartItemResponseDTO>> getCart(CartRequestDTO cartRequestDTO) {
        return ResponseEntity.ok(Arrays.asList(
                new CartItemResponseDTO(1L, "TestTitle", 200, "https://i.pravatar.cc", 100, 2, 400),
                new CartItemResponseDTO(2L, "TestTitle2", 200, "https://i.pravatar.cc", 100, 2, 400),
                new CartItemResponseDTO(3L, "TestTitle3", 200, "https://i.pravatar.cc", 100, 2, 400)
        ));
    }

    @CrossOrigin
    @PutMapping("/cart")
    public ResponseEntity<?> modifyCartItem(CartItemRequestDTO cartItemRequestDTO) {
        return ResponseEntity.ok(new PriceResponseDTO(300, 1200));
    }

    @CrossOrigin
    @DeleteMapping("/cart")
    public ResponseEntity<List<CartItemResponseDTO>> deleteItemFromCart(CartRequestDTO cartRequestDTO) {
        return ResponseEntity.ok(Arrays.asList(
                new CartItemResponseDTO(1L, "TestTitle", 200, "https://i.pravatar.cc", 100, 2, 400),
                new CartItemResponseDTO(2L, "TestTitle2", 200, "https://i.pravatar.cc", 100, 2, 400),
                new CartItemResponseDTO(3L, "TestTitle3", 200, "https://i.pravatar.cc", 100, 2, 400)
        ));
    }

    @CrossOrigin
    @PostMapping("/payment")
    public ResponseEntity<?> postPayment(CartRequestDTO cartRequestDTO) {
        return ResponseEntity.ok("dummy payment ok");
    }
}

