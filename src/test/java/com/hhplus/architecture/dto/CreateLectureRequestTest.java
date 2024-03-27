package com.hhplus.architecture.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.architecture.common.exception.InvalidRequestException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
class CreateLectureRequestTest {

  private static Stream<Arguments> invalidRequest() {
    return Stream.of(
        Arguments.of(new CreateLectureRequest(null, 30L, System.currentTimeMillis(),
            System.currentTimeMillis())),
        Arguments.of(new CreateLectureRequest("특강", 0, System.currentTimeMillis(),
            System.currentTimeMillis())),
        Arguments.of(new CreateLectureRequest("특강", 30L, 0,
            System.currentTimeMillis())),
        Arguments.of(new CreateLectureRequest("특강", 30L, System.currentTimeMillis(),
            0))
    );
  }

  @ParameterizedTest
  @MethodSource("invalidRequest")
  @DisplayName("강의 등록 시 request 검증")
  void validRequest_exceptionTest(CreateLectureRequest request) {
    // given
    // when
    // then
    assertThatThrownBy(request::validationRequest)
        .isInstanceOf(InvalidRequestException.class)
        .hasMessage("잘못된 요청입니다.");

  }

}