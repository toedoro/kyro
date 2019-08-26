package com.chowis.kyro.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chowis.kyro.exception.FileStorageException;
import com.chowis.kyro.message.KyroResponse;
import com.chowis.kyro.message.UploadFileResponse;
import com.chowis.kyro.model.Content;
import com.chowis.kyro.service.ContentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/system/content", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public class ContentController {

	private static final Logger logger = LoggerFactory.getLogger(ContentController.class);
	
	@Autowired
	private ContentService contentService;
	
	@PostMapping
	public ResponseEntity<KyroResponse> create(@RequestBody Content body) {
		try {
			Content content = contentService.create(body);

			String message = "Content Succesfully created!";
			return ResponseEntity.status(HttpStatus.CREATED).body(KyroResponse.of(message).setSequence(content.getSequence()));
		} catch (Exception ex) {
			logger.debug(ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(ex.getMessage()));
		}
	}

	@GetMapping
	public ResponseEntity<Collection<Content>> read() {
		Collection<Content> contents = contentService.read();

		return ResponseEntity.status(HttpStatus.OK).body(contents);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Content> read(@PathVariable BigInteger id) {
		Content content = contentService.read(id);

		return ResponseEntity.status(HttpStatus.OK).body(content);
	}

	@PutMapping("/{id}")
	public ResponseEntity<KyroResponse> update(@PathVariable BigInteger id, @RequestBody Content content) {
		try {
			content.setSequence(id);
			contentService.create(content);

			String message = "Content Succesfully updated!";
			
			return ResponseEntity.status(HttpStatus.OK).body(KyroResponse.of(message));
		} catch (Exception ex) {
			logger.debug(ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(ex.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<KyroResponse> delete(@PathVariable BigInteger id) {
		try {
			contentService.delete(id);
			String message = "Content Succesfully deleted!";
			
			return ResponseEntity.status(HttpStatus.OK).body(KyroResponse.of(message));
		} catch (Exception ex) {
			String message = String.format("Content not found %s", id);
			logger.debug(ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(message));
		}
	}
	
	@PutMapping("/{id}/file")
	public ResponseEntity<KyroResponse> updateContentWithFile(@PathVariable BigInteger id, @RequestParam("file") MultipartFile file) {
		try {
			KyroResponse uploadFileResponse = _updateWithFile(id, file);
			
			return ResponseEntity
						.status(HttpStatus.OK)
						.body(uploadFileResponse);
		} catch (Exception ex) {
			logger.debug(ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(ex.getMessage()));
		}
	}
	
	@PutMapping("/{id}/files")
	public ResponseEntity<List<KyroResponse>> updateWithFiles(@PathVariable BigInteger id, @RequestParam("file") MultipartFile[] files) {
		List<KyroResponse> list = Arrays.asList(files)
                .stream()
                .map(file -> {
					try {
						return _updateWithFile(id, file);
					} catch (FileStorageException e) {
						logger.error(e.getMessage());
						return null;
					}
				}).filter(Objects::nonNull)
                .collect(Collectors.toList());
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(list);
	}
	
	private KyroResponse _updateWithFile(BigInteger id, MultipartFile file) throws FileStorageException {
		return contentService.updateContentWithFile(id, file);
	}
	
    @GetMapping("/{id}/file/{fileName:.+}")
    public ResponseEntity<Resource> getFileFromContentAsResource(@PathVariable BigInteger id, @PathVariable String fileName, HttpServletRequest request) {
        Resource resource = contentService.getFileFromContentAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.debug("Could not determine file type.");
        }
        
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", resource.getFilename()))
                .body(resource);
    }
	
}
