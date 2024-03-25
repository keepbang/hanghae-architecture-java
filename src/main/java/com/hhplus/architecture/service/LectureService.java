package com.hhplus.architecture.service;

import com.hhplus.architecture.common.exception.LectureApplyException;
import com.hhplus.architecture.domain.Lecture;
import com.hhplus.architecture.dto.LectureDto;
import com.hhplus.architecture.dto.LectureHistoryDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
   * @param name               강의 이름.
   * @param maxUser            최대 수강생 수.
   * @param startApplyMillis   신청 시작 시간.
   * @param startLectureMillis 신청 종료 시간.
   * @return 저장된 강의.
   */
  public LectureDto saveLecture(String name, long maxUser, long startApplyMillis,
      long startLectureMillis) {
    return lectureManager.save(name, maxUser, startApplyMillis, startLectureMillis);
  }

  /**
   * 사용자 특강 신청.
   *
   * @param userId    사용자 아이디.
   * @param lectureId 강의 아이디.
   */
  public LectureHistoryDto userApply(Long userId, Long lectureId) {
    long totalApplyCount = lectureHistoryManager.countApplyByLectureId(lectureId);
    LectureDto lectureDto = lectureManager.findById(lectureId);

    if (totalApplyCount >= lectureDto.maxUser()) {
      throw new LectureApplyException("최대 신청수를 초과했습니다.");
    }

    boolean isAlready = lectureHistoryManager.isAlreadyApplyByUserIdAndLectureId(
        userId, lectureId);

    if (isAlready) {
      throw new LectureApplyException("이미 신청했습니다.");
    }

    return lectureHistoryManager.save(userId, lectureId);
  }

  /**
   * 강의 조회.
   *
   * @param lectureId 강의 아이디.
   * @return 강의 정보.
   */
  public Long countApplyUserByLectureId(Long lectureId) {
    return lectureHistoryManager.countApplyByLectureId(lectureId);
  }

  public Boolean isApplyByLectureIdAndUserId(Long lectureId, Long userId) {
    return lectureHistoryManager.isAlreadyApplyByUserIdAndLectureId(userId, lectureId);
  }
}
