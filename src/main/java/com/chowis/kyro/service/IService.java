package com.chowis.kyro.service;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;

public interface IService<T, ID> {

	Integer offSet = 0;
	Integer limit = 1000;

	public T create(@NotNull T type);

	public Page<T> read(Integer offSet, Integer limit);

	public Collection<T> read();

	public T read(@NotNull ID id);

	public T update(@NotNull T type);

	public void delete(@NotNull ID id);

}
