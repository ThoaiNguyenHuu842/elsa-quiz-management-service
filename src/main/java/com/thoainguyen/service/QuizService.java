package com.thoainguyen.service;

import com.thoainguyen.domain.Quiz;
import com.thoainguyen.repository.QuizRepository;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class QuizService {

  private final QuizRepository quizRepository;

  public Mono<Boolean> existsByQuizId(String quizId) {
    Date now = new Date();
    return quizRepository.existByQuizId(quizId,now);
  }

  public Mono<Quiz> findByQuizId(String quizId) {
    Date now = new Date();
    return quizRepository.findByQuizId(quizId,now);
  }
}
