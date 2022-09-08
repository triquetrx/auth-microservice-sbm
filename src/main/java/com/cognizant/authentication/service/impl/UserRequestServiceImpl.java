package com.cognizant.authentication.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.authentication.dto.ConfirmPasswordDTO;
import com.cognizant.authentication.dto.ForgotPasswordDTO;
import com.cognizant.authentication.dto.NewUserDTO;
import com.cognizant.authentication.dto.PasswordChangeDTO;
import com.cognizant.authentication.exception.InvalidSecurityKey;
import com.cognizant.authentication.exception.PasswordNotAMatchException;
import com.cognizant.authentication.exception.UserNotFoundException;
import com.cognizant.authentication.model.Users;
import com.cognizant.authentication.repository.UserRepository;
import com.cognizant.authentication.service.UserRequestService;

@Service
public class UserRequestServiceImpl implements UserRequestService  {

	@Autowired
	UserRepository repository;

	@Override
	@Transactional
	public void newUser(NewUserDTO newUserDTO) {

		int id = (int) repository.count() + 1;
		repository.save(new Users(id, newUserDTO.getName(), newUserDTO.getEmail(),
				encoder().encode(newUserDTO.getPassword()), newUserDTO.getRole(), newUserDTO.getUserId(),true,encoder().encode(newUserDTO.getSecurityKey())));
	}
	
	@Override
	@Transactional
	public String changePassword(String username,PasswordChangeDTO passwordChangeDTO) throws PasswordNotAMatchException {
		Users users = repository.findByEmail(username).get();
		if(encoder().matches(passwordChangeDTO.getOldPassword(), users.getPassword())) {
			users.setPassword(encoder().encode(passwordChangeDTO.getNewPassword()));
			repository.save(users);
			return "CHANGED_PASSWORD_SUCCESSFULLY";
		} else {			
			throw new PasswordNotAMatchException();
		}
	}
	
	@Override
	@Transactional
	public boolean checkPassword(String username,ConfirmPasswordDTO dto) throws PasswordNotAMatchException {
		Users users = repository.findByEmail(username).get();
		if(encoder().matches(dto.getConfirmPassword(), users.getPassword())) {
			return true;
		} else {			
			throw new PasswordNotAMatchException();
		}
	}
	
	@Override
	@Transactional
	public String updateUser(NewUserDTO dto) {
		Users users = repository.findByEmail(dto.getEmail()).get();
		users.setName(dto.getName());
		users.setRoles(dto.getRole());
		users.setActive(true);
		repository.save(users);
		return "USER_UPDATED";
	}
	
	@Override
	@Transactional
	public String forgotPassword(ForgotPasswordDTO dto) throws InvalidSecurityKey, UserNotFoundException {
		Optional<Users> user = repository.findByEmail(dto.getEmail());
		if(user.isPresent()) {
			if(encoder().matches(dto.getSecurityKey(), user.get().getSecurityKey())) {
				user.get().setPassword(encoder().encode(dto.getNewPassword()));
				repository.save(user.get());
				return "Password changed for "+user.get().getEmail();
			}
			throw new InvalidSecurityKey("Invalid Ssecurity Key");
		} else {
			throw new UserNotFoundException("USER_NOT_PRESENT");
		}
	}

	private PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
}
