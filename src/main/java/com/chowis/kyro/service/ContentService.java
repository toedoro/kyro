package com.chowis.kyro.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chowis.kyro.model.Content;
import com.chowis.kyro.repository.IContentRepository;
import com.chowis.kyro.repository.IRepository;

@Service
public class ContentService extends AbstractService<Content, BigInteger>{

	@Autowired
	private IContentRepository commentRepository;
	
	@Override
	@SuppressWarnings("unchecked")
	protected IRepository<Content, BigInteger> getRepository() {
		return (IRepository<Content, BigInteger>) commentRepository;
	}
	
}
