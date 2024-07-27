package com.thoainguyen.service;

import com.thoainguyen.domain.Answer;
import com.thoainguyen.domain.Question;
import com.thoainguyen.domain.Quiz;
import com.thoainguyen.domain.QuizAnswers;
import com.thoainguyen.dto.AnswerDto;
import com.thoainguyen.dto.QuizAnswersDto;
import com.thoainguyen.dto.QuizResultDto;
import com.thoainguyen.repository.QuizAnswersRepository;
import com.thoainguyen.repository.QuizRepository;
import io.netty.util.internal.StringUtil;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class QuizService {

  private final QuizRepository quizRepository;
  private final QuizAnswersRepository quizAnswersRepository;
  private final static String FORMATTED_SCORE = "%s/%s";

  public Mono<Boolean> existsByQuizId(String quizId) {
    Date now = new Date();
    return quizRepository.existsByQuizId(quizId, now);
  }

  public Mono<Quiz> findByQuizId(String quizId) {
    Date now = new Date();
    return quizRepository.findByQuizId(quizId, now);
  }

  public Mono<QuizResultDto> processQuizAnswers(QuizAnswersDto dto) {
    log.info("Processing quiz answers {}", dto);
    QuizResultDto result = buildQuizResultDto(dto);
    try {
      validateDto(dto);
    }
    catch (Exception ex) {
      log.error("Processed quiz answers {} with error {}", dto, ex);
      result.setError(ex.getMessage());
      return Mono.just(result);
    }

    Mono<Quiz> quizMono = quizRepository.findOneByQuizId(dto.getQuizId());
    return quizMono.flatMap(quiz -> {
      Mono<Boolean> alreadySubmittedMono = quizAnswersRepository.existByQuizIdAndConnectionId(quiz.getId(), dto.getConnectionId());
      return alreadySubmittedMono.flatMap(alreadySubmitted -> {
        if (alreadySubmitted) {
          log.error("Processed quiz answers {} with error: User already submitted answers for this quiz", dto);
          result.setError("User already submitted answers for this quiz");
          return Mono.just(result);
        } else {
          try {
            List<AnswerDto> answersDto = dto.getAnswers();
            List<Question> questions = quiz.getQuestions();
            Map<BigInteger, AnswerDto> mapQuestionIdAndAnswer = answersDto
              .stream()
              .collect(Collectors.toMap(AnswerDto::getQuestionId, item -> item));

            validateAnswersDto(mapQuestionIdAndAnswer, questions);

            int numberOfCorrectAnswers = 0;
            for (Question question : questions) {
              AnswerDto answer = mapQuestionIdAndAnswer.get(question.getQuestionId());
              if (answer.getAnswer() == question.getCorrectAnswer()) {
                numberOfCorrectAnswers++;
              }
            }

            QuizAnswers quizAnswers = buildQuizAnswers(dto, quiz, numberOfCorrectAnswers);
            return quizAnswersRepository.save(quizAnswers).flatMap(q -> {
              result.setScore(q.getScore());
              result.setFormattedScore(q.getFormattedScore());
              log.error("Processed quiz answers {} success with result {}", dto, result);
              return Mono.just(result);
            });
          } catch (Exception ex) {
            log.error("Processed quiz answers {} with error {}", dto, ex);
            result.setError(ex.getMessage());
            return Mono.just(result);
          }
        }
      });
    }).switchIfEmpty(
      Mono.defer(() -> {
        log.error("Processed quiz answers {} with error: quiz not found", dto);
        result.setError("Quiz not found");
        return Mono.just(result);
      })
    );
  }

  private void validateDto(QuizAnswersDto dto) {
    if (StringUtil.isNullOrEmpty(dto.getConnectionId())) {
      throw new IllegalArgumentException("Connection id is required");
    } else if (StringUtil.isNullOrEmpty(dto.getQuizId())) {
      throw new IllegalArgumentException("Quiz id is required");
    } else if (StringUtil.isNullOrEmpty(dto.getUserName())) {
      throw new IllegalArgumentException("User name is required");
    } else if (CollectionUtils.isEmpty(dto.getAnswers())) {
      throw new IllegalArgumentException("Answers id is required");
    }
  }

  private void validateAnswersDto(Map<BigInteger, AnswerDto> mapQuestionIdAndAnswer, List<Question> questions) {
    if (questions.size() != mapQuestionIdAndAnswer.size()) {
      throw new IllegalArgumentException("Require all questions to be answered");
    }

    Set<BigInteger> questionIdsInQuiz = questions
      .stream()
      .map(Question::getQuestionId)
      .collect(Collectors.toSet());

    if (!questionIdsInQuiz.containsAll(mapQuestionIdAndAnswer.keySet())) {
      questionIdsInQuiz.removeAll(mapQuestionIdAndAnswer.keySet());
      throw new IllegalArgumentException("These questions have not answered " + String
        .join(",", questionIdsInQuiz.stream().map(String::valueOf).collect(
          Collectors.toList())));
    }
  }

  private QuizAnswers buildQuizAnswers(QuizAnswersDto dto, Quiz quiz, int numberOfCorrectAnswers) {
    QuizAnswers quizAnswers = new QuizAnswers();
    List<Answer> answers = dto.getAnswers().stream().map(a -> Answer.builder().answer(a.getAnswer()).questionId(a.getQuestionId()).build()).collect(Collectors.toList());
    quizAnswers.setAnswers(answers);
    quizAnswers.setQuizId(quiz.getId());
    quizAnswers.setConnectionId(dto.getConnectionId());
    quizAnswers.setSubmittedTime(new Date());
    quizAnswers.setScore(numberOfCorrectAnswers);
    quizAnswers.setFormattedScore(String.format(FORMATTED_SCORE,numberOfCorrectAnswers,quiz.getQuestions().size()));
    return quizAnswers;
  }

  private QuizResultDto buildQuizResultDto(QuizAnswersDto dto) {
    return QuizResultDto
      .builder()
      .connectionId(dto.getConnectionId())
      .quizId(dto.getQuizId())
      .userName(dto.getUserName())
      .build();
  }
}
