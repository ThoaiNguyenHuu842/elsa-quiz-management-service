package com.thoainguyen.domain;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "quiz_answers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizAnswers {
  @Id
  private BigInteger id;
  @Field(value = "connection_id")
  private String connectionId;
  @Field(value = "user_name")
  private String userName;
  @Field(value = "quiz_id")
  private BigInteger quizId;
  @Field("submitted_time")
  private Date submittedTime;
  @Field("score")
  private Integer score;
  @Field("formatted_score")
  private String formattedScore;
  @Field("answers")
  private List<Answer> answers;
}
