package com.thoainguyen.rest;

import com.thoainguyen.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("quiz-mng-service/quiz/")
@AllArgsConstructor
public class QuizEndpoint {

  private final QuizService quizService;

  @GetMapping(value = "{quizId}/exists")
  public Mono<Boolean> existsByQuizId(@PathVariable String quizId) {
    return quizService.existsByQuizId(quizId);
  }

  @GetMapping(value = "{quizId}")
  public Mono<ResponseEntity> findByQuizId(@PathVariable String quizId) {
    return quizService.findByQuizId(quizId)
      .map(quiz -> ResponseEntity.status(HttpStatus.OK).body(quiz))
      .cast(ResponseEntity.class)
      .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found"));
  }
}
