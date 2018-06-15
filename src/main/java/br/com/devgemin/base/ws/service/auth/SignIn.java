package br.com.devgemin.base.ws.service.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.request.LoginRequest;
import br.com.devgemin.base.ws.response.JwtAuthenticationResponse;
import br.com.devgemin.base.ws.security.JwtTokenProvider;

@Component
public class SignIn {
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider tokenProvider;

	public SignIn(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
	}

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}
