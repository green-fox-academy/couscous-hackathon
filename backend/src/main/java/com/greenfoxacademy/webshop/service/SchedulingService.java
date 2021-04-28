package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.exception.CustomExceptionHandler;
import com.greenfoxacademy.webshop.model.Cart;
import com.greenfoxacademy.webshop.repository.CartRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class SchedulingService {

  private CartRepository cartRepository;

  private static final Logger logger = Logger.getLogger(CustomExceptionHandler.class);

  @Autowired
  public SchedulingService(CartRepository cartRepository) {
    this.cartRepository = cartRepository;
  }

  @Scheduled(cron = "0 0 2 * * *")
  public void cartCleanup() {
    long dayEarlierInMillis = System.currentTimeMillis() - (24 * 3_600_000);

    List<Cart> oldCartList = ((List<Cart>) cartRepository.findAll()).stream()
        .filter(cart -> cart.getCreatedAt().getTime() < dayEarlierInMillis)
        .collect(Collectors.toList());

    for (Cart cart : oldCartList) {
      logger.info("Deleted cart " + cart.getId());
      cartRepository.delete(cart);
    }
  }
}
