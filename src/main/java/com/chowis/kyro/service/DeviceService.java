package com.chowis.kyro.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chowis.kyro.model.Device;
import com.chowis.kyro.repository.IDeviceRepository;
import com.chowis.kyro.repository.IRepository;

@Service
public class DeviceService extends AbstractService<Device, BigInteger>{

	@Autowired
	private IDeviceRepository deviceRepository;
	
	@Override
	@SuppressWarnings("unchecked")
	protected IRepository<Device, BigInteger> getRepository() {
		return (IRepository<Device, BigInteger>) deviceRepository;
	}
	
}
