package com.hhplus.architecture.repository;

import com.hhplus.architecture.domain.Lecture;
import java.util.Optional;

/**
 * create on 2024/03/25.
 * create by IntelliJ IDEA.
 *
 * <p> 강의 관련 DAO </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface LectureRepository {

  /**
   * 강의 조회.
   *
   * @param lectureId 강의 아이디.
   * @return 강의.
   */
  Optional<Lecture> findById(long lectureId);

  /**
   * 강의 등록
   *
   * @param name               강의 이름.
   * @param maxUser            최대 수강생 수.
   * @param startApplyMillis  특강 신청 시작 시간.
   * @param startLectureMillis 특강 시작 시간.
   * @return 저장된 특강.
   */
  Lecture save(String name, long maxUser, long startApplyMillis, long startLectureMillis);
}
