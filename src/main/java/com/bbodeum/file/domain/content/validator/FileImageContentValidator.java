package com.bbodeum.file.domain.content.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class FileImageContentValidator implements FileContentValidator {

    @Override
    public void validate(MultipartFile file) {
        if (!isImage(file.getContentType())) {
            throw new IllegalArgumentException("이미지 파일만 업로드할 수 있습니다.");
        }
    }

    private boolean isImage(String contentType) {
        return "image/jpeg".equals(contentType)
                || "image/png".equals(contentType)
                || "image/gif".equals(contentType);
    }
}
