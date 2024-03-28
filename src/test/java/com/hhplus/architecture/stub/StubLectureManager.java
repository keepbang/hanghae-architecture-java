package com.hhplus.architecture.stub;

import com.hhplus.architecture.dto.ApplyCounterDto;
import com.hhplus.architecture.dto.LectureDto;
import com.hhplus.architecture.service.LectureManager;
import java.time.LocalDateTime;
import java.util.List;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubLectureManager implements LectureManager {

  private LectureDto lectureDto
      = new LectureDto(1L,
      "토요특강",
      30L,
      System.currentTimeMillis(),
      LocalDateTime.now()
          .plusDays(3L)
          .getNano()
  );

  private ApplyCounterDto applyCounterDto = new ApplyCounterDto(
      1L, 0L, 30L
  );

  @Override
  public LectureDto save(String name, long maxUser, long startApplyMillis,
      long startLectureMillis) {
    return lectureDto;
  }

  @Override
  public List<LectureDto> findAll() {
    return List.of(lectureDto);
  }

  @Override
  public ApplyCounterDto findCountAndLockByLectureId(long lectureId) {
    return this.applyCounterDto;
  }

  @Override
  public void saveApplyCount(Long lectureId, ApplyCounterDto applyCounter) {

  }

  public void initApply(long applyCount) {
    this.applyCounterDto = new ApplyCounterDto(1L, applyCount, 30L);
  }
}
