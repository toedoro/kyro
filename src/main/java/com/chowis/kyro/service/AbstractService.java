package com.chowis.kyro.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.transaction.annotation.Transactional;

import com.chowis.kyro.exception.ResourceNotFoundException;
import com.chowis.kyro.repository.IRepository;

@Transactional
public abstract class AbstractService<T, ID extends Serializable> implements IService<T, ID> {

	protected abstract IRepository<T, ID> getRepository();

	@Override
	public T create(T type) {
		try {
			return getRepository().save(type);
		} catch (Exception ex) {
			throw new IllegalStateException(ex.getMessage(), ex);
		}
	}

	@Override
	public Page<T> read(Integer offSet, Integer limit) {
		return getRepository().findAll(getPageable(offSet, limit));
	}

	@Override
	public Collection<T> read() {
		List<T> list = getRepository().findAll();

		return list;
	}

	@Override
	public T read(ID id) {
		return getRepository()
					.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(String.format("Resource not found %s", id)));
	}

	@Override
	public T update(T type) {
		try {
			return getRepository().save(type);
		} catch (Exception ex) {
			throw new IllegalStateException(ex.getMessage(), ex);
		}
	}

	@Override
	public void delete(ID id) {
		try {
			getRepository().deleteById(id);
		} catch (Exception ex) {
			throw new IllegalStateException(ex.getMessage(), ex);
		}
	}

	protected Pageable getPageable(Integer offSet, Integer limit) {
		Sort sort = new Sort(Sort.Direction.DESC, "date_created");
		return pageable(offSet, limit, sort);
	}
	
	protected Pageable pageable(Integer offSet, Integer limit, Sort sort) {
		return PageRequest.of(offSet, limit, sort);
	}
}
