package br.com.devgemin.base.ws.service.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.model.Role;
import br.com.devgemin.base.ws.repository.RoleRepository;
import br.com.devgemin.base.ws.response.RoleResponse;

@Component
public class RoleService {

	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<RoleResponse> findAll() {
		List<Role> roles = roleRepository.findAll();

		return newRoleResponseList(roles);
	}
	
	private List<RoleResponse> newRoleResponseList(List<Role> roles){
		List<RoleResponse> rolesResponse = new ArrayList<>();
		if(rolesResponse != null) {
			for (Role role : roles)
				rolesResponse.add(new RoleResponse(role));
		}
		
		return rolesResponse;
	}
	
}
