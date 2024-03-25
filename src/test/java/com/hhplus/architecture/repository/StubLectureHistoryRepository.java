package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.LectureHistory;
import java.util.ArrayList;
import java.util.List;
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
public class StubLectureHistoryRepository implements LectureHistoryRepository {

  // 하나의 강의에 대해서만 검증 진행
  private final List<LectureHistory> lectureHistories = new ArrayList<>();

  long cursor = 1;

  @Override
  public long countApplyByLectureId(long lectureId) {
    return lectureHistories.size();
  }

  @Override
  public boolean isAlreadyApplyByUserIdAndLectureId(long userId, long lectureId) {
    return lectureHistories.stream()
        .anyMatch(history -> Objects.equals(history.userId(), userId)
            && Objects.equals(history.lectureId(), lectureId));
  }

  @Override
  public LectureHistory save(long userId, long lectureId) {
    LectureHistory apply = new LectureHistory(cursor++, userId, lectureId,
        System.currentTimeMillis());
    lectureHistories.add(apply);
    return apply;
  }
}