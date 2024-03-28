package com.hhplus.architecture.service;

import com.hhplus.architecture.domain.LectureHistory;
import com.hhplus.architecture.dto.LectureHistoryDto;
import com.hhplus.architecture.repository.LectureHistoryJpaRepository;
import org.springframework.stereotype.Component;

/**
 * create on 2024/03/25. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
@Component
public class LectureHistoryManagerImpl implements LectureHistoryManager {

  private final LectureHistoryJpaRepository lectureHistoryJpaRepository;

  public LectureHistoryManagerImpl(final LectureHistoryJpaRepository lectureHistoryJpaRepository) {
    this.lectureHistoryJpaRepository = lectureHistoryJpaRepository;
  }

  @Override
  public boolean isAlreadyApplyByUserIdAndLectureId(long userId, long lectureId) {
    return lectureHistoryJpaRepository.existsByUserIdAndLectureId(userId, lectureId);
  }

  @Override
  public LectureHistoryDto save(long userId, long lectureId) {
    LectureHistory lectureHistory = new LectureHistory(userId, lectureId,
        System.currentTimeMillis());

    LectureHistory savedHistory = lectureHistoryJpaRepository.save(lectureHistory);
    return new LectureHistoryDto(
        savedHistory.getUserId(),
        savedHistory.getLectureId(),
        savedHistory.getApplyMillis()
    );
  }

}
