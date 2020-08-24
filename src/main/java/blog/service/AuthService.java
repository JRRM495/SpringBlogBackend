package blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blog.dto.LoginRequest;
import blog.dto.RegRequest;
import blog.model.User;
import blog.repo.UserRepo;
import blog.security.JwtProvider;

@Service
public class AuthService {
	  	@Autowired
	    private UserRepo userRepository;
	   
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
	    private JwtProvider jwtProvider;

	    public void signup(RegRequest registerRequest) {
	        User user = new User();
	        user.setUserName(registerRequest.getUsername());
	        user.setEmail(registerRequest.getEmail());
	        user.setPassword(encodePassword(registerRequest.getPassword()));

	        userRepository.save(user);
	    }

	    private String encodePassword(String password) {
	        return passwordEncoder.encode(password);
	    }

	    public AuthenticationResponse login(LoginRequest loginRequest) {
	        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
	                loginRequest.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authenticate);
	        String authenticationToken = jwtProvider.generateToken(authenticate);
	        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
	    }

	    //full class name is used since we already have a user object model
	    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
	        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
	                getContext().getAuthentication().getPrincipal();
	        return Optional.of(principal);
	    }
	    
}
