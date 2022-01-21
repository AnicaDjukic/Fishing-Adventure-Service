package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.*;
import isa.FishingAdventure.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import isa.FishingAdventure.model.User;
import isa.FishingAdventure.service.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "get")
	public @ResponseBody UserInfoDto getUserInfo(@RequestHeader("Authorization") String token) {
		return new UserInfoDto(userService.findByToken(token.split(" ")[1]));
	}

	@GetMapping(value = "getRole")
	public @ResponseBody String getRole(@RequestHeader("Authorization") String token) {
		return userService.getRoleFromToken(token.split(" ")[1]);
	}

	@GetMapping(value = "getRoleIfActivated")
	public @ResponseBody String getRoleIfActivated(@RequestHeader("Authorization") String token) {
		return userService.getRoleIfActivated(token.split(" ")[1]);
	}

	@PutMapping(value = "update")
	public @ResponseBody UserInfoDto update(@RequestBody UserInfoDto dto) {
		userService.save(updateUser(dto));
		return dto;
	}

	private User updateUser(UserInfoDto dto) {
		User user = userService.findByEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setBiography(dto.getBiography());
		user.setPhoneNumber(dto.getPhoneNumber());
		user.getAddress().setStreet(dto.getStreet());
		user.getAddress().setCity(dto.getCity());
		user.getAddress().setCountry(dto.getCountry());
		return user;
	}

	@PutMapping(value = "changePassword")
	public @ResponseBody ChangePasswordDto changePassword(@RequestHeader("Authorization") String token,
			@RequestBody ChangePasswordDto dto) {
		User user = userService.findByToken(token.split(" ")[1]);
		if (dto.getNewPassword().equals(dto.getPasswordAgain())) {
			user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
			user.setLastPasswordResetDate(Timestamp.valueOf(LocalDateTime.now()));
		}
		userService.save(user);
		return dto;
	}

	@GetMapping(value = "getAllUsers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<User> users = userService.findAllNotDeleted();
		return new ResponseEntity<>(createUserDtos(users), HttpStatus.OK);
	}

	private List<UserDto> createUserDtos(List<User> users) {
		List<UserDto> userDtos = new ArrayList<>();
		for (User user : users) {
			UserDto userDto = new UserDto(user.getEmail(), user.getName(), user.getSurname(), user.getUserType());
			userDtos.add(userDto);
		}
		return userDtos;
	}

	@GetMapping(value = "getAllRegistrationRequests")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<RegistrationRequestDto>> getAllRegistrationRequests() {
		List<User> users = userService.findAllUnactivatedAdvertisers();
		return new ResponseEntity<>(createRegistrationRequestDtos(users), HttpStatus.OK);
	}

	private List<RegistrationRequestDto> createRegistrationRequestDtos(List<User> users) {
		List<RegistrationRequestDto> requests = new ArrayList<>();
		for (User user : users) {
			RegistrationRequestDto request = new RegistrationRequestDto(user);
			requests.add(request);
		}
		return requests;
	}

	@GetMapping(value = "/rejectRegistrationRequest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> rejectRegistrationRequest(@RequestParam("email") String email,
			@RequestParam("reason") String reason) {
		userService.rejectRegistrationRequest(email, reason);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	@GetMapping(value = "/approveRegistrationRequest/{email}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> approveRegistrationRequest(@PathVariable String email) {
		userService.approveRegistrationRequest(email);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	@PutMapping(value = "/deleteUser")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteUser(@RequestBody String email) {
		userService.delete(email);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	@GetMapping(value = "/getUserPointsInfo")
	public ResponseEntity<UserPointsDto> getUserPointsInfo(@RequestHeader("Authorization") String token) {
		User user = userService.findByToken(token.split(" ")[1]);
		return new ResponseEntity<>(userService.getUserPointsInfo(user), HttpStatus.OK);
	}

	@GetMapping(value = "/hasPasswordBeenChanged")
	public ResponseEntity<Boolean> hasPasswordBeenChanged(@RequestHeader("Authorization") String token) {
		User user = userService.findByToken(token.split(" ")[1]);
		boolean isPasswordChanged = userService.hasPasswordBeenChanged(user.getEmail());
		return new ResponseEntity<>(isPasswordChanged, HttpStatus.OK);
	}

}
