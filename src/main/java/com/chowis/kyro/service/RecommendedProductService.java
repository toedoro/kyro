package com.chowis.kyro.service;

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
import com.chowis.kyro.message.UploadFileResponse;
import com.chowis.kyro.model.Device;
import com.chowis.kyro.model.RecommendedProduct;
import com.chowis.kyro.repository.IRecommendedProductRepository;
import com.chowis.kyro.repository.IRepository;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class RecommendedProductService extends AbstractService<RecommendedProduct, BigInteger> {

	private final Path fileStorageLocation;

	@Autowired
	private IRecommendedProductRepository recommendedProductRepository;

	@Override
	protected IRepository<RecommendedProduct, BigInteger> getRepository() {
		return recommendedProductRepository;
	}

	@Autowired
	public RecommendedProductService(FileStorageProperties fileStorageProperties) throws FileStorageException {
		try {
			fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
			Files.createDirectories(fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public UploadFileResponse updateWithFile(BigInteger id, MultipartFile file) throws FileStorageException {
    	RecommendedProduct recommendedProduct = super.read(id);
        try {
        	if(recommendedProduct == null){
        		String message = String.format("File not found! %s", id);
        		throw new FileNotFoundException(message);
        	}
        	
        	String fileUri = String.format("/api/system/recommended-product/%s/file/", String.valueOf(recommendedProduct.getId()));
        	String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(fileUri).toUriString();
        	
            recommendedProduct.setData(file.getBytes());
            recommendedProduct.setFileType(file.getContentType());
            recommendedProduct.setFileUrl(fileUrl);
            super.update(recommendedProduct);
            
            return new UploadFileResponse(recommendedProduct.getFileName(), fileUrl, file.getContentType(), file.getSize());
        } catch (IOException ex) {
        	String message = String.format("Could not store file %s. Please try again!", id);
            throw new FileStorageException(message, ex);
        }
    }

	@Deprecated
	public UploadFileResponse storeFileToDisk(MultipartFile file) throws FileStorageException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			String fileDownloadUri = ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/api/system/recommended-product/file/")
					.path(fileName).toUriString();

			return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
		} catch (IOException ex) {
			String message = String.format("Could not store file %s. Please try again!", fileName);
			throw new FileStorageException(message, ex);
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.getMessage(), ex);

		}
	}

	@Deprecated
	public Resource getFileAsResourceFromDisk(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
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