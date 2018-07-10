package br.com.devgemin.base.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Bank")
@Table(name = "bank")
public class Bank extends BaseModel {


	private static final long serialVersionUID = 1L;

	@Column(name = "code")
	private Integer code;

	@Column(name = "name")
	private String name;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
