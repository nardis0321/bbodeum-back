package com.bbodeum.file.domain.content.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class FileSizeContentValidator implements FileContentValidator {

    private static final long MAX_FILE_SIZE = 70 * 1024 * 1024;

    @Override
    public void validate(MultipartFile file) {
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("파일 용량은 70MB를 초과할 수 없습니다.");
        }
    }
}
