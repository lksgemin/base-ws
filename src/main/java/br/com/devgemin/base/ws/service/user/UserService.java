package br.com.devgemin.base.ws.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.exception.baseexception.notfound.UserNameNotFoundException;
import br.com.devgemin.base.ws.model.User;
import br.com.devgemin.base.ws.repository.UserRepository;
import br.com.devgemin.base.ws.response.UserIdentityAvailabilityResponse;
import br.com.devgemin.base.ws.response.UserProfileResponse;
import br.com.devgemin.base.ws.response.UserResponse;

@Component
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
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
	
	private List<UserResponse> newUserResponseList(List<User> users){
		List<UserResponse> usersResponse = new ArrayList<>();
		if(users != null) {
			for (User user : users)
				usersResponse.add(new UserResponse(user));
		}
		
		return usersResponse;
	}
	
}
