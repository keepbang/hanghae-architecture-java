package com.hhplus.architecture.service;

import com.hhplus.architecture.dto.ApplyCounterDto;
import com.hhplus.architecture.dto.CreateLectureRequest;
import com.hhplus.architecture.dto.LectureDto;
import com.hhplus.architecture.dto.LectureHistoryDto;
import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * create on 3/23/24. create by IntelliJ IDEA.
 *
 * <p> 특강 관련 서비스 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
public class LectureService {

  private final LectureManager lectureManager;
  private final LectureHistoryManager lectureHistoryManager;
  private final LectureValidator lectorValidator;

  public LectureService(LectureManager lectureManager,
      LectureHistoryManager lectureHistoryManager,
      LectureValidator lectorValidator) {
    this.lectureManager = lectureManager;
    this.lectureHistoryManager = lectureHistoryManager;
    this.lectorValidator = lectorValidator;
  }


  /**
   * 특강 등록.
   *
   * @param request 특강 등록 요청 정보.
   * @return 저장된 특강.
   */
  @Transactional
  public LectureDto saveLecture(@NonNull CreateLectureRequest request) {

    request.validationRequest();

    return lectureManager.save(
        request.name(),
        request.maxUser(),
        request.startApplyMillis(),
        request.startLectureMillis()
    );
  }

  /**
   * 사용자 특강 신청.
   *
   * @param userId    사용자 아이디.
   * @param lectureId 특강 아이디.
   */
  @Transactional
  public LectureHistoryDto userApply(Long userId, Long lectureId) {
    // 증가된 dto
    ApplyCounterDto applyCounterDto = lectureManager.findCountAndLockByLectureId(lectureId)
        .increase();

    lectorValidator.validAlreadyApply(
        lectureHistoryManager.isAlreadyApplyByUserIdAndLectureId(userId, lectureId)
    );

    LectureHistoryDto lectureHistoryDto = lectureHistoryManager.save(userId, lectureId);
    lectureManager.saveApplyCount(lectureId, applyCounterDto);

    return lectureHistoryDto;
  }

  /**
   * 특정 특강를 들은 학생 수 조회.
   *
   * @param lectureId 특강 아이디.
   * @return 특강 정보.
   */
  public ApplyCounterDto countApplyUserByLectureId(Long lectureId) {
    return lectureManager.findCountAndLockByLectureId(lectureId);
  }

  /**
   * 특강신청 성공/실패 조회
   *
   * @param lectureId 특강 아이디.
   * @param userId    사용자 아이디.
   * @return 성공/실패 여부.
   */
  public Boolean isApplyByLectureIdAndUserId(Long lectureId, Long userId) {
    return lectureHistoryManager.isAlreadyApplyByUserIdAndLectureId(userId, lectureId);
  }

  /**
   * 특강 목록 조회.
   *
   * @return  특강 목록.
   */
  public List<LectureDto> findAllLectures() {
    return lectureManager.findAll();
  }
}
