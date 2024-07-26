package com.thoainguyen.repository;

import com.thoainguyen.domain.Quiz;
import java.math.BigInteger;
import java.util.Date;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface QuizRepository extends ReactiveMongoRepository<Quiz, BigInteger> {
  @ExistsQuery("{'quizId':?0, 'startTime':{$lte:?1}, 'endTime':{$gte:?1}}")
  Mono<Boolean> existByQuizId(String quizId, Date now);

  @Query("{'quizId':?0, 'startTime':{$lte:?1}, 'endTime':{$gte:?1}}")
  Mono<Quiz> findByQuizId(String quizId, Date now);
}
