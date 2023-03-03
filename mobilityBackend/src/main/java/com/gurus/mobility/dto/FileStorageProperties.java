package com.gurus.mobility.dto;

import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@NoArgsConstructor
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

}
