package com.gurus.mobility.service.User;

import java.io.IOException;
import java.util.stream.Stream;

import com.gurus.mobility.entity.user.FileDB;
import com.gurus.mobility.repository.User.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileStorageServiceImpl implements IFileStorageService{

    @Autowired
    private FileDBRepository fileDBRepository;
    @Override
    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }
    @Override
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }
    @Override
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
