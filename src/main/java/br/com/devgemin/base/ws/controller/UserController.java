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

@RestController
@RequestMapping("/api/user")
public class UserController extends RestService {

	private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailabilityResponse checkUsernameAvailability(HttpServletRequest httpRequest, @RequestParam(value = "username") String username) {
        UserIdentityAvailabilityResponse response = userService.checkUsernameAvailability(username);
        loggerSupport.logSuccess(httpRequest);
        return response;
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailabilityResponse checkEmailAvailability(HttpServletRequest httpRequest, @RequestParam(value = "email") String email) {
    	UserIdentityAvailabilityResponse response = userService.checkEmailAvailability(email);
        loggerSupport.logSuccess(httpRequest);
        return response;
    }

    @GetMapping("/{username}")
    public UserProfileResponse getUserProfile(HttpServletRequest httpRequest, @PathVariable(value = "username") String username) {
        UserProfileResponse userProfileResponse = userService.getUserProfile(username);
        loggerSupport.logSuccess(httpRequest);
        return userProfileResponse;
    }


}
