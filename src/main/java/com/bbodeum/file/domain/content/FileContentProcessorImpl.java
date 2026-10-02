package com.bbodeum.file.domain.content;

import com.bbodeum.file.domain.content.validator.FileContentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileContentProcessorImpl implements FileContentProcessor {

    private final List<FileContentValidator> fileContentValidatorList;
    private final ThumbnailProcessor thumbnailProcessor;
    private final FileContentStore fileContentStore;

    @Override
    public BufferedImage generateThumbnail(MultipartFile file) {
        fileContentValidatorList.forEach(fileValidator -> fileValidator.validate(file));
        return thumbnailProcessor.process(file);
    }

    @Override
    public byte[] convertImageToThumbnailWebp(MultipartFile file) {
        fileContentValidatorList.forEach(fileValidator -> fileValidator.validate(file));
        var thumbnail = thumbnailProcessor.process(file);
        return convertToWebp(thumbnail);
    }

    @Override
    public String uploadImageAsWebpThumbnail(MultipartFile file) {
        fileContentValidatorList.forEach(fileValidator -> fileValidator.validate(file));
        var thumbnail = thumbnailProcessor.process(file);
        byte[] bytes = convertToWebp(thumbnail);

        String newFileName = UUID.randomUUID() + ".webp";
        fileContentStore.storeByteArray(bytes, newFileName);

        return "";
    }



    private byte[] convertToWebp(BufferedImage image) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            ImageIO.write(image, "webp", outputStream);

            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("이미지 변환에 실패했습니다.", e);
        }
    }
}