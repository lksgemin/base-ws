package br.com.devgemin.base.ws.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.devgemin.base.ws.response.RoleResponse;
import br.com.devgemin.base.ws.service.role.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/role")
public class RoleController extends RestService {

	private RoleService roleService;
	
    public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
    
    @ApiOperation(value = "getAllRoles", nickname = "getAllRoles", notes = "Get All Roles")
	@ApiResponses({
			@ApiResponse(code = 200, message = "List roles Response", response = RoleResponse[].class)
	})
	@GetMapping(path="/all")
	public @ResponseBody List<RoleResponse> getAllRoles() {
    	List<RoleResponse> response = roleService.findAll();
		return response;
	}


}
