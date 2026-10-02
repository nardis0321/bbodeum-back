package com.bbodeum.file.domain;

import com.bbodeum.file.domain.content.FileContentProcessor;
import com.bbodeum.file.domain.content.FileContentStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileStore fileStore;
    private final FileContentProcessor fileContentProcessor;
    private final FileContentStore fileContentStore;

    @Override
    public String registerFile(FileCommand fileCommand) {

        var webp = fileContentProcessor.convertImageToThumbnailWebp(fileCommand.getMultipartFile());
        String newName = UUID.randomUUID() + ".webp";
        fileContentStore.storeByteArray(webp, newName);

        File entity = fileStore.store(fileCommand.withPath(newName).toEntity());
        return entity.getFileToken();
    }
}