package com.hhplus.architecture.service;

import com.hhplus.architecture.repository.LectureRepository;
import org.springframework.stereotype.Component;

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
public class LectureManager {

  private final LectureRepository lectureRepository;

  public LectureManager(final LectureRepository lectureRepository) {
    this.lectureRepository = lectureRepository;
  }

  public void save(String name, long maxUser, long startApplyMillis, long startLectureMillis) {
    lectureRepository.save(name, maxUser, startApplyMillis, startLectureMillis);
  }
}
