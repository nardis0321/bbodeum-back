package com.bbodeum.file.domain;

import lombok.Getter;

@Getter
public class FileInfo {

    private final String fileToken;
    private final String path;
    private final String contentType;
    private final Long size;
    private final File.Status status;
    private final File.FileReferenceType fileReferenceType;
    private final Long fileReferenceId;
    private final File.FileType fileType;

    public FileInfo(File file) {
        this.fileToken = file.getFileToken();
        this.path = file.getPath();
        this.contentType = file.getContentType();
        this.size = file.getSize();
        this.status = file.getStatus();
        this.fileReferenceType = file.getFileReferenceType();
        this.fileReferenceId = file.getFileReferenceId();
        this.fileType = file.getFileType();
    }
}
