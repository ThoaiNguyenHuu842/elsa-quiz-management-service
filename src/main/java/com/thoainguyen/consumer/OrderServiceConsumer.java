package com.thoainguyen.consumer;

import com.thoainguyen.producer.Producer;
import com.thoainguyen.rest.Order;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceConsumer {

  @Autowired
  private Producer producer;

  @Bean
  public Consumer<Order> consumeOrderCreated() {
    return order -> {
      System.out.println("Processing order: " + order);
      producer.produceOrderProcessed(order);
    };
  }
}
