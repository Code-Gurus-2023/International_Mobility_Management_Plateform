package com.gurus.mobility.service.User;

import com.gurus.mobility.dto.FileStorageProperties;
import com.gurus.mobility.exception.FileStorageExeption;
import com.gurus.mobility.exception.MyFileNotFoundExeption;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class ImageService {

    private final Path fileStorageLocation;
    private static String storageDirectoryPath = System.getProperty("user.dir") + "/src/main/resources/static/images";
    private static final String DEFAULT_IMAGE_EXTENSION = "png";

    @Autowired
    public ImageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(storageDirectoryPath);

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageExeption("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageExeption("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageExeption("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundExeption("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundExeption("File not found " + fileName, ex);
        }
    }
    public  byte[] getImage( String image) throws IOException {

        FileInputStream in = new FileInputStream(image);

        return IOUtils.toByteArray(in);
    }
//    public ResponseEntity<String> uploadFile(MultipartFile multipartFile){
//        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        Path fileStorage = get(DIRECTORY,filename).toAbsolutePath().normalize();
//
//    }
    public String uploadImage(MultipartFile file) {

        makeDirectoryIfNotExist(storageDirectoryPath);
        Path storageDirectory = Paths.get(storageDirectoryPath);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path destination = Paths.get(storageDirectory.toString() + "/" + fileName);

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);// we are Copying all bytes from an input stream to a file

        } catch (IOException e) {
            e.printStackTrace();
        }


        // return the download image url as a response entity
        String imageLink = fileName.substring(5);
        return imageLink;
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
