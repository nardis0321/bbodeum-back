package com.bbodeum.file.application;

import com.bbodeum.file.domain.FileCommand;
import com.bbodeum.file.domain.FileService;
import com.bbodeum.file.domain.content.FileContentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileFacade {

    private final FileService fileService;
    private final FileContentProcessor fileContentProcessor;

    public String registerFile(FileCommand fileCommand) {
        System.out.println("FileFacade : start registerFile");
        String fileName = fileContentProcessor.uploadImage(fileCommand.getMultipartFile());
        System.out.println("fileContentProcessor 완료");
        return fileService.registerFile(fileCommand.withPath(fileName));
    }

}
