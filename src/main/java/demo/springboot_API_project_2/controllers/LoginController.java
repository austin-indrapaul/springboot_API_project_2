package demo.springboot_API_project_2.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.springboot_API_project_2.entities.Customer;
import demo.springboot_API_project_2.models.AuthenticationRequest;
import demo.springboot_API_project_2.models.AuthenticationResponse;
import demo.springboot_API_project_2.repositories.CustomerRepository;
import demo.springboot_API_project_2.security_configs.JwtUtil;
import demo.springboot_API_project_2.security_configs.MyUserDetailsService;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	CustomerRepository repo;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			final String jwt = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new AuthenticationResponse(jwt));

		} catch (BadCredentialsException ex) {
			throw new Exception("INVALID CREDENTIALS - ", ex);
		}
	}

	@GetMapping("/who")
	public ResponseEntity<?> who_logged_in(HttpServletRequest request) {

		final String authorizationHeader = request.getHeader("Authorization");

		String jwt = authorizationHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwt);

		Optional<Customer> customer = repo.findByUsername(username);

		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
	}

}
