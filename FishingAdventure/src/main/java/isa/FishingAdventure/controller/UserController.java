package isa.FishingAdventure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.dto.UserInfoDto;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.service.EmailService;
import isa.FishingAdventure.service.UserService;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/signup/async")
	public String signUpAsync(@RequestBody UserDto user) {
		// slanje emaila
		System.out.println("Ovde");
		try {
			System.out.println(user.getEmail());
			// System.out.println(user.toString());
			System.out.println("Thread id: " + Thread.currentThread().getId());
			emailService.sendNotificaitionAsync(user);
		} catch (Exception e) {
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}

		return "success";
	}
	
	@RequestMapping(value="get", method = RequestMethod.GET)
	public @ResponseBody UserInfoDto getItem(@RequestParam("email") String email){

	    User user = userService.findByEmail(email);
	    UserInfoDto userInfoDto = new UserInfoDto(user);
	    
	    return userInfoDto;
	}
	
	
	@RequestMapping(value="update", method = RequestMethod.PUT)
	public @ResponseBody UserInfoDto update(@RequestBody UserInfoDto dto) {
		
		System.out.println(dto.getEmail());
		
		
		User user = userService.findByEmail(dto.getEmail());

		user.setName(dto.getName()); 
		user.setSurname(dto.getSurname());
		user.setPhoneNumber(dto.getPhoneNumber());
		user.getAddress().setStreet(dto.getStreet());
		user.getAddress().setCity(dto.getCity());
		user.getAddress().setCountry(dto.getCountry());
		
		userService.save(user);

		return dto;
	}

}
