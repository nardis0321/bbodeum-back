package com.bbodeum.file.domain.content;

import com.bbodeum.exception.FileContentProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThumbnailProcessorImpl implements ThumbnailProcessor {
        private static final int MAX_SIZE = 1200;
        private static final double QUALITY = 0.8;

        @Override
        public BufferedImage process(MultipartFile file) {
            try {
                return Thumbnails.of(file.getInputStream())
                        .size(MAX_SIZE, MAX_SIZE)
                        .outputFormat("webp")
                        .outputQuality(QUALITY)
                        .asBufferedImage();
            } catch (IOException e) {
                throw new FileContentProcessingException("thumbnail processing failed");
            }
        }
}
