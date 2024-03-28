package com.hhplus.architecture.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(
        name = "lecture_history_uk",
        columnNames = {"user_id", "lecture_id"}
    )
})
@Getter
public class LectureHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "lecture_id")
  private Long lectureId;

  @Column(name = "apply_millis")
  private Long applyMillis;

  protected LectureHistory() {
  }

  public LectureHistory(Long userId, Long lectureId, Long applyMillis) {
    this.userId = userId;
    this.lectureId = lectureId;
    this.applyMillis = applyMillis;
  }
}
