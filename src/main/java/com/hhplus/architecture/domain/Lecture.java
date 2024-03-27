package com.hhplus.architecture.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Getter
public class Lecture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 특강 이름.
  @Column(name = "name")
  private String name;

  // 최대 수강생 수.
  @Column(name = "max_user")
  private Long maxUser;

  // 신청 시작 시간.
  @Column(name = "start_apply_millis")
  private Long startApplyMillis;

  // 특강 시작 시간.
  @Column(name = "start_lecture_millis")
  private Long startLectureMillis;

  protected Lecture() {

  }

  public Lecture(String name, Long maxUser, Long startApplyMillis, Long startLectureMillis) {
    this.name = name;
    this.maxUser = maxUser;
    this.startApplyMillis = startApplyMillis;
    this.startLectureMillis = startLectureMillis;
  }
}
