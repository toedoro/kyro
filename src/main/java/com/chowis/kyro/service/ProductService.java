package com.chowis.kyro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.chowis.kyro.config.FileStorageProperties;
import com.chowis.kyro.exception.FileNotFoundException;
import com.chowis.kyro.exception.FileStorageException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ProductService {

	private final Path fileStorageLocation;

    @Autowired
    public ProductService(FileStorageProperties fileStorageProperties) throws FileStorageException {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String saveFile(MultipartFile file) throws FileStorageException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
        	System.out.println("1====================================");
        	System.out.println("1=======> " + ex.getMessage());
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        } catch(Exception ex){
        	System.out.println("2====================================");
        	System.out.println("2=======> " + ex.getMessage());
        	throw new IllegalArgumentException(ex.getMessage(), ex);
        	
        } 
    }

    public Resource getFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }
    
}