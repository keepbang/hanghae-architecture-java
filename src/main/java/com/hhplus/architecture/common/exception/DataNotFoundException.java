package com.hhplus.architecture.common.exception;

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
public class DataNotFoundException extends RuntimeException {

  public DataNotFoundException(String message) {
    super(message);
  }
}
