package com.hhplus.architecture.common.exception;

/**
 * create on 2024/03/25.
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
public class MaxApplyException extends RuntimeException {

  private static final String MESSAGE = "최대 신청수를 초과했습니다.";

  public MaxApplyException() {
    super(MESSAGE);
  }
}
