package com.hhplus.architecture.dto;

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
public record LectureHistoryDto(
    long id,
    long userId,
    long lectureId,
    long applyMillis
) {

}
