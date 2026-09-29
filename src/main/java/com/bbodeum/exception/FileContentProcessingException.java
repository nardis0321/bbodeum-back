package com.bbodeum.exception;

import com.bbodeum.common.response.ErrorCode;

public class FileContentProcessingException extends BaseException {

    public FileContentProcessingException() {
        super(ErrorCode.FILE_CONTENT_PROCESSING_FAILED);
    }

    public FileContentProcessingException(ErrorCode errorCode) {
        super(errorCode);
    }

    public FileContentProcessingException(String errorMsg) {
        super(errorMsg, ErrorCode.FILE_CONTENT_PROCESSING_FAILED);
    }

    public FileContentProcessingException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
