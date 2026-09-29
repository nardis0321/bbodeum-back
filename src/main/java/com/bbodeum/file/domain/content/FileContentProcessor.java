package com.bbodeum.file.domain.content;

import org.springframework.web.multipart.MultipartFile;

public interface FileContentProcessor {
    String uploadImage(MultipartFile file);
}