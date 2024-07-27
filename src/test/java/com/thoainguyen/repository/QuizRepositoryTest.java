package com.thoainguyen.repository;

import com.thoainguyen.IntegrationTestConfig;
import com.thoainguyen.domain.Question;
import com.thoainguyen.domain.Quiz;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class QuizRepositoryTest extends IntegrationTestConfig {
  @Autowired
  private QuizRepository quizRepository;

  private Quiz quiz1;
  private String quizId1 = "1a2b3c4d";
  private String quizId2 = "3c4d5e6f";

  @BeforeEach
  @Transactional
  public void before() {
    Question question1 = new Question();
    question1.setQuestionId(new BigInteger("1"));
    question1.setQuestion("What is the capital of France?");
    question1.setAnswers(List.of("Berlin","Madrid","Paris","Rome"));
    question1.setCorrectAnswer(2);

    Question question2 = new Question();
    question2.setQuestionId(new BigInteger("2"));
    question2.setQuestion("Which planet is known as the Red Planet?");
    question2.setAnswers(List.of("Earth","Mars","Jupiter","Saturn"));
    question2.setCorrectAnswer(1);

    Question question3 = new Question();
    question3.setQuestionId(new BigInteger("3"));
    question3.setQuestion("Who wrote 'Romeo and Juliet'?");
    question3.setAnswers(List.of("William Shakespeare", "Charles Dickens", "Mark Twain", "Jane Austen"));
    question3.setCorrectAnswer(0);

    quiz1 = new Quiz();
    quiz1.setId(new BigInteger("1"));
    quiz1.setQuizId(quizId1);
    quiz1.setStartTime(Date.from(Instant.parse("2024-06-30T14:28:17.585Z")));
    quiz1.setEndTime(Date.from(Instant.parse("2100-08-30T14:28:17.585Z")));

    quiz1.setQuestions(List.of(question1, question2, question3));
  }

  @Test
  void existsByQuizIdIsTrue_when_quizIdIsExistsAndIsOpening() {
    Mono<Boolean> result = quizRepository.existsByQuizId(quizId1, new Date());
    StepVerifier.create(result).expectNext(true).expectComplete().verify();
  }

  @Test
  void existsByQuizIdIsFalse_when_quizIdIsExistsButHaveNotStarted() {
    Mono<Boolean> result = quizRepository.existsByQuizId(quizId2, new Date());
    StepVerifier.create(result).expectNext(false).expectComplete().verify();
  }

  @Test
  void findByQuizIdReturnQuizData_when_quizIdIsExistsAndIsOpening() {
    Mono<Quiz> result = quizRepository.findByQuizId(quizId1, new Date());
    StepVerifier.create(result).expectNext(quiz1).expectComplete().verify();
  }

  @Test
  void findByQuizIdReturnEmpty_when_quizIdIsExistsButHaveNotStarted() {
    Mono<Quiz> result = quizRepository.findByQuizId(quizId2, new Date());
    StepVerifier.create(result).expectComplete().verify();
  }
}
