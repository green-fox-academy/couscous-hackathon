package com.greenfoxacademy.webshop.model.filterAPI;

import com.greenfoxacademy.webshop.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ItemDAO implements IItemDAO {

  @PersistenceContext
  private EntityManager entityManager;


  @Override
  public List<Item> searchItem(List<SearchCriteria> params,Integer page, Integer pageSize) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Item> query = builder.createQuery(Item.class);
    Root r = query.from(Item.class);

    Predicate predicate = builder.conjunction();

    ItemSearchQueryCriteriaConsumer searchConsumer =
        new ItemSearchQueryCriteriaConsumer(predicate, builder, r);
    params.stream().forEach(searchConsumer);
    predicate = searchConsumer.getPredicate();
    query.where(predicate);

    TypedQuery<Item> typedQuery = entityManager.createQuery(query);
    typedQuery.setFirstResult(page*pageSize);
    typedQuery.setMaxResults(pageSize);

    //List<Item> result = entityManager.createQuery(query).getResultList();
    List<Item> result = typedQuery.getResultList();

    return result;
  }

  @Override
  public void save(Item entity) {
    entityManager.persist(entity);
  }
}
