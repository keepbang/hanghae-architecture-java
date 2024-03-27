package com.hhplus.architecture.service;

import com.hhplus.architecture.dto.LectureHistoryDto;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 특강 신청 이력 관련 Interface </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface LectureHistoryManager {

  /**
   * 특강 신청(저장).
   *
   * @param userId    사용자 아이디.
   * @param lectureId 특강 아이디.
   */
  LectureHistoryDto save(long userId, long lectureId);

  /**
   * 특정 특강 신청 수 조회.
   *
   * @param lectureId 특강 아이디.
   * @return 신청한 사용자 수.
   */
  long countApplyByLectureId(long lectureId);

  /**
   * 이미 특강를 신청했는지 조회.
   *
   * @param userId    사용자 아이디.
   * @param lectureId 특강 아이디.
   * @return true(신청함), false(신청안함).
   */
  boolean isAlreadyApplyByUserIdAndLectureId(long userId, long lectureId);
}
