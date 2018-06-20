package br.com.devgemin.base.ws.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.devgemin.base.ws.request.UpdateUserRequest;
import br.com.devgemin.base.ws.response.SuccessResponse;
import br.com.devgemin.base.ws.response.UserIdentityAvailabilityResponse;
import br.com.devgemin.base.ws.response.UserProfileResponse;
import br.com.devgemin.base.ws.response.UserResponse;
import br.com.devgemin.base.ws.response.UserSummary;
import br.com.devgemin.base.ws.security.CurrentUser;
import br.com.devgemin.base.ws.security.UserPrincipal;
import br.com.devgemin.base.ws.service.user.UserService;
import br.com.devgemin.base.ws.service.user.update.UpdateUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/user")
public class UserController extends RestService {

	private UserService userService;
	private UpdateUserService updateUserService;
	
    public UserController(UserService userService, UpdateUserService updateUserService) {
		this.userService = userService;
		this.updateUserService = updateUserService;
	}

    @ApiOperation(value = "getCurrentUser", nickname = "getCurrentUser", notes = "Show current user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Current User", response = UserSummary.class)
    })
	@GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @ApiOperation(value = "checkUsernameAvailability", nickname = "checkUsernameAvailability", notes = "Check username availability")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Username Available", response = UserIdentityAvailabilityResponse.class)
    })
    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailabilityResponse checkUsernameAvailability(HttpServletRequest httpRequest, @RequestParam(value = "username") String username) {
        UserIdentityAvailabilityResponse response = userService.checkUsernameAvailability(username);
        loggerSupport.logSuccess(httpRequest);
        return response;
    }

    @ApiOperation(value = "checkEmailAvailability", nickname = "checkEmailAvailability", notes = "Check email user availability")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User email Available", response = UserIdentityAvailabilityResponse.class)
    })
    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailabilityResponse checkEmailAvailability(HttpServletRequest httpRequest, @RequestParam(value = "email") String email) {
    	UserIdentityAvailabilityResponse response = userService.checkEmailAvailability(email);
        loggerSupport.logSuccess(httpRequest);
        return response;
    }

    @ApiOperation(value = "getUserProfile", nickname = "getUserProfile", notes = "Get User Profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User profile founded", response = UserIdentityAvailabilityResponse.class)
    })
    @GetMapping("/{username}")
    public UserProfileResponse getUserProfile(HttpServletRequest httpRequest, @PathVariable(value = "username") String username) {
        UserProfileResponse userProfileResponse = userService.getUserProfile(username);
        loggerSupport.logSuccess(httpRequest);
        return userProfileResponse;
    }
    
    @ApiOperation(value = "getAllUsers", nickname = "getAllUsers", notes = "Get All Users")
	@ApiResponses({
			@ApiResponse(code = 200, message = "List users Response", response = UserResponse[].class)
	})
	@GetMapping(path="/all")
    @PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody List<UserResponse> getAllUsers(HttpServletRequest httpRequest) {
    	List<UserResponse> response = userService.findAll();
    	loggerSupport.logSuccess(httpRequest);
		return response;
	}

    @ApiOperation(value = "updateUser", nickname = "updateUser", notes = "Update an existing user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = SuccessResponse.class)
    })
    @PutMapping(path = "/{idUser}", consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody SuccessResponse update(HttpServletRequest httpRequest, @ApiParam(name = "idUser", value = "ID user", required = true) @PathVariable("idUser") String idUser, @ApiParam(name = "updateUser", value = "update user data", required = true) @RequestBody UpdateUserRequest request) {
        SuccessResponse response = updateUserService.update(idUser, request);
        loggerSupport.logSuccess(httpRequest);
        return response;
    }

}
