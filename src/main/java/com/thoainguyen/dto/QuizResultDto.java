package com.thoainguyen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizResultDto {
  private String connectionId;
  private String quizId;
  private String userName;
  private Integer score;
  private String formattedScore;
  private String error;
}
