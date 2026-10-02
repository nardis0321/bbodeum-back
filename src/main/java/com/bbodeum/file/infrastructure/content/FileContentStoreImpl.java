package com.bbodeum.file.infrastructure.content;

import com.bbodeum.exception.FileContentProcessingException;
import com.bbodeum.file.domain.content.FileContentStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Component
public class FileContentStoreImpl implements FileContentStore {

    private final Path uploadPath;
    public FileContentStoreImpl(
            @Value("${file.upload-path}") String uploadPath) {
        this.uploadPath = Paths.get(uploadPath);
    }

    @Override
    public String store(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID() + extension;

        Path targetPath = uploadPath.resolve(fileName);

        try {
            Files.createDirectories(uploadPath);
            file.transferTo(targetPath);

            return fileName;

        } catch (IOException e) {
            throw new FileContentProcessingException("파일 저장에 실패했습니다.");
        }
    }

    @Override
    public String storeBufferedImage(BufferedImage bufferedImage) {

        String fileName = UUID.randomUUID() + ".webp";
        Path path = uploadPath.resolve(fileName);

        try {
            Files.createDirectories(path.getParent());

            ImageIO.write(bufferedImage, "webp", path.toFile());

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("이미지 저장에 실패했습니다.", e);
        }
    }

    @Override
    public String storeByteArray(byte[] content, String saveName) {
        Path path = uploadPath.resolve(saveName);

        try {
            Files.createDirectories(path.getParent());
            Files.write(path, content);

            return saveName;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장에 실패했습니다.", e);
        }
    }
}
