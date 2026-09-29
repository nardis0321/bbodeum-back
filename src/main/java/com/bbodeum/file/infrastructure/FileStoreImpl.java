package com.bbodeum.file.infrastructure;

import com.bbodeum.exception.InvalidParamException;
import com.bbodeum.file.domain.File;
import com.bbodeum.file.domain.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileStoreImpl implements FileStore {

    private final FileRepository fileRepository;

    @Override
    public File store(File file) {

        if (file == null) throw new InvalidParamException("file");

        if (StringUtils.isEmpty(file.getFileToken())) throw new InvalidParamException("file.getFileToken()");
        if (StringUtils.isEmpty(file.getPath())) throw new InvalidParamException("file.getPath()");
//        if (StringUtils.isEmpty(file.getContentType())) throw new InvalidParamException("file.getContentType()");
//        if (file.getSize() == null) throw new InvalidParamException("file.getSize()");
        if (file.getStatus() == null) throw new InvalidParamException("file.getStatus()");

        return fileRepository.save(file);
    }
}
