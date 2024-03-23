package com.hhplus.architecture.api;

import com.hhplus.architecture.dto.UserSingUpResponse;
import com.hhplus.architecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/singup")
@RequiredArgsConstructor
public class LectureController {

  private final LectureService lectureService;

  /**
   * TODO - 특강 신청 API
   */
  @PostMapping("/{id}")
  public ResponseEntity<UserSingUpResponse> singUp(@PathVariable Long id) {
    return ResponseEntity.ok(lectureService.userSingUp(id));
  }

  /**
   * TODO - 특강 신청 여부 조회 API
   */
}
