package com.bbodeum.file.infrastructure.content;

import com.bbodeum.file.domain.content.FileContentReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@Component
public class FileContentReaderImpl implements FileContentReader {

    private final Path uploadPath;
    public FileContentReaderImpl(
            @Value("${file.upload-path}") String uploadPath) {
        this.uploadPath = Paths.get(uploadPath);
    }

    @Override
    public Resource getThumbnail(Long courseId) {
        String fileName = "t_" + courseId + ".jpg";

        Path filePath = uploadPath
                .resolve(fileName)
                .normalize();

        try {
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getThumbnail(String filePath) {

        return new File(uploadPath.toString(), filePath);
    }

    public Optional<byte[]> getBytes(String saveName) {
        Path path = uploadPath.resolve(saveName);

        try {
            return Optional.of(Files.readAllBytes(path));
        } catch (NoSuchFileException e) {
            log.warn("파일을 찾을 수 없습니다. saveName={}", saveName);
            return Optional.empty();
        } catch (IOException e) {
            log.error("파일을 읽는 중 오류가 발생했습니다. saveName={}", saveName, e);
            return Optional.empty();
        }
    }
}