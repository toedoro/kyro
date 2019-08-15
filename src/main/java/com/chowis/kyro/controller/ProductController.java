package com.chowis.kyro.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chowis.kyro.exception.FileStorageException;
import com.chowis.kyro.message.UploadFileResponse;
import com.chowis.kyro.service.ProductService;

import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/system/product")
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    @PostMapping("/file")
    public UploadFileResponse saveFile(@RequestParam("file") MultipartFile file) throws FileStorageException {
        String fileName = productService.saveFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/system/product/file/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/files")
    public List<UploadFileResponse> saveFiles(@RequestParam("file") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> {
					try {
						return saveFile(file);
					} catch (FileStorageException e) {
						e.printStackTrace();
						return null;
					}
					
				}).filter(Objects::nonNull).collect(Collectors.toList());
    }
    
    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = productService.getFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    

}
