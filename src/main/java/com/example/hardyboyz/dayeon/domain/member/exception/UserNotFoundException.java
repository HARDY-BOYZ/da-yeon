package com.example.hardyboyz.dayeon.domain.member.exception;

import com.example.hardyboyz.dayeon.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {

    public static final String USER_NOT_FOUNT = "사용자를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(USER_NOT_FOUNT);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }

}
