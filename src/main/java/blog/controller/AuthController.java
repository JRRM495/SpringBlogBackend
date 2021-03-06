package blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blog.dto.LoginRequest;
import blog.dto.RegRequest;
import blog.service.AuthService;
import blog.service.AuthenticationResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired 
	private AuthService authService;
	
	  @PostMapping("/signup")
	    public ResponseEntity<HttpStatus> signup(@RequestBody RegRequest registerRequest) {
	        authService.signup(registerRequest);
	        return new ResponseEntity(HttpStatus.OK);
	    }
	  
	   @PostMapping("/login")
	    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
	        return authService.login(loginRequest);
	    }
	   
	   public void testMethod() {
		   
	   }

}
