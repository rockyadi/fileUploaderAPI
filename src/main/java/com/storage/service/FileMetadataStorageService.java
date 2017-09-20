package com.storage.service;

import com.domain.FileMetadata;
import com.exception.FileArchiveException;

public interface FileMetadataStorageService {

     FileMetadata save(FileMetadata fileMetadata) throws FileArchiveException;
     FileMetadata getFileMetaData(long id) throws FileArchiveException;
}
