package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.LoyaltyProgramDto;
import isa.FishingAdventure.service.UserCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "userCategory")
@CrossOrigin
public class UserCategoryController {
	
	@Autowired
	private UserCategoryService categoryService;

	@GetMapping(value = "/getLoyaltyProgram")
	public ResponseEntity<LoyaltyProgramDto> getLoyaltyProgram() {
		return new ResponseEntity<>(categoryService.getLoyaltyProgramInfo(), HttpStatus.OK);
	}

	@PutMapping(value = "/updateLoyaltyProgram")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateLoyaltyProgram(@RequestBody LoyaltyProgramDto dto) {
		categoryService.updateReservationPointPercentages(dto.getClientPointsPercentage(), dto.getAdvertiserPointsPercentage());
		categoryService.updateCategoryPoints("SILVER_CLIENT", dto.getClientSilverPoints());
		categoryService.updateCategoryPoints("GOLD_CLIENT", dto.getClientGoldPoints());
		categoryService.updateCategoryPoints("SILVER_ADVERTISER", dto.getAdvertiserSilverPoints());
		categoryService.updateCategoryPoints("GOLD_ADVERTISER", dto.getAdvertiserGoldPoints());
		categoryService.updateAdvertiserCuts(dto.getAdvertiserBaseCut());

		return new ResponseEntity("ok", HttpStatus.OK);
	}


}
