package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.model.Item;
import com.greenfoxacademy.webshop.model.ItemResponseDTO;
import com.greenfoxacademy.webshop.model.filterAPI.IItemDAO;
import com.greenfoxacademy.webshop.model.filterAPI.SearchCriteria;
import com.greenfoxacademy.webshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private IItemDAO filterApi;

  public List<ItemResponseDTO> getItems (String search, Integer page, Integer pageSize ) {
    List<SearchCriteria> params = new ArrayList<>();
    Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
    Matcher matcher = pattern.matcher(search + ",");
    while (matcher.find()) {
      params.add(new SearchCriteria(matcher.group(1), matcher.group(2),matcher.group(3) ));
    }
    List<Item> items = filterApi.searchItem(params,page, pageSize);

    List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();
    for (Item item: items
    ) {
      ItemResponseDTO itemResponseDTO = new ItemResponseDTO(item.getId(),item.getTitle(),item.getPrice(),item.getImages().get(0).getUrl());
      itemResponseDTOS.add(itemResponseDTO);
    }

    return itemResponseDTOS;
  }
}
