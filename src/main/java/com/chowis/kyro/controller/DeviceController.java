package com.chowis.kyro.controller;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
import com.chowis.kyro.model.Device;
import com.chowis.kyro.service.DeviceService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/system/device", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@PostMapping
	public ResponseEntity<KyroResponse> create(@RequestBody Device body) {
		try {
			Device device = deviceService.create(body);

			String message = "Device Succesfully created!";
			return ResponseEntity.status(HttpStatus.CREATED).body(KyroResponse.of(message).setId(device.getId()));
		} catch (Exception ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(ex.getMessage()));
		}
	}

	@GetMapping
	public ResponseEntity<Collection<Device>> read() {
		Collection<Device> devices = deviceService.read();

		return ResponseEntity.status(HttpStatus.OK).body(devices);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Device> read(@PathVariable BigInteger id) {
		Device device = deviceService.read(id);

		return ResponseEntity.status(HttpStatus.OK).body(device);
	}

	@PutMapping("/{id}")
	public ResponseEntity<KyroResponse> update(@PathVariable BigInteger id, @RequestBody Device device) {
		try {
			device.setId(id);
			deviceService.create(device);
			
			String message = "Device Succesfully updated!";
			return ResponseEntity.status(HttpStatus.OK).body(KyroResponse.of(message));
		} catch (Exception ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(ex.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<KyroResponse> delete(@PathVariable BigInteger id) {
		try {
			deviceService.delete(id);
			
			String message = "Device Succesfully deleted!";
			return ResponseEntity.status(HttpStatus.OK).body(KyroResponse.of(message));
		} catch (Exception ex) {
			String message = String.format("Device not found %s", id);
			KyroResponse response = new KyroResponse(message);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
}
