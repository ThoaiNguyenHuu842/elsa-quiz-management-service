package com.thoainguyen.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizAnswersDto {
  private String connectionId;
  private String quizId;
  private String userName;
  private List<AnswerDto> answers;
}
