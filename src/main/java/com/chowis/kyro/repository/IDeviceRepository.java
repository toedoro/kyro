package com.chowis.kyro.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.chowis.kyro.model.Device;

@Repository
public interface IDeviceRepository extends IRepository<Device, BigInteger>{

}
