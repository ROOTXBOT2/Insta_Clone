package com.insta_clone.api.common.exception;

/**
 * @author rua
 */
public class MyAppException extends RuntimeException{
    private final int code;        // HTTP 상태코드(401, 404, 409 등)
    private final String message;  // 사용자에게 보여줄 메시지

    public MyAppException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() { return code; }
    @Override
    public String getMessage() { return message; }
}
