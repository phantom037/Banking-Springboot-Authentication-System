package com.example.auth_service.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(500, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR), //INTERNAL_SERVER_ERROR => code = 500
    INVALID_KEY(400, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(400, "User existed",HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(400, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(400, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(404, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(401, "User is not authenticated", HttpStatus.UNAUTHORIZED), //UNAUTHORIZED => code = 401
    UNAUTHORIZED(403, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(400, "Invalid date of birth. You must be at least {min}", HttpStatus.BAD_REQUEST),
    PERMISSION_EXISTED(400, "Permission existed", HttpStatus.BAD_REQUEST),
    FIRSTNAME_IS_NULL(400, "First name must not be null", HttpStatus.BAD_REQUEST),
    LASTNAME_IS_NULL(400, "Last name must not be null", HttpStatus.BAD_REQUEST);
    private int code;
    private String message;
    private HttpStatus status;
    ErrorCode(int code, String message, HttpStatus status){
        this.code = code;
        this.message = message;
        this.status = status;
    }


}
