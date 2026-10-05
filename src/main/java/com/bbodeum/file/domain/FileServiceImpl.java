package com.bbodeum.file.domain;

import com.bbodeum.file.domain.content.FileContentProcessor;
import com.bbodeum.file.domain.content.FileContentReader;
import com.bbodeum.file.domain.content.FileContentStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileReader fileReader;
    private final FileStore fileStore;
    private final FileContentProcessor fileContentProcessor;
    private final FileContentStore fileContentStore;
    private final FileContentReader fileContentReader;

    @Override
    public String registerFile(FileCommand fileCommand) {

        var webp = fileContentProcessor.convertImageToThumbnailWebp(fileCommand.getMultipartFile());
        String newName = UUID.randomUUID() + ".webp";
        fileContentStore.storeByteArray(webp, newName);

        File entity = fileStore.store(fileCommand.withPath(newName).toEntity());
        return entity.getFileToken();
    }

    @Override
    public FileInfo retrieveFileInfo(Long fileReferenceId, File.FileReferenceType fileReferenceType) {
        File file = fileReader.getFile(fileReferenceId, fileReferenceType);
        return new FileInfo(file);
    }

    @Override
    public Optional<byte[]> retrieveFileContent(Long fileReferenceId, File.FileReferenceType fileReferenceType) {
        FileInfo fileInfo = retrieveFileInfo(fileReferenceId, fileReferenceType);
        return fileContentReader.getBytes(fileInfo.getPath());
    }

    @Override
    public Optional<byte[]> courseImage(Long courseId) {
        return retrieveFileContent(courseId, File.FileReferenceType.COURSE_IMG);
    }
}