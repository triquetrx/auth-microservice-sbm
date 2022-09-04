package com.cognizant.authentication.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.authentication.model.Users;
import com.cognizant.authentication.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> users = userRepo.findByEmail(username);
		users.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
		return users.map(LocalUserDetails::new).get();
	}

	@Transactional
	public String getName(String username) {
		Users users = userRepo.findByEmail(username).get();
		return users.getName();
	}

}
