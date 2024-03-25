package com.hhplus.architecture.service;

import com.hhplus.architecture.repository.LectureHistoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
@Component
public class LectureHistoryManager {

  private final LectureHistoryRepository lectureHistoryRepository;

  public LectureHistoryManager(final LectureHistoryRepository lectureHistoryRepository) {
    this.lectureHistoryRepository = lectureHistoryRepository;
  }

  @Transactional(readOnly = true)
  public long countApplyByLectureId(long lectureId) {
    return lectureHistoryRepository.countApplyByLectureId(lectureId);
  }

}
