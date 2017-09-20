package com.storage.service;

import com.domain.FileMetadata;
import com.repositories.FileStorageRepo;
import com.storage.service.impl.FileMetadataStorageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;


public class FileMetadataStorageServiceImplMockTest {

    @Mock
    private FileStorageRepo fileStorageRepo;
    private FileMetadataStorageServiceImpl fileMetadataStorageServiceImpl;
    @Mock
    private FileMetadata fileMetadata;
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        fileMetadataStorageServiceImpl=new FileMetadataStorageServiceImpl();
        fileMetadataStorageServiceImpl.setFileStorageRepo(fileStorageRepo);
    }
    @Test
    public void shouldReturnFileMetadata_whenGetFileMetadataIsCalled() throws Exception {
        // Arrange
        when(fileStorageRepo.findOne(5l)).thenReturn(fileMetadata);
        // Act
        FileMetadata retrievedFileMetadata = fileMetadataStorageServiceImpl.getFileMetaData(5l);
        // Assert
        assertThat(retrievedFileMetadata, is(equalTo(fileMetadata)));

    }
    @Test
    public void shouldReturnFileMetadata_whenSaveIsCalled() throws Exception {
        // Arrange
        when(fileStorageRepo.save(fileMetadata)).thenReturn(fileMetadata);
        // Act
        FileMetadata savedFileMetadata = fileMetadataStorageServiceImpl.save(fileMetadata);
        // Assert
        assertThat(savedFileMetadata, is(equalTo(fileMetadata)));
    }

}