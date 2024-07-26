package com.thoainguyen.domain;

import java.math.BigInteger;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
  @Field(value = "question_id")
  private BigInteger questionId;
  @Field(value = "question")
  private String question;
  @Field(value = "answers")
  private List<String> answers;
  @Field(value = "correctAnswer")
  private int correctAnswer;
}
