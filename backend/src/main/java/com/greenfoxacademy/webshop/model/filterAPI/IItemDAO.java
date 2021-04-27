package com.greenfoxacademy.webshop.model.filterAPI;

import com.greenfoxacademy.webshop.model.Item;

import java.util.List;

public interface IItemDAO {
  List<Item> searchItem(List<SearchCriteria> params,Integer page, Integer pageSize);

  void save(Item entity);
}
