package com;

import com.storage.service.FileMetadataStorageService;
import com.storage.service.impl.FileMetadataStorageServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.storage.StorageProperties;
import com.storage.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }


    @Bean
    public FileMetadataStorageService fileMetadataStorageService() {

        FileMetadataStorageService fileMetadataStorageService = new FileMetadataStorageServiceImpl();
        return fileMetadataStorageService;
    }
}
