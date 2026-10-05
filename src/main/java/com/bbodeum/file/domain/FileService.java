package com.bbodeum.file.domain;

import java.util.Optional;

public interface FileService {
    String registerFile(FileCommand fileCommand);
    FileInfo retrieveFileInfo(Long fileReferenceId, File.FileReferenceType fileReferenceType);
    Optional<byte[]> retrieveFileContent(Long fileReferenceId, File.FileReferenceType fileReferenceType);
    Optional<byte[]> courseImage(Long courseId);
}
