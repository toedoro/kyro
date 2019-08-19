package com.chowis.kyro.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.chowis.kyro.model.Device;
import com.chowis.kyro.model.RecommendedProduct;

@Repository
public interface IRecommendedProductRepository extends IRepository<RecommendedProduct, BigInteger>{

}
