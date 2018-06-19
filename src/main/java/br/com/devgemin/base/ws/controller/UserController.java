package br.com.devgemin.base.ws.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.devgemin.base.ws.response.UserIdentityAvailabilityResponse;
import br.com.devgemin.base.ws.response.UserProfileResponse;
import br.com.devgemin.base.ws.response.UserSummary;
import br.com.devgemin.base.ws.security.CurrentUser;
import br.com.devgemin.base.ws.security.UserPrincipal;
import br.com.devgemin.base.ws.service.user.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/user")
public class UserController extends RestService {

	private UserService userService;
	
    public UserController(UserService userService) {
		this.userService = userService;
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


}
