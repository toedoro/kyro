package com.chowis.kyro.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.chowis.kyro.model.ProductMode;
import com.chowis.kyro.model.RecommendedProduct;
import com.chowis.kyro.service.RecommendedProductService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/system/recommended-product")
public class RecommendedProductController {
	
	@Autowired
    private RecommendedProductService recommendedProductService;
	
	private static final Logger logger = LoggerFactory.getLogger(RecommendedProductController.class);
	
	@PostMapping
	public ResponseEntity<KyroResponse> create(@RequestBody RecommendedProduct body) {
		try {
			RecommendedProduct recommendedProduct = recommendedProductService.create(body);
			String message = "Recommended Product Succesfully created!";
			
			return ResponseEntity.status(HttpStatus.CREATED).body(KyroResponse.of(message).setId(recommendedProduct.getId()));
		} catch (Exception ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(ex.getMessage()));
		}
	}

	@GetMapping
	public ResponseEntity<Collection<RecommendedProduct>> read() {
		Collection<RecommendedProduct> recommendedProducts = recommendedProductService.read();

		return ResponseEntity.status(HttpStatus.OK).body(recommendedProducts);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RecommendedProduct> read(@PathVariable BigInteger id) {
		RecommendedProduct recommendedProduct = recommendedProductService.read(id);

		return ResponseEntity.status(HttpStatus.OK).body(recommendedProduct);
	}

	@PutMapping("/{id}")
	public ResponseEntity<KyroResponse> update(@PathVariable BigInteger id, @RequestBody RecommendedProduct recommendedProduct) {
		try {
			recommendedProduct.setId(id);
			recommendedProductService.create(recommendedProduct);
			
			String message = "Recommended Product Succesfully updated!";
			return ResponseEntity.status(HttpStatus.OK).body(KyroResponse.of(message));
		} catch (Exception ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(ex.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<KyroResponse> delete(@PathVariable BigInteger id) {
		try {
			recommendedProductService.delete(id);
			
			String message = "Recommended Product Succesfully deleted!";
			return ResponseEntity.status(HttpStatus.OK).body(KyroResponse.of(message));
		} catch (Exception ex) {
			String message = String.format("Recommended Product not found %s", id);
			KyroResponse response = new KyroResponse(message);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@PostMapping("/files")
	public ResponseEntity<List<KyroResponse>> updateWithFiles(
			@RequestParam(value="oily") Optional<MultipartFile> oily,
			@RequestParam(value="good") Optional<MultipartFile> good,
			@RequestParam(value="dry") Optional<MultipartFile> dry,
			@RequestParam(value="complexion") Optional<MultipartFile> complexion,
			@RequestParam(value="wrinkles") Optional<MultipartFile> wrinkles,
			@RequestParam(value="impurities") Optional<MultipartFile> impurities,
			@RequestParam(value="keratin") Optional<MultipartFile> keratin,
			@RequestParam(value="moisture") Optional<MultipartFile> moisture,
			@RequestParam(value="pores") Optional<MultipartFile> pores,
			@RequestParam(value="spots") Optional<MultipartFile> spots) throws FileStorageException {
		
		Map<String, Optional<MultipartFile>> files = new HashMap<>();
		files.put("oily", oily);
		files.put("good", good);
		files.put("dry", dry);
		files.put("complexion", complexion);
		files.put("wrinkles", wrinkles);
		files.put("impurities", impurities);
		files.put("keratin", keratin);
		files.put("moisture", moisture);
		files.put("pores", pores);
		files.put("spots", spots);
		
		List<KyroResponse> list = recommendedProductService.updateRecommendedProductWithFile(files);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(list);

	}
	
    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<Resource> getFileFromRecommendedProductAsResource(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = recommendedProductService.getFileFromRecommendedProductAsResource(fileName);

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
