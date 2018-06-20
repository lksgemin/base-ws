package br.com.devgemin.base.ws.response;

import java.util.HashSet;
import java.util.Set;

import br.com.devgemin.base.ws.model.Role;
import br.com.devgemin.base.ws.model.User;

public class UserResponse {
    private Long id;
    private String username;
    private String name;
    private String email;
    private Set<Role> roles = new HashSet<>();
    private boolean active;

    public UserResponse() {
    	
    }
    
    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.active = user.isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
    
    
}
