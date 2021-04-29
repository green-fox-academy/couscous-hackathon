package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.exception.CustomExceptionHandler;
import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.Cart;
import com.greenfoxacademy.webshop.model.CartAmount;
import com.greenfoxacademy.webshop.model.CartItemRequestDTO;
import com.greenfoxacademy.webshop.repository.CartAmountRepository;
import com.greenfoxacademy.webshop.exception.CartNotFoundException;
import com.greenfoxacademy.webshop.model.Cart;
import com.greenfoxacademy.webshop.model.CartItemResponseDTO;
import com.greenfoxacademy.webshop.model.CartRequestDTO;
import com.greenfoxacademy.webshop.model.CartResponseDTO;
import com.greenfoxacademy.webshop.model.Item;
import com.greenfoxacademy.webshop.repository.CartRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartAmountRepository cartAmountRepository;

    @Autowired
    CartAmountService cartAmountService;

    @Autowired
    ItemService itemService;

  public void addItemToCart(CartItemRequestDTO request, String cartId) throws ItemNotFoundException {
    Cart cart = getCartById(cartId);

    Optional<CartAmount> optionalCartAmount =
        cartAmountRepository.findCartAmountByItem_IdAndCart_Id(request.getItemId(), cartId);
    CartAmount cartAmount;
    if (optionalCartAmount.isPresent()) {
      cartAmount = optionalCartAmount.get();
      int amountNonNegative = Math.max(cartAmount.getAmount() + request.getItemAmount(),0);
      cartAmount.setAmount(amountNonNegative);
    } else {
      cartAmount = new CartAmount(cart, itemService.getItemById(request.getItemId()), request.getItemAmount());
        }
        cartAmountRepository.save(cartAmount);
    }

    public void deleteItemFromCart(CartRequestDTO request, String cartId) throws CartNotFoundException {
        cartAmountService.deleteCartAmount(cartAmountService.getCartAmountByItemAndCartId(request.getItemId(), cartId));
    }

    public List<CartItemResponseDTO> getCartList(String cartId) throws CartNotFoundException {
        List<CartItemResponseDTO> cartList = new ArrayList<>();
        Cart cart = getCartById(cartId);
        for (CartAmount item : cart.getCartAmounts()) {
            cartList.add(itemToCartItemResponseDTO(item.getItem(), cartId));
        }
        return cartList;
    }

    private Cart getCartById(String cartId) {
        return cartRepository.findById(cartId)
                .orElse(cartRepository.save(new Cart(cartId)));
    }

    public CartResponseDTO toCartResponseDTO(List<CartItemResponseDTO> list) {
        int finalPrice = 0;
        for (CartItemResponseDTO cartItemResponseDTO : list) {
            finalPrice = finalPrice + cartItemResponseDTO.getFinalPrice();
        }
        return new CartResponseDTO(list, finalPrice);
    }

    public CartItemResponseDTO itemToCartItemResponseDTO(Item item, String cartId) throws CartNotFoundException {
        CartItemResponseDTO dto = new CartItemResponseDTO(
                item.getId(),
                item.getTitle(),
                item.getPrice(),
                item.getImages().get(0).getUrl(),
                cartAmountService.getCartAmountByItemAndCartId(item.getId(), cartId).getAmount(),
                cartAmountService.getCartAmountByItemAndCartId(item.getId(), cartId).getAmount() * item.getPrice()
        );
        return dto;
    }

    public Cart getCartByItemAndCartId(Long itemId, String cartId) throws CartNotFoundException {
        return cartAmountService.getCartAmountByItemAndCartId(itemId, cartId).getCart();
    }

    public String getSessionId (HttpServletRequest request) throws CartNotFoundException {
      Cookie cookie = Arrays.stream(request.getCookies()).filter(n -> n.getName().equals("cart_id")).findFirst()
          .orElseThrow( () -> new CartNotFoundException("No cart id."));
    return cookie.getValue();
    }
}
