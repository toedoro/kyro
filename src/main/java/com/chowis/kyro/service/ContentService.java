package com.chowis.kyro.service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chowis.kyro.config.FileStorageProperties;
import com.chowis.kyro.exception.FileNotFoundException;
import com.chowis.kyro.exception.FileStorageException;
import com.chowis.kyro.exception.ResourceNotFoundException;
import com.chowis.kyro.message.UploadFileResponse;
import com.chowis.kyro.model.Content;
import com.chowis.kyro.model.ContentType;
import com.chowis.kyro.model.Device;
import com.chowis.kyro.model.RecommendedProduct;
import com.chowis.kyro.repository.IContentRepository;
import com.chowis.kyro.repository.IRepository;
import com.chowis.kyro.util.DateUtil;

@Service
public class ContentService extends AbstractService<Content, BigInteger> {
	
	private Path fileStorageLocation;

	@Autowired
	private IContentRepository commentRepository;
	
	@Autowired
	private DeviceService deviceService;
	
	
	@Autowired
	public ContentService(FileStorageProperties fileStorageProperties) throws FileStorageException {
		try {
			fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
			Files.createDirectories(fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}
	
	@Override
	protected IRepository<Content, BigInteger> getRepository() {
		return commentRepository;
	}
	
	public Content create(BigInteger deviceId, Content content){
		Device device = deviceService.read(deviceId);
		if(device == null){
			throw new ResourceNotFoundException("Device not found!");
		}
		
		content.setDevice(device);
		
		return super.create(content);
	}
	
	public UploadFileResponse updateContentWithFile(BigInteger id, MultipartFile file) throws FileStorageException {
		Content content = super.read(id);
		String rawFileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (rawFileName.contains("..")) {
				throw new FileStorageException(String.format("Sorry! Filename contains invalid path sequence %s", rawFileName));
			}
			
			String date = DateUtil.formatDate(new Date(), "YYYYMMddhhmmss");
			String fileName = String.format("%s_%s_%s", "getUser()", date, rawFileName);
			content.setFileName(fileName);
			ContentType contentType = ContentType.getContentType(file.getContentType());
			content.setContentType(contentType.getValue());

			Path targetLocation = fileStorageLocation.resolve(fileName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			String fileUri = String.format("/api/system/recommended-product/%s/file/%s", content.getId(), fileName);
        	String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(fileUri).toUriString();
        	
			return new UploadFileResponse(fileName, fileUrl, file.getContentType(), file.getSize());
		} catch (IOException ex) {
			String message = String.format("Could not store file %s. Please try again!", rawFileName);
			throw new FileStorageException(message, ex);
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.getMessage(), ex);
		}
    }
	
	public Resource getFileFromContentAsResource(String fileName) {
		try {
			Path filePath = fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				String message = String.format("Could not store file %s. Please try again!", fileName);
				throw new FileNotFoundException(message);
			}
		} catch (MalformedURLException ex) {
			String message = String.format("File not found %s", fileName);
			throw new FileNotFoundException(message, ex);
		}
	}
	
}
