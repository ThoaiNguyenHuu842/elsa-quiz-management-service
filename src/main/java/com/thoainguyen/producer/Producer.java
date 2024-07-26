package com.thoainguyen.producer;

import com.thoainguyen.rest.Order;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Producer {
  private final StreamBridge streamBridge;

  public void produceOrderCreated(Order order) {
    Message<Order> message = MessageBuilder.withPayload(order).setHeader(
      KafkaHeaders.MESSAGE_KEY, String.valueOf(order.getCustomerId())).build();
    streamBridge.send("produceOrderCreated-out-0",message);
  }

  public void produceOrderProcessed(Order order) {
    Message<Order> message = MessageBuilder.withPayload(order).setHeader(
      KafkaHeaders.MESSAGE_KEY, String.valueOf(order.getCustomerId())).build();
    streamBridge.send("produceOrderProcessed-out-0",message);
  }
}
