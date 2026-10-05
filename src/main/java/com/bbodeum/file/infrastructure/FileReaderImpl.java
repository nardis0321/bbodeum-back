package com.bbodeum.file.infrastructure;

import com.bbodeum.file.domain.File;
import com.bbodeum.file.domain.FileReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileReaderImpl implements FileReader {

    private final FileRepository fileRepository;

    @Override
    public File getFile(Long fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public File getFile(String fileToken) {
        return fileRepository.findByFileToken(fileToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public File getFile(Long fileReferenceId, File.FileReferenceType fileReferenceType) {
        return fileRepository.findByFileReferenceIdAndFileReferenceType(fileReferenceId, fileReferenceType)
                .orElseThrow(EntityNotFoundException::new);
    }
}
