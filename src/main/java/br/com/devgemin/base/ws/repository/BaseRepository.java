package br.com.devgemin.base.ws.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devgemin.base.ws.model.BaseModel;

@Repository
public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, Serializable> {
	
}
