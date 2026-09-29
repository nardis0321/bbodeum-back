package com.bbodeum.file.domain;

import com.bbodeum.basetime.entity.BaseTimeEntity;
import com.bbodeum.util.TokenGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "files")
public class File extends BaseTimeEntity {
    private static final String PREFIX_FILE = "fil_";

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fileToken;
    private String path;
    private String contentType;
    private Long size;

    @Enumerated(EnumType.STRING)
    private FileReferenceType fileReferenceType;
    private Long fileReferenceId;

    @Getter
    @RequiredArgsConstructor
    public enum FileReferenceType {
        COURSE_IMG
    }

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Getter
    @RequiredArgsConstructor
    public enum FileType {
        THUMBNAIL,
        DETAIL_IMAGE,
        ATTACHMENT
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활성화"), DISABLE("비활성화");
        private final String description;
    }

    @Builder
    public File(String path, String contentType, Long size,
                FileReferenceType fileReferenceType, Long fileReferenceId, FileType fileType){
//        if (StringUtils.isEmpty(path)) throw new InvalidParamException("empty path");
//        if (StringUtils.isEmpty(contentType)) throw new InvalidParamException("empty contentType");
//        if (StringUtils.isEmpty(size)) throw new InvalidParamException("empty size");

        this.fileToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_FILE);
        this.path = path;
        this.contentType = contentType;
        this.size = size;

        this.fileReferenceType = fileReferenceType;
        this. fileReferenceId = fileReferenceId;
        this.fileType = fileType;

        this.status = Status.ENABLE;
    }

    public void enable() {
        this.status = Status.ENABLE;
    }

    public void disable() {
        this.status = Status.DISABLE;
    }
}
