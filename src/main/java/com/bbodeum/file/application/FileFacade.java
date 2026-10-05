package com.bbodeum.file.application;

import com.bbodeum.file.domain.File;
import com.bbodeum.file.domain.FileCommand;
import com.bbodeum.file.domain.FileInfo;
import com.bbodeum.file.domain.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileFacade {

    private final FileService fileService;

    public String registerFile(FileCommand fileCommand) {
        return fileService.registerFile(fileCommand);
    }

    public Optional<byte[]> retrieveCourseImage(Long courseId) {
        return fileService.courseImage(courseId);
    }

    public FileInfo retrieveFileInfo(Long courseId) {
        return fileService.retrieveFileInfo(courseId, File.FileReferenceType.COURSE_IMG);
    }
}
