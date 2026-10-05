package com.bbodeum.file.domain.content;

import org.springframework.core.io.Resource;

import java.io.File;
import java.util.Optional;

public interface FileContentReader {
    Resource getThumbnail(Long courseId);
    File getThumbnail(String filePath);
    Optional<byte[]> getBytes(String saveName);
}