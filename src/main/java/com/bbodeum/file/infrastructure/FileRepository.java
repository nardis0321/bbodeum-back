package com.bbodeum.file.infrastructure;

import com.bbodeum.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByFileToken(String fileToken);
}
