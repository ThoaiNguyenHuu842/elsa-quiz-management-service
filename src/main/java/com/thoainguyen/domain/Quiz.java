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

@Document(collection = "quiz")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {
  @Id
  private BigInteger id;
  @Field(value = "quiz_id")
  private String quizId;
  @Field("start_time")
  private Date startTime;
  @Field("end_time")
  private Date endTime;
  @Field("questions")
  private List<Question> questions;
}
