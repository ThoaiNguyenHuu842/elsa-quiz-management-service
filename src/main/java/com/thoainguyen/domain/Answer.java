package com.thoainguyen.domain;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
  @Field(value = "question_id")
  private BigInteger questionId;
  @Field(value = "answer")
  private Integer answer;
}
