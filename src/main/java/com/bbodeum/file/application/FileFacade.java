package com.bbodeum.file.application;

import com.bbodeum.file.domain.FileCommand;
import com.bbodeum.file.domain.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileFacade {

    private final FileService fileService;
    public String registerFile(FileCommand fileCommand) {
        return fileService.registerFile(fileCommand);
    }

}
