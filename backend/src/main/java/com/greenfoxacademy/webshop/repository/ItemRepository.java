package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
  List<Item> findAll();
}
