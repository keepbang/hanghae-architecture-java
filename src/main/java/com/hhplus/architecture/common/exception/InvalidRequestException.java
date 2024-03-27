package com.hhplus.architecture.common.exception;

/**
 * create on 3/26/24. create by IntelliJ IDEA.
 *
 * <p> 잘못된 요청에 대한 Exception </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class InvalidRequestException extends RuntimeException {
  public InvalidRequestException(String message) {
    super(message);
  }
}
