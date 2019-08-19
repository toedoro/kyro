package com.chowis.kyro.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.chowis.kyro.model.Device;
import com.chowis.kyro.repository.IDeviceRepository;
import com.chowis.kyro.repository.IRepository;

@Service
public class DeviceService extends AbstractService<Device, BigInteger>{

	@Autowired
	private IDeviceRepository deviceRepository;
	
	@Override
	protected IRepository<Device, BigInteger> getRepository() {
		return deviceRepository;
	}
	
}
