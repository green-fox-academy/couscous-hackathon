package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.exception.CartNotFoundException;
import com.greenfoxacademy.webshop.exception.ItemNotFoundException;
import com.greenfoxacademy.webshop.model.Item;
import com.greenfoxacademy.webshop.model.ItemDescriptionDTO;
import com.greenfoxacademy.webshop.model.ItemResponseDTO;
import com.greenfoxacademy.webshop.model.filterAPI.IItemDAO;
import com.greenfoxacademy.webshop.model.filterAPI.SearchCriteria;
import com.greenfoxacademy.webshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private CartAmountService cartAmountService;

  @Autowired
  private IItemDAO filterApi;

  public List<ItemResponseDTO> getItems(String search, Integer page, Integer pageSize) {
    List<SearchCriteria> params = new ArrayList<>();
    Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
    Matcher matcher = pattern.matcher(search + ",");
    while (matcher.find()) {
      params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
    }
    List<Item> items = filterApi.searchItem(params, page, pageSize);

    List<ItemResponseDTO> itemResponseDTOS = itemsToResponseDTOList(items);

    return itemResponseDTOS;
  }

  public List<ItemResponseDTO> itemsToResponseDTOList(List<Item> items) {
    List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();
    for (Item item : items
    ) {
      ItemResponseDTO itemResponseDTO =
          new ItemResponseDTO(item.getId(), item.getTitle(), item.getPrice(), item.getImages().get(0).getUrl(),
              item.getCategory());
      itemResponseDTOS.add(itemResponseDTO);
    }
    return itemResponseDTOS;
  }

  public Item getItemById(Long id) throws ItemNotFoundException {
    Optional<Item> optionalItem = itemRepository.findById(id);
    return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item is not found with this id."));
  }

  public ItemDescriptionDTO itemToDescriptionDTO(Item item, String cartId)
      throws ItemNotFoundException, CartNotFoundException {
    ItemDescriptionDTO dto = new ItemDescriptionDTO(
        item.getId(),
        item.getTitle(),
        item.getPrice(),
        item.getDescription(),
        item.getImages().stream().map(i -> i.getUrl()).collect(Collectors.toList()),
        cartAmountService.getCartAmountByItemAndCartId(item.getId(), cartId).getAmount(),
        item.getCategory());
    return dto;
  }
}
