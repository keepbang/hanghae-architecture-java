package com.hhplus.architecture.stub;

import com.hhplus.architecture.dto.LectureHistoryDto;
import com.hhplus.architecture.service.LectureHistoryManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class StubLectureHistoryManager implements LectureHistoryManager {

  private List<LectureHistoryDto> lectureHistories = new ArrayList<>();

  @Override
  public LectureHistoryDto save(long userId, long lectureId) {
    LectureHistoryDto dto = new LectureHistoryDto(
        userId,
        lectureId,
        System.currentTimeMillis()
    );

    lectureHistories.add(dto);

    return dto;
  }

  public void initList(int count) {
    for (int i = 1; i <= count; i++) {
      this.save(i, 1L);
    }
  }

  @Override
  public boolean isAlreadyApplyByUserIdAndLectureId(long userId, long lectureId) {
    return lectureHistories.stream()
        .anyMatch(dto -> Objects.equals(dto.userId(), userId)
            && Objects.equals(dto.lectureId(), lectureId));
  }
}
