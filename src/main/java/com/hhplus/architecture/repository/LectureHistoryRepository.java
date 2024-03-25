package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.LectureHistory;

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
public interface LectureHistoryRepository {

  /**
   * 특정 강의 신청 수 조회.
   *
   * @param lectureId 강의 아이디.
   * @return 신청한 사용자 수.
   */
  long countApplyByLectureId(long lectureId);

  /**
   * 이미 강의를 신청했는지 조회.
   *
   * @param userId    사용자 아이디.
   * @param lectureId 강의 아이디.
   * @return true(신청함), false(신청안함).
   */
  boolean isAlreadyApplyByUserIdAndLectureId(long userId, long lectureId);

  /**
   * 강의 신청(저장).
   *
   * @param userId    사용자 아이디.
   * @param lectureId 강의 아이디.
   */
  LectureHistory save(long userId, long lectureId);

}
