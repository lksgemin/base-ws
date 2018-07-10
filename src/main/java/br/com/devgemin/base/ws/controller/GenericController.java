package br.com.devgemin.base.ws.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.devgemin.base.ws.model.BaseModel;
import br.com.devgemin.base.ws.response.SuccessResponse;
import br.com.devgemin.base.ws.service.BaseService;

public class GenericController<T extends BaseModel> extends RestService {
	
	@Autowired
	private BaseService<T> service;
	
	@GetMapping(path = "/all", consumes = APPLICATION_JSON_VALUE)
	public List<T> list(HttpServletRequest httpRequest) {
		List<T> list = service.findAll();
		loggerSupport.logSuccess(httpRequest);
		return list;
	}
	
	@PostMapping(path = "/save", consumes = APPLICATION_JSON_VALUE)
	public SuccessResponse create(HttpServletRequest httpRequest, @RequestBody T entity) {
		SuccessResponse response = service.save(entity);
		loggerSupport.logSuccess(httpRequest);
		return response;
	}
	
	@PutMapping(path = "/update/{id}", consumes = APPLICATION_JSON_VALUE)
	public SuccessResponse update(HttpServletRequest httpRequest, @PathVariable(value = "id") Long id, @RequestBody T entity) {
		SuccessResponse response = service.save(entity);
		loggerSupport.logSuccess(httpRequest);
		return response;
	}
	@DeleteMapping(path = "/delete/{id}", consumes = APPLICATION_JSON_VALUE)
	public SuccessResponse delete(HttpServletRequest httpRequest, @PathVariable(value = "id") Long id) {
		SuccessResponse response = service.remove(id);
		loggerSupport.logSuccess(httpRequest);
		return response;
	}
	
	@GetMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
	public Optional<T> get(HttpServletRequest httpRequest, @PathVariable(value = "id") Long id) {
		return service.findOne(id);
	}
}
