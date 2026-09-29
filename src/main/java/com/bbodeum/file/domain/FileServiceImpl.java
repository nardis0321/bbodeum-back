package com.bbodeum.file.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileStore fileStore;

    @Override
    public String registerFile(FileCommand fileCommand) {
        File file = fileStore.store(fileCommand.toEntity());
        return file.getFileToken();
    }

}