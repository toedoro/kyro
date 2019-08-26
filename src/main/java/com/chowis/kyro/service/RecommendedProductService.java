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
import com.chowis.kyro.message.KyroResponse;
import com.chowis.kyro.message.UploadFileResponse;
import com.chowis.kyro.model.Content;
import com.chowis.kyro.model.ContentType;
import com.chowis.kyro.model.Device;
import com.chowis.kyro.model.ProductMode;
import com.chowis.kyro.model.RecommendedProduct;
import com.chowis.kyro.repository.IRecommendedProductRepository;
import com.chowis.kyro.repository.IRepository;
import com.chowis.kyro.util.DateUtil;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	
	public List<KyroResponse> updateRecommendedProductWithFile(Map<String, Optional<MultipartFile>> files) throws FileStorageException {
		List<KyroResponse> list = new ArrayList<>();
		files.entrySet().stream().forEach(entry -> {
			ProductMode productMode = ProductMode.getContentType(entry.getKey());
			entry.getValue().ifPresent(file -> {
				UploadFileResponse uploadFileResponse;
				try {
					uploadFileResponse = updateRecommendedProductWithFile(productMode, file);
					list.add(uploadFileResponse);
				} catch (FileStorageException e) {
					e.printStackTrace();
				}
			});
		});
		
		return list;
	}
	
	private UploadFileResponse updateRecommendedProductWithFile(ProductMode productMode, MultipartFile file) throws FileStorageException {
		String rawFileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (rawFileName.contains("..")) {
				throw new FileStorageException(String.format("Sorry! Filename contains invalid path sequence %s", rawFileName));
			}
			String date = DateUtil.formatDate(new Date(), "YYYYMMddhhmmss");
			String fileName = String.format("rp_%s_%s", date, rawFileName);
			
			RecommendedProduct recommendedProduct = new RecommendedProduct();
			recommendedProduct.setFileName(fileName);
			recommendedProduct.setMode(productMode.getValue());
			super.create(recommendedProduct);

			Path targetLocation = fileStorageLocation.resolve(fileName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			String fileUri = String.format("/api/system/recommended-product/file/%s", fileName);
        	String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(fileUri).toUriString();
        	
			return new UploadFileResponse(fileName, fileUrl, file.getContentType(), file.getSize());
		} catch (IOException ex) {
			String message = String.format("Could not store file %s. Please try again!", rawFileName);
			throw new FileStorageException(message, ex);
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.getMessage(), ex);
		}
    }
	
	public Resource getFileFromRecommendedProductAsResource(String fileName) {
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