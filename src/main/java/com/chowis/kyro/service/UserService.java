package com.chowis.kyro.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chowis.kyro.model.User;
import com.chowis.kyro.repository.IRepository;
import com.chowis.kyro.repository.IUserRepository;

@Service
public class UserService extends AbstractService<User, BigInteger> {
	
	@Autowired
	private IUserRepository userRepository;
	
	
	@Override
	protected IRepository<User, BigInteger> getRepository() {
		return userRepository;
	}
	
}
