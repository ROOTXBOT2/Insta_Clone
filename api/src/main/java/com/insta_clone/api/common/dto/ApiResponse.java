package com.insta_clone.api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rua
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;    // 성공/실패
    private String message;     // 상태 메시지 (에러/성공)
    private T data;             // 실제 반환 데이터 (User, Token 등)
    private int code;           // (선택) 상태코드, 상세 사유

    // 성공
    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<>(true, msg, data, 200);
    }
    // 실패
    public static <T> ApiResponse<T> error(String msg, int code) {
        return new ApiResponse<>(false, msg, null, code);
    }
}
