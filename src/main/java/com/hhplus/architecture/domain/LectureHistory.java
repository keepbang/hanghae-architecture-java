package com.hhplus.architecture.domain;

import java.util.Objects;

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
public record LectureHistory(
    long id,
    long userId,
    long lectureId,
    long applyMillis
) {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LectureHistory that = (LectureHistory) o;
    return Objects.equals(id, that.id)
        && Objects.equals(userId, that.userId)
        && Objects.equals(lectureId, that.lectureId)
        && Objects.equals(applyMillis, that.applyMillis);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, lectureId, applyMillis);
  }
}
