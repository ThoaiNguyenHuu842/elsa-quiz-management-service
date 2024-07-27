package com.thoainguyen.repository;

import com.thoainguyen.domain.QuizAnswers;
import java.math.BigInteger;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface QuizAnswersRepository extends ReactiveMongoRepository<QuizAnswers, BigInteger> {

  @ExistsQuery("{'quizId':?0, 'connectionId':?1}")
  Mono<Boolean> existByQuizIdAndConnectionId(BigInteger quizId, String connectionId);
}
