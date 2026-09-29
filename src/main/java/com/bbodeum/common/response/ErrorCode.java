package com.bbodeum.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),

    FILE_CONTENT_PROCESSING_FAILED("파일 처리 중 오류가 발생했습니다.")
    ;


    private final String errorMsg;

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg, arg);
    }
}
