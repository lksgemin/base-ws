package br.com.devgemin.base.ws.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devgemin.base.ws.request.LoginRequest;
import br.com.devgemin.base.ws.request.SignUpRequest;
import br.com.devgemin.base.ws.service.auth.SignIn;
import br.com.devgemin.base.ws.service.auth.SignUp;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends RestService {

	private SignIn signIn;
	private SignUp signUp;
	
	public AuthController(SignIn sigIn, SignUp signUp) {
		this.signIn = sigIn;
		this.signUp = signUp;
	}
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(HttpServletRequest httpRequest, @Valid @RequestBody LoginRequest loginRequest) {

    	ResponseEntity<?> response = signIn.authenticateUser(loginRequest);
    	loggerSupport.logSuccess(httpRequest);
        return response;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(HttpServletRequest httpRequest, @Valid @RequestBody SignUpRequest signUpRequest) {
    	ResponseEntity<?> response = signUp.registerUser(signUpRequest);
    	loggerSupport.logSuccess(httpRequest);
        return response;
    }
}
