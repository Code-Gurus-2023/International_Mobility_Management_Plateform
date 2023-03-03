package com.gurus.mobility.service.User;

import com.gurus.mobility.entity.user.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface IFileStorageService {
    FileDB store(MultipartFile file) throws IOException;

    FileDB getFile(String id);

    Stream<FileDB> getAllFiles();
}
