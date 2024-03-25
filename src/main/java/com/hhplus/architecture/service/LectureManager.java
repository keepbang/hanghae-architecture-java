package com.hhplus.architecture.service;

import com.hhplus.architecture.dto.LectureDto;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 강의 관련 Interface </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface LectureManager {

  /**
   * 강의 조회.
   *
   * @param id 강의 아이디.
   * @return 강의.
   */
  LectureDto findById(long id);

  /**
   * 강의 등록
   *
   * @param name               강의 이름.
   * @param maxUser            최대 수강생 수.
   * @param startApplyMillis   특강 신청 시작 시간.
   * @param startLectureMillis 특강 시작 시간.
   * @return 저장된 특강.
   */
  LectureDto save(String name, long maxUser, long startApplyMillis, long startLectureMillis);

}
