package com.chowis.kyro.repository;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.chowis.kyro.model.Content;
import com.chowis.kyro.model.Device;
import com.chowis.kyro.model.User;

@Repository
public interface IUserRepository extends IRepository<User, BigInteger>{

}
