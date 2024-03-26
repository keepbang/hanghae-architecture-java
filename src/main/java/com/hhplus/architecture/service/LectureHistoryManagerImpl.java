package com.hhplus.architecture.service;

import com.hhplus.architecture.domain.LectureHistory;
import com.hhplus.architecture.dto.LectureHistoryDto;
import com.hhplus.architecture.repository.LectureHistoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly = true)
public class LectureHistoryManagerImpl implements LectureHistoryManager {

  private final LectureHistoryRepository lectureHistoryRepository;

  public LectureHistoryManagerImpl(final LectureHistoryRepository lectureHistoryRepository) {
    this.lectureHistoryRepository = lectureHistoryRepository;
  }

  @Override
  public long countApplyByLectureId(long lectureId) {
    return lectureHistoryRepository.countAllByLectureId(lectureId);
  }

  @Override
  public boolean isAlreadyApplyByUserIdAndLectureId(long userId, long lectureId) {
    return lectureHistoryRepository.existsByUserIdAndLectureId(userId, lectureId);
  }

  @Override
  @Transactional
  public LectureHistoryDto save(long userId, long lectureId) {
    LectureHistory lectureHistory = new LectureHistory(userId, lectureId,
        System.currentTimeMillis());

    LectureHistory savedHistory = lectureHistoryRepository.save(lectureHistory);
    return new LectureHistoryDto(
        savedHistory.getUserId(),
        savedHistory.getLectureId(),
        savedHistory.getApplyMillis()
    );
  }

}
