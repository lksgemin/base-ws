package br.com.devgemin.base.ws.service.user;

import br.com.devgemin.base.ws.exception.baseexception.notfound.UserNameNotFoundException;
import br.com.devgemin.base.ws.model.User;
import br.com.devgemin.base.ws.repository.UserRepository;
import br.com.devgemin.base.ws.response.UserIdentityAvailabilityResponse;
import br.com.devgemin.base.ws.response.UserProfileResponse;

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
}
