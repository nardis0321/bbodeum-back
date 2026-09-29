package com.bbodeum.file.domain;

import com.bbodeum.course.dto.CourseDTORequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@ToString
public class FileCommand {

    private final String path;
    private final String contentType;
    private final Long size;

    private File.FileReferenceType fileReferenceType;
    private Long fileReferenceId;
    private File.FileType fileType;

    private final MultipartFile multipartFile;

    public File toEntity(){
        return File.builder()
                .path(path)
                .contentType(contentType)
                .size(size)
                .fileReferenceType(fileReferenceType)
                .fileReferenceId(fileReferenceId)
                .fileType(fileType)
                .build();
    }

    public static FileCommand from(CourseDTORequest dto, MultipartFile multipartFile) {
        return new FileCommand(
                null,
                multipartFile.getContentType(),
                multipartFile.getSize(),
                File.FileReferenceType.COURSE_IMG,
                dto.getCourseId(),
                File.FileType.DETAIL_IMAGE,
                multipartFile
        );
    }

    public FileCommand withPath(String path) {
        return new FileCommand(
                path,
                contentType,
                size,
                fileReferenceType,
                fileReferenceId,
                fileType,
                multipartFile
        );
    }
}
