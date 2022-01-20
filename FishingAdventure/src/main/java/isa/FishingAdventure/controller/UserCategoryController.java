package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.LoyaltyProgramDto;
import isa.FishingAdventure.service.UserCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "userCategory")
@CrossOrigin
public class UserCategoryController {
	
	@Autowired
	private UserCategoryService categoryService;

	@GetMapping(value = "/getLoyaltyProgram")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<LoyaltyProgramDto> getLoyaltyProgram() {
		return new ResponseEntity<>(categoryService.getLoyaltyProgramInfo(), HttpStatus.OK);
	}
}
