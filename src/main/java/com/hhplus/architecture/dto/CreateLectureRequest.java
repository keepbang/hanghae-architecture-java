package com.hhplus.architecture.dto;

/**
 * create on 3/25/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record CreateLectureRequest(
    String name,
    long maxUser,
    long startApplyMillis,
    long startLectureMillis
) {

}
