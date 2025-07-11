package com.insta_clone.api.common.handler;

import com.insta_clone.api.common.dto.ApiResponse;
import com.insta_clone.api.common.exception.MyAppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author rua
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 커스텀 예외 처리
    @ExceptionHandler(MyAppException.class)
    public ResponseEntity<ApiResponse<Object>> handleMyAppException(MyAppException ex) {
        return ResponseEntity
                .status(ex.getCode())
                .body(ApiResponse.error(ex.getMessage(), ex.getCode()));
    }

    // 자주 쓰는 Spring 내장 예외(입력값 검증 실패 등)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(msg, 400));
    }

    // 예상치 못한 모든 에러 처리 (최후의 방어선)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAll(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(ApiResponse.error("서버 내부 오류가 발생했습니다.", 500));
    }
}
