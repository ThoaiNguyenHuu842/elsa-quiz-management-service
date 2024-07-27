package com.thoainguyen.producer;

import com.thoainguyen.dto.QuizResultDto;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class Producer {
  private final StreamBridge streamBridge;

  public Mono<Boolean> produceQuizResult(QuizResultDto quizResultDto) {
    Message<QuizResultDto> message = MessageBuilder.withPayload(quizResultDto).build();
    return Mono.just(streamBridge.send("produceQuizResult-out-0",message));
  }
}
