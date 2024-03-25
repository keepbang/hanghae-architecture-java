package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.Lecture;
import java.util.Optional;

/**
 * create on 2024/03/25.
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubLectureManager implements LectureRepository {

  private Lecture lecture;

  @Override
  public Optional<Lecture> findById(long lectureId) {
    return Optional.of(this.lecture);
  }

  @Override
  public Lecture save(String name, long maxUser, long startApplyMillis, long startLectureMillis) {
    this.lecture = new Lecture(1L, name, maxUser, startApplyMillis, startLectureMillis);
    return this.lecture;
  }
}
