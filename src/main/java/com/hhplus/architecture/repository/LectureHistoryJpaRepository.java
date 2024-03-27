package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.LectureHistory;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface LectureHistoryJpaRepository extends JpaRepository<LectureHistory, Long> {

  long countAllByLectureId(long lectureId);

  boolean existsByUserIdAndLectureId(long userId, long lectureId);

}
