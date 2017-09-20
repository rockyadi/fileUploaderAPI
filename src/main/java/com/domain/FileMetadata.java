package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@JsonIgnoreProperties
@Entity
public class FileMetadata  {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String fileName;
    private String author;
    private String version;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
