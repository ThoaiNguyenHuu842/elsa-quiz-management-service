package com.thoainguyen.consumer;

import com.thoainguyen.dto.QuizAnswersDto;
import com.thoainguyen.producer.Producer;
import com.thoainguyen.service.QuizService;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class QuizAnswersConsumer {

  @Autowired
  private Producer producer;

  @Autowired
  private QuizService quizService;

  @Bean
  public Consumer<Flux<QuizAnswersDto>> consumeQuizAnswers() {
    return dto -> {
      dto.subscribe(quizAnswersDto -> {
        quizService.processQuizAnswers(quizAnswersDto).subscribe(result -> producer.produceQuizResult(result));
      });
    };
  }
}
