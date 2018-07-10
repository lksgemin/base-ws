package br.com.devgemin.base.ws.service.impl;

import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_DELETE_SUCCESS;
import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_SAVE_SUCCESS;
import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_UPDATE_SUCCESS;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.devgemin.base.ws.exception.baseexception.notfound.GlobalRegisterNotFoundException;
import br.com.devgemin.base.ws.model.BaseModel;
import br.com.devgemin.base.ws.repository.BaseRepository;
import br.com.devgemin.base.ws.response.SuccessResponse;
import br.com.devgemin.base.ws.service.BaseService;
import br.com.devgemin.base.ws.util.I18N;

public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

	@Autowired
	private BaseRepository<T> repository;
	
	private Class<T> persistentClass;
	
	@Autowired
	private I18N i18n;
	
	@SuppressWarnings("unchecked")  
	 public BaseServiceImpl() {  
	  this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())  
	    .getActualTypeArguments()[0];  
	 } 
	
	@Override
	public SuccessResponse remove(Long id) {

		Optional<T> model = repository.findById(id);

		if (model.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new GlobalRegisterNotFoundException();
		}
		
		return new SuccessResponse(GLOBAL_DELETE_SUCCESS.getCode(), i18n.get(GLOBAL_DELETE_SUCCESS));
	} 
	  
	@Override
	public SuccessResponse save(T model) {
		repository.save(model);
		
		return new SuccessResponse(GLOBAL_SAVE_SUCCESS.getCode(), i18n.get(GLOBAL_SAVE_SUCCESS));
	}

	@Override
	public SuccessResponse update(T model) {
		repository.save(model);
		
		return new SuccessResponse(GLOBAL_UPDATE_SUCCESS.getCode(), i18n.get(GLOBAL_UPDATE_SUCCESS));
	}

	@Override
	public List<T> findAll() {
		List<T> list = repository.findAll();
		if(list == null || list.size() <= 0)
			throw new GlobalRegisterNotFoundException();
		
		return list;
	}
	
	@Override
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public Optional<T> findOne(Long id) {
		Optional<T> model = repository.findById(id);
		if(!model.isPresent()) 
			throw new GlobalRegisterNotFoundException();
		
		return model;
	}
}
