package com.thoainguyen.repository;

import com.thoainguyen.IntegrationTestConfig;
import com.thoainguyen.domain.QuizAnswers;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class QuizAnswersRepositoryTest extends IntegrationTestConfig {
  @Autowired
  private QuizAnswersRepository quizAnswersRepository;

  @Test
  void existByQuizIdAndConnectionIdIsFalse_when_quizHasNotBeenSubmitted() {
    Mono<Boolean> result = quizAnswersRepository.existByQuizIdAndConnectionId(BigInteger.ONE, "9b4d465e-68b7-4b3a-8f13-7f12c00b076d");
    StepVerifier.create(result).expectNext(false).expectComplete().verify();
  }

  @Test
  void existByQuizIdAndConnectionIdIsTrue_when_quizHasBeenSubmitted() {
    QuizAnswers quizAnswers = QuizAnswers.builder().quizId(BigInteger.ONE).connectionId("9b4d465e-68b7-4b3a-8f13-7f12c00b0763").build();
    Mono<Boolean> result = quizAnswersRepository.save(quizAnswers)
      .flatMap(q -> quizAnswersRepository.existByQuizIdAndConnectionId(BigInteger.ONE, "9b4d465e-68b7-4b3a-8f13-7f12c00b0763"));
    StepVerifier.create(result).expectNext(true).expectComplete().verify();
  }
}
