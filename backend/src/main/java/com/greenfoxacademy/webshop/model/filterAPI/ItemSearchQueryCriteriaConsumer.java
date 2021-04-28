package com.greenfoxacademy.webshop.model.filterAPI;

import com.greenfoxacademy.webshop.model.Category;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

@AllArgsConstructor

public class ItemSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {
  private Predicate predicate;
  private CriteriaBuilder builder;
  private Root r;

  @Override
  public void accept(SearchCriteria param) throws IllegalArgumentException{
    if (param.getOperation().equalsIgnoreCase(">")) {
      predicate = builder.and(predicate, builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
    }
    else if (param.getOperation().equalsIgnoreCase("<")) {
      predicate = builder.and(predicate, builder.lessThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
    }
    else if (param.getOperation().equalsIgnoreCase(":") && param.getKey().equals("category")) {
      predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), Integer.parseInt(param.getValue().toString())));
    }
    else if (param.getOperation().equalsIgnoreCase(":")) {
      if (r.get(param.getKey()).getJavaType() == String.class) {
        predicate = builder.and(predicate, builder.like(r.get(param.getKey()), "%" + param.getValue() + "%"));
      }
      else {
        predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), param.getValue()));
      }
    }
  }

  public Predicate getPredicate() {
    return predicate;
  }
}
