package fhi360.covid.controller;

import fhi360.covid.enums.RoleName;
import fhi360.covid.exception.AppException;
import fhi360.covid.exception.CovidException;
import fhi360.covid.model.Role;
import fhi360.covid.model.User;
import fhi360.covid.payload.request.LoginRequest;
import fhi360.covid.payload.request.SignUpRequest;
import fhi360.covid.payload.response.ApiResponse;
import fhi360.covid.payload.response.JwtAuthenticationResponse;
import fhi360.covid.repository.RoleRepository;
import fhi360.covid.repository.UserRepository;
import fhi360.covid.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final String USER_ROLE_NOT_SET = "User role not set";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;



    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = userRepository.findByEmail(loginRequest.getUsernameOrEmail());
//		User user1 = userRepository.findUserByRoles();

        String jwt = jwtTokenProvider.generateToken(authentication);
        System.out.println("Roles:: " + user.getRoles().toString());

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, user));
    }

    @PostMapping("/signup")
	public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//		if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
//			throw new CovidException(HttpStatus.BAD_REQUEST, "Username is already taken");
//		}

		if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
			throw new CovidException(HttpStatus.BAD_REQUEST, "Email is already taken");
		}
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getEmail()))) {
            throw new CovidException(HttpStatus.BAD_REQUEST, "Phone is already taken");
        }

//		String firstName = signUpRequest.getFirstName().toLowerCase();
//
//		String lastName = signUpRequest.getLastName().toLowerCase();
//
//		String username = signUpRequest.getUsername().toLowerCase();

		String email = signUpRequest.getEmail().toLowerCase();
		String phone = signUpRequest.getPhone().toLowerCase();

		String password = passwordEncoder.encode(signUpRequest.getPassword());

		User user = new User(email, password, phone);

		List<Role> roles = new ArrayList<>();

		if (userRepository.count() == 0) {
//			roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER)
//					.orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
			roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN)
					.orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
		} else {
			roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER)
					.orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
		}
		user.setRoles(roles);

		User result = userRepository.save(user);


		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{userId}")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE,"User registered successfully"));
	}

}
