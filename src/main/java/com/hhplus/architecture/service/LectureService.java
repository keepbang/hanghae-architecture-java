package com.hhplus.architecture.service;

import com.hhplus.architecture.domain.Lecture;
import org.springframework.stereotype.Service;

/**
 * create on 3/23/24. create by IntelliJ IDEA.
 *
 * <p> 강의 관련 서비스 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Service
public class LectureService {

  private final LectureManager lectureManager;
  private final LectureHistoryManager lectureHistoryManager;

  public LectureService(LectureManager lectureManager,
      LectureHistoryManager lectureHistoryManager) {
    this.lectureManager = lectureManager;
    this.lectureHistoryManager = lectureHistoryManager;
  }


  /**
   * 강의 등록
   *
   * @param name                강의 이름.
   * @param maxUser             최대 수강생 수.
   * @param startApplyMillis    신청 시작 시간.
   * @param startLectureMillis  신청 종료 시간.
   * @return  저장된 강의.
   */
  public Lecture saveLecture(String name, long maxUser, long startApplyMillis,
      long startLectureMillis) {
    lectureManager.save(name, maxUser, startApplyMillis, startLectureMillis);
  }

  /**
   * 사용자 특강 신청.
   *
   * @param lectureId     강의 아이디.
   * @param userId        사용자 아이디.
   * @return 신청 여부.
   */
  public Boolean userApply(Long lectureId, Long userId) {
    long totalApplyCount = lectureHistoryManager.countApplyByLectureId(lectureId);


    return true;
  }

}
