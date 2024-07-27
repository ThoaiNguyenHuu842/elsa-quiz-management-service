package com.thoainguyen.service;

import com.thoainguyen.IntegrationTestConfig;
import com.thoainguyen.domain.Question;
import com.thoainguyen.domain.Quiz;
import com.thoainguyen.dto.AnswerDto;
import com.thoainguyen.dto.QuizAnswersDto;
import com.thoainguyen.dto.QuizResultDto;
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

public class QuizServiceIntegrationTest extends IntegrationTestConfig {
  @Autowired
  private QuizService quizService;
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
    Mono<Boolean> result = quizService.existsByQuizId(quizId1);
    StepVerifier.create(result).expectNext(true).expectComplete().verify();
  }

  @Test
  void existsByQuizIdIsFalse_when_quizIdIsExistsButHaveNotStarted() {
    Mono<Boolean> result = quizService.existsByQuizId(quizId2);
    StepVerifier.create(result).expectNext(false).expectComplete().verify();
  }

  @Test
  void findByQuizIdReturnQuizData_when_quizIdIsExistsAndIsOpening() {
    Mono<Quiz> result = quizService.findByQuizId(quizId1);
    StepVerifier.create(result).expectNext(quiz1).expectComplete().verify();
  }

  @Test
  void findByQuizIdReturnEmpty_when_quizIdIsExistsButHaveNotStarted() {
    Mono<Quiz> result = quizService.findByQuizId(quizId2);
    StepVerifier.create(result).expectComplete().verify();
  }

  @Test
  void processQuizAnswersSuccess() {
    QuizAnswersDto dto = new QuizAnswersDto();
    dto.setConnectionId("5b4d465e-68b7-4b3a-8f13-7f12c00b076a");
    dto.setQuizId(quizId1);
    dto.setUserName("Chris");

    AnswerDto answerDto1 = AnswerDto.builder().questionId(new BigInteger("1")).answer(1).build();
    AnswerDto answerDto2 = AnswerDto.builder().questionId(new BigInteger("2")).answer(1).build();
    AnswerDto answerDto3 = AnswerDto.builder().questionId(new BigInteger("3")).answer(1).build();
    dto.setAnswers(List.of(answerDto1, answerDto2, answerDto3));

    Mono<QuizResultDto> result = quizService.processQuizAnswers(dto);

    QuizResultDto expectedResult = new QuizResultDto();
    expectedResult.setConnectionId("5b4d465e-68b7-4b3a-8f13-7f12c00b076a");
    expectedResult.setQuizId(quizId1);
    expectedResult.setUserName("Chris");
    expectedResult.setScore(1);
    expectedResult.setFormattedScore("1/3");
    StepVerifier.create(result).expectNext(expectedResult).expectComplete().verify();
  }

  @Test
  void processQuizAnswersError_when_QuizNotFound() {
    QuizAnswersDto dto = new QuizAnswersDto();
    dto.setConnectionId("5b4d465e-68b7-4b3a-8f13-7f12c00b076a");
    dto.setQuizId("AJHSH");
    dto.setUserName("Chris");

    AnswerDto answerDto1 = AnswerDto.builder().questionId(new BigInteger("1")).answer(1).build();
    AnswerDto answerDto2 = AnswerDto.builder().questionId(new BigInteger("2")).answer(1).build();
    AnswerDto answerDto3 = AnswerDto.builder().questionId(new BigInteger("3")).answer(1).build();
    dto.setAnswers(List.of(answerDto1, answerDto2, answerDto3));

    Mono<QuizResultDto> result = quizService.processQuizAnswers(dto);

    QuizResultDto expectedResult = new QuizResultDto();
    expectedResult.setConnectionId("5b4d465e-68b7-4b3a-8f13-7f12c00b076a");
    expectedResult.setQuizId("AJHSH");
    expectedResult.setUserName("Chris");
    expectedResult.setError("Quiz not found");
    StepVerifier.create(result).expectNext(expectedResult).expectComplete().verify();
  }

  @Test
  void processQuizAnswersError_when_aQuestionHasNotAnswered() {
    QuizAnswersDto dto = new QuizAnswersDto();
    dto.setConnectionId("5b4d465e-68b7-4b3a-8f13-7f12c00b076b");
    dto.setQuizId(quizId1);
    dto.setUserName("Chris A");

    AnswerDto answerDto1 = AnswerDto.builder().questionId(new BigInteger("1")).answer(1).build();
    AnswerDto answerDto2 = AnswerDto.builder().questionId(new BigInteger("2")).answer(1).build();
    dto.setAnswers(List.of(answerDto1, answerDto2));

    Mono<QuizResultDto> result = quizService.processQuizAnswers(dto);

    QuizResultDto expectedResult = new QuizResultDto();
    expectedResult.setConnectionId("5b4d465e-68b7-4b3a-8f13-7f12c00b076b");
    expectedResult.setQuizId(quizId1);
    expectedResult.setUserName("Chris A");
    expectedResult.setError("Require all questions to be answered");
    StepVerifier.create(result).expectNext(expectedResult).expectComplete().verify();
  }

  @Test
  void processQuizAnswersError_when_userAlreadySubmitted() {
    QuizAnswersDto dto = new QuizAnswersDto();
    dto.setConnectionId("5b4d465e-68b7-4b3a-8f13-7f12c00b076d");
    dto.setQuizId(quizId1);
    dto.setUserName("Chris");

    AnswerDto answerDto1 = AnswerDto.builder().questionId(new BigInteger("1")).answer(1).build();
    AnswerDto answerDto2 = AnswerDto.builder().questionId(new BigInteger("2")).answer(1).build();
    AnswerDto answerDto3 = AnswerDto.builder().questionId(new BigInteger("3")).answer(1).build();
    dto.setAnswers(List.of(answerDto1, answerDto2, answerDto3));

    Mono<QuizResultDto> result = quizService.processQuizAnswers(dto).flatMap(q -> quizService.processQuizAnswers(dto));

    QuizResultDto expectedResult = new QuizResultDto();
    expectedResult.setConnectionId("5b4d465e-68b7-4b3a-8f13-7f12c00b076d");
    expectedResult.setQuizId(quizId1);
    expectedResult.setUserName("Chris");
    expectedResult.setError("User already submitted answers for this quiz");
    StepVerifier.create(result).expectNext(expectedResult).expectComplete().verify();
  }
}
