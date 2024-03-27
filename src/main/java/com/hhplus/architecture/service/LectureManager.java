package com.hhplus.architecture.service;

import com.hhplus.architecture.dto.LectureDto;
import java.util.List;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 특강 관련 Interface </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface LectureManager {

  /**
   * 특강 조회.
   *
   * @param id 특강 아이디.
   * @return 특강.
   */
  LectureDto findAndLockById(long id);

  /**
   * 특강 등록
   *
   * @param name               특강 이름.
   * @param maxUser            최대 수강생 수.
   * @param startApplyMillis   특강 신청 시작 시간.
   * @param startLectureMillis 특강 시작 시간.
   * @return 저장된 특강.
   */
  LectureDto save(String name, long maxUser, long startApplyMillis, long startLectureMillis);

  /**
   * 등록된 특강 목록 조회.
   *
   * @return  특강 목록.
   */
  List<LectureDto> findAll();

}
