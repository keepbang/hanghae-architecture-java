package com.hhplus.architecture.common;


import com.hhplus.architecture.common.exception.DataNotFoundException;
import com.hhplus.architecture.common.exception.InvalidRequestException;
import com.hhplus.architecture.common.exception.LectureApplyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
@RestControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {
  @ExceptionHandler(LectureApplyException.class)
  public ResponseEntity<ErrorResponse> handleLectureApplyException(Exception e) {
    log.error("error: ", e);
    ErrorResponse errorResponse = new ErrorResponse("400", e.getMessage());
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<ErrorResponse> handleInvalidRequestException(Exception e) {
    log.error("error: ", e);
    ErrorResponse errorResponse = new ErrorResponse("400", e.getMessage());
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleDataNotFoundException(Exception e) {
    log.error("error: ", e);
    ErrorResponse errorResponse = new ErrorResponse("400", e.getMessage());
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleViolationException(Exception e) {
    log.error("error: ", e);
    ErrorResponse errorResponse = new ErrorResponse("400", "이미 신청했습니다.");
    return ResponseEntity.badRequest().body(errorResponse);
  }

}
