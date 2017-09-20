package com.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import com.domain.FileMetadata;
import com.storage.service.FileMetadataStorageService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exception.ArchiveFileNotFoundException;
import com.storage.service.StorageService;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    @Autowired
    private FileMetadataStorageService fileMetadataStorageService;
    private static final Logger LOG = Logger.getLogger(FileUploadController.class);


    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;

    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ApiOperation(value = "Search File Metadata with an ID",response = FileMetadata.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public FileMetadata showProduct(@PathVariable Long id){
        FileMetadata fileMetadata = fileMetadataStorageService.getFileMetaData(id);
        return fileMetadata;
    }

    @ApiOperation(value = "Upload a file")
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,@ModelAttribute("fileMetadata") FileMetadata fileMetadata,
            RedirectAttributes redirectAttributes) {

        storageService.store(file);
        fileMetadataStorageService.save(fileMetadata);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "metadata is stored with id " + fileMetadata.getId() +"!");

        return "redirect:/";
    }

    @ExceptionHandler(ArchiveFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(ArchiveFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
