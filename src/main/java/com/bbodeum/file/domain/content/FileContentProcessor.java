package com.bbodeum.file.domain.content;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface FileContentProcessor {
    BufferedImage generateThumbnail(MultipartFile file);
    byte[] convertImageToThumbnailWebp(MultipartFile file);
    String uploadImageAsWebpThumbnail(MultipartFile file);
}