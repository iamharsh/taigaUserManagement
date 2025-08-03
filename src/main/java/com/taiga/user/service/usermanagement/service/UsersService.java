package com.taiga.user.service.usermanagement.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.taiga.user.service.usermanagement.shared.UserDto;

public interface UsersService extends UserDetailsService{

	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailByEmail(String email);
}
