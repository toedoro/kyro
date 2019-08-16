package com.chowis.kyro.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.chowis.kyro.model.Content;
import com.chowis.kyro.model.Device;

@Repository
public interface IContentRepository extends IRepository<Content, BigInteger>{

}
