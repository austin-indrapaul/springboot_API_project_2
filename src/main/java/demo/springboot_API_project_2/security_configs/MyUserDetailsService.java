package demo.springboot_API_project_2.security_configs;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demo.springboot_API_project_2.entities.Customer;
import demo.springboot_API_project_2.repositories.CustomerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	CustomerRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Customer cust = repo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("invalid credentials"));
		return new org.springframework.security.core.userdetails.User(cust.getUsername(), cust.getPassword(),
				new ArrayList<>());
	}
}
