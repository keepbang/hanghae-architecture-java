package com.hhplus.architecture.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.architecture.common.exception.InvalidRequestException;
import com.hhplus.architecture.common.exception.LectureApplyException;
import com.hhplus.architecture.dto.CreateLectureRequest;
import com.hhplus.architecture.dto.LectureDto;
import com.hhplus.architecture.service.LectureService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


/**
 * create on 2024/03/27.
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
@WebMvcTest(LectureController.class)
class LectureControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();

  @MockBean
  private LectureService lectureService;

  @Test
  @DisplayName("특강 등록")
  void saveLecture() throws Exception {
    // given
    CreateLectureRequest request = new CreateLectureRequest(
        "토요 특강",
        30L,
        System.currentTimeMillis() + 10000L,
        System.currentTimeMillis() + 10000L
    );
    given(lectureService.saveLecture(
        any(CreateLectureRequest.class))
    ).willReturn(new LectureDto(
        1L,
        request.name(),
        request.maxUser(),
        request.startApplyMillis(),
        request.startApplyMillis()
    ));

    // when
    // then
    mockMvc.perform(post("/lectures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpectAll(status().isCreated());
  }

  @Test
  @DisplayName("특강 등록 실패")
  void saveLecture_fail() throws Exception {
    // given
    CreateLectureRequest request = new CreateLectureRequest(
        "토요 특강",
        30L,
        System.currentTimeMillis() + 10000L,
        System.currentTimeMillis() + 10000L
    );

    given(lectureService.saveLecture(
        any(CreateLectureRequest.class))
    ).willThrow(InvalidRequestException.class);

    // when
    // then
    mockMvc.perform(post("/lectures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpectAll(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("400"));
  }

  @Test
  @DisplayName("특강 목록 조회")
  void findAllLectures() throws Exception {
    // given

    LectureDto lectureDto = new LectureDto(
        1L,
        "토요 특강",
        30L,
        System.currentTimeMillis() + 10000L,
        System.currentTimeMillis() + 10000L
    );

    given(lectureService.findAllLectures())
        .willReturn(List.of(lectureDto));

    // when
    // then
    mockMvc.perform(get("/lectures"))
        .andExpectAll(status().isOk())
        .andExpect(jsonPath("$").isArray());
  }

  @Test
  @DisplayName("특강 신청 실패")
  void apply_fail() throws Exception {
    // given
    given(lectureService.userApply(anyLong(), anyLong()))
        .willThrow(LectureApplyException.class);

    // when
    // then
    mockMvc.perform(post("/lectures/{lectureId}/users/{userId}",1L, 1L))
        .andExpectAll(status().isBadRequest())
        .andExpect(jsonPath("$.code").value("400"));
  }

  @Test
  @DisplayName("특강 신청 내역 조회")
  void isApplyBy() throws Exception {
    // given
    given(lectureService.isApplyByLectureIdAndUserId(anyLong(), anyLong()))
        .willReturn(true);

    // when
    // then
    mockMvc.perform(get("/lectures/{lectureId}/users/{userId}",1L, 1L))
        .andExpectAll(status().isOk())
        .andExpect(jsonPath("$").value(true));

  }
}