package com.repositories;

import com.domain.FileMetadata;
import org.springframework.data.repository.CrudRepository;

public interface FileStorageRepo extends CrudRepository<FileMetadata, Long> {
}
