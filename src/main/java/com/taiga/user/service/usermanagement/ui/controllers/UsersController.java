package com.taiga.user.service.usermanagement.ui.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taiga.user.service.usermanagement.service.UsersService;
import com.taiga.user.service.usermanagement.shared.UserDto;
import com.taiga.user.service.usermanagement.ui.model.CreateUserRequestModel;
import com.taiga.user.service.usermanagement.ui.model.CreateUserResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Environment env;

	@Autowired
	private UsersService userService;

	@GetMapping("/status/check")
	public String status() {
		return "working on port " + env.getProperty("local.server.port");
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel users) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(users, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);

		CreateUserResponseModel response = modelMapper.map(createdUser, CreateUserResponseModel.class);

		return new ResponseEntity<CreateUserResponseModel>(response, HttpStatus.CREATED);

	}

}
