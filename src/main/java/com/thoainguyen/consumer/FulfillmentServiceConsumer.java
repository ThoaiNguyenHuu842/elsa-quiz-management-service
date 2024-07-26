package com.thoainguyen.consumer;

import com.thoainguyen.rest.Order;
import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FulfillmentServiceConsumer {

  @Bean
  public Consumer<Order> consumeOrderProcessed() {
    return order -> {
      System.out.println("Processing shipment for order: " + order);
    };
  }

}
