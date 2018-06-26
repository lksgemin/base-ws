package br.com.devgemin.base.ws.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.exception.baseexception.notfound.UserIdNotFoundException;
import br.com.devgemin.base.ws.exception.baseexception.notfound.UserNameNotFoundException;
import br.com.devgemin.base.ws.model.User;
import br.com.devgemin.base.ws.repository.UserRepository;
import br.com.devgemin.base.ws.response.UserIdentityAvailabilityResponse;
import br.com.devgemin.base.ws.response.UserProfileResponse;
import br.com.devgemin.base.ws.response.UserResponse;
import br.com.devgemin.base.ws.response.UserSummary;
import br.com.devgemin.base.ws.security.UserPrincipal;
import br.com.devgemin.base.ws.util.Validator;

@Component
public class UserService {

	private UserRepository userRepository;
	
	private Validator validator;

	public UserService(UserRepository userRepository, Validator validator) {
		this.userRepository = userRepository;
		this.validator = validator;
	}
	
	public UserSummary getCurrentUser(UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), verifyIsAdminUser(currentUser));
        return userSummary;
    }
	
	public UserProfileResponse getUserProfile(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null){
        		throw new UserNameNotFoundException();
        }

        UserProfileResponse userProfileResponse = new UserProfileResponse(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());

        return userProfileResponse;
    }
	
	public UserIdentityAvailabilityResponse checkUsernameAvailability(String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailabilityResponse(isAvailable);
    }
	
	public UserIdentityAvailabilityResponse checkEmailAvailability(String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailabilityResponse(isAvailable);
    }
	
	public List<UserResponse> findAll() {
		List<User> users = userRepository.findAll();

		return newUserResponseList(users);
	}
	
	public UserResponse getUserById(String id) {
		validator.idUserEmpty(id);
		validator.idUserInvalid(id);

		User user = userRepository.findById(Long.valueOf(id)).orElseThrow(
	             () -> new UserIdNotFoundException()
	    );
	
		UserResponse userResponse = new UserResponse(user);
        return userResponse;
    }
	
	private boolean verifyIsAdminUser(UserPrincipal currentUser) {
		boolean isAdmin = currentUser.getAuthorities().stream().anyMatch( role-> 
			role.getAuthority().equals("ROLE_ADMIN"));
		
		return isAdmin;
	}
	
	private List<UserResponse> newUserResponseList(List<User> users){
		List<UserResponse> usersResponse = new ArrayList<>();
		if(users != null) {
			for (User user : users)
				usersResponse.add(new UserResponse(user));
		}
		
		return usersResponse;
	}
	
}
