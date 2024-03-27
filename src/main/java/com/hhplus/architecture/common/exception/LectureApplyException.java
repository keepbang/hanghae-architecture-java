package com.hhplus.architecture.common.exception;

/**
 * create on 2024/03/25.
 * create by IntelliJ IDEA.
 *
 * <p> 특강 신청시 발생하는 Exception </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
public class LectureApplyException extends RuntimeException {
  public LectureApplyException(String message) {
    super(message);
  }
}
