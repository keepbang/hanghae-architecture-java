package com.hhplus.architecture.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * create on 3/28/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
public class ApplyCounter {

  @Id
  @Column(name = "lecture_id")
  private Long lectureId;

  @Column(name = "apply_count")
  private Long applyCount;

  @Column(name = "max_user")
  private Long maxUser;

  protected ApplyCounter() {

  }

  public ApplyCounter(Long lectureId, Long maxUser) {
    this.lectureId = lectureId;
    this.applyCount = 0L;
    this.maxUser = maxUser;
  }

  public ApplyCounter(Long lectureId, Long applyCount, Long maxUser) {
    this.lectureId = lectureId;
    this.applyCount = applyCount;
    this.maxUser = maxUser;
  }
}
