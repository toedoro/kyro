package com.chowis.kyro.controller;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.chowis.kyro.message.KyroResponse;
import com.chowis.kyro.model.Content;
import com.chowis.kyro.service.ContentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/system/content", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public class ContentController {

	@Autowired
	private ContentService contentService;

	@PostMapping
	public ResponseEntity<KyroResponse> create(@RequestBody Content content) {
		try {
			contentService.create(content);

			String message = "Content Succesfully created!";
			return ResponseEntity.status(HttpStatus.CREATED).body(KyroResponse.of(message));
		} catch (Exception ex) {
			
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
			content.setId(id);
			contentService.create(content);

			String message = "Content Succesfully updated!";
			
			return ResponseEntity.status(HttpStatus.OK).body(KyroResponse.of(message));
		} catch (Exception ex) {
			
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
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(message));
		}
	}
	
}
