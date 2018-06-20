package br.com.devgemin.base.ws.service.auth;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.devgemin.base.ws.exception.HandlerException;
import br.com.devgemin.base.ws.exception.baseexception.unauthorized.SignUpExistingUserEmailException;
import br.com.devgemin.base.ws.exception.baseexception.unauthorized.SignUpExistingUsernameException;
import br.com.devgemin.base.ws.model.Role;
import br.com.devgemin.base.ws.model.RoleName;
import br.com.devgemin.base.ws.model.User;
import br.com.devgemin.base.ws.repository.RoleRepository;
import br.com.devgemin.base.ws.repository.UserRepository;
import br.com.devgemin.base.ws.request.SignUpRequest;
import br.com.devgemin.base.ws.response.ApiResponse;

@Component
public class SignUp {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private RoleRepository roleRepository;

	public SignUp(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        System.out.println(signUpRequest.getUsername());
        System.out.println(signUpRequest.getEmail());
        
		if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new SignUpExistingUsernameException();
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
        	throw new SignUpExistingUserEmailException();
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.isActive());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new HandlerException("1", "User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
