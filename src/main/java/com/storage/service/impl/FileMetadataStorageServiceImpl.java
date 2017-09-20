package com.storage.service.impl;

import com.domain.FileMetadata;
import com.exception.FileArchiveException;
import com.storage.service.FileMetadataStorageService;
import com.repositories.FileStorageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FileMetadataStorageServiceImpl implements FileMetadataStorageService {

    private FileStorageRepo fileStorageRepo;

    @Autowired
    public void setFileStorageRepo(FileStorageRepo fileStorageRepo) {
        this.fileStorageRepo = fileStorageRepo;
    }

    public FileMetadata save(FileMetadata fileMetadata) throws FileArchiveException {
        try {
            return fileStorageRepo.save(fileMetadata);

        }
        catch (Exception e) {
                throw new FileArchiveException("Failed to store file metadata " + fileMetadata.getFileName(), e);
        }
    }

    public FileMetadata getFileMetaData(long id) throws FileArchiveException {
        try {
            return fileStorageRepo.findOne(id);
        }
        catch (Exception e) {
            throw new FileArchiveException("Failed to retrieve metadata with id " + id, e);
        }
    }

}
