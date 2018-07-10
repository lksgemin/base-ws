package br.com.devgemin.base.ws.service;

import java.util.List;
import java.util.Optional;

import br.com.devgemin.base.ws.model.BaseModel;
import br.com.devgemin.base.ws.response.SuccessResponse;

public interface BaseService<T extends BaseModel> {

	List<T> findAll();

	Optional<T> findOne(Long modelId);

	SuccessResponse remove(Long modelId);

	SuccessResponse save(T model);

	SuccessResponse update(T model);

	Class<T> getPersistentClass();

}
