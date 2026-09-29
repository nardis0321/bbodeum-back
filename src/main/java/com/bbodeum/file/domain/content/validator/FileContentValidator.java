package com.bbodeum.file.domain.content.validator;

import org.springframework.web.multipart.MultipartFile;

public interface FileContentValidator {
    void validate(MultipartFile file);
}
