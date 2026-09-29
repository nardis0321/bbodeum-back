package com.bbodeum.file.infrastructure.content;

import com.bbodeum.file.domain.content.FileContentProcessor;
import com.bbodeum.file.domain.content.FileContentStore;
import com.bbodeum.file.domain.content.ThumbnailProcessor;
import com.bbodeum.file.domain.content.validator.FileContentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileContentProcessorImpl implements FileContentProcessor {

    private final List<FileContentValidator> fileContentValidatorList;
    private final ThumbnailProcessor thumbnailProcessor;
    private final FileContentStore fileContentStore;

    @Override
    public String uploadImage(MultipartFile file) {
        System.out.println("uploadImage : start validate");
        fileContentValidatorList.forEach(fileValidator -> fileValidator.validate(file));
        System.out.println("파일 validate 완료");
        BufferedImage thumbnail = thumbnailProcessor.process(file);
        System.out.println("썸네일화 완료");
        return fileContentStore.storeBufferedImage(thumbnail);
    }
}