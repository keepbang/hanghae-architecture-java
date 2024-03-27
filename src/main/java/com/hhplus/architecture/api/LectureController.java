package com.hhplus.architecture.api;

import com.hhplus.architecture.dto.CreateLectureRequest;
import com.hhplus.architecture.dto.LectureDto;
import com.hhplus.architecture.dto.LectureHistoryDto;
import com.hhplus.architecture.service.LectureService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create on 3/23/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {

  private final LectureService lectureService;

  @PostMapping
  public ResponseEntity<LectureDto> saveLecture(@RequestBody CreateLectureRequest request) {
    return new ResponseEntity<>(
        lectureService.saveLecture(request)
        , HttpStatus.CREATED
    );
  }

  @GetMapping
  public ResponseEntity<List<LectureDto>> findAllLectures() {
    return new ResponseEntity<>(
        lectureService.findAllLectures()
        , HttpStatus.OK
    );
  }

  @PostMapping("/{lectureId}/users/{userId}")
  public ResponseEntity<LectureHistoryDto> apply(@PathVariable Long lectureId,
      @PathVariable Long userId) {
    return ResponseEntity.ok(lectureService.userApply(userId, lectureId));
  }

  @GetMapping("/{lectureId}/users/{userId}")
  public ResponseEntity<Boolean> isApplyBy(@PathVariable Long lectureId,
      @PathVariable Long userId) {
    return ResponseEntity.ok(lectureService.isApplyByLectureIdAndUserId(lectureId, userId));
  }
}
