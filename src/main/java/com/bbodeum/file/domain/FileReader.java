package com.bbodeum.file.domain;

public interface FileReader {
    File getFile(Long fileId);
    File getFile(String fileToken);
    File getFile(Long fileReferenceId, File.FileReferenceType fileReferenceType);
}
