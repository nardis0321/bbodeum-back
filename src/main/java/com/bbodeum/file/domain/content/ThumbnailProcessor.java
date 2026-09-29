package com.bbodeum.file.domain.content;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface ThumbnailProcessor {
    BufferedImage process(MultipartFile file);
}
