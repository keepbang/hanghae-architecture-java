package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.LectureHistory;
import org.springframework.stereotype.Repository;

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
@Repository
public class LectureHistoryRepositoryImpl implements LectureHistoryRepository {

  @Override
  public long countApplyByLectureId(long lectureId) {
    return 0;
  }

  @Override
  public boolean isAlreadyApplyByUserIdAndLectureId(long userId, long lectureId) {
    return false;
  }

  @Override
  public LectureHistory save(long userId, long lectureId) {
    return null;
  }
}
