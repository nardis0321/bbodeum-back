package com.bbodeum.file.domain.content;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface FileContentStore {
    String store(MultipartFile file);
    String storeBufferedImage(BufferedImage bufferedImage);
    String storeByteArray(byte[] content, String saveName);
}