package br.com.devgemin.base.ws.response;

import br.com.devgemin.base.ws.model.Role;
import br.com.devgemin.base.ws.model.RoleName;

public class RoleResponse {
    private Long id;
    private RoleName name;

    public RoleResponse() {
    	
    }
    
    public RoleResponse(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}

}
