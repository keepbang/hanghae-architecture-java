package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.LectureHistory;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

/**
 * create on 2024/03/25.
 * create by IntelliJ IDEA.
 *
 * <p> 강의 기록 기능 DAO </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface LectureHistoryRepository extends JpaRepository<LectureHistory, Long> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  long countAllByLectureId(long lectureId);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  boolean existsByUserIdAndLectureId(long userId, long lectureId);

}
