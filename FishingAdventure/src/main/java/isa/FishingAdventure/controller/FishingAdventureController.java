package isa.FishingAdventure.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.FishingAdventure.dto.FishingAdventureDto;
import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.service.FishingAdventureService;
import isa.FishingAdventure.service.FishingInstructorService;

@RestController
@Configurable
@RequestMapping(value = "fishingAdventure", produces = MediaType.APPLICATION_JSON_VALUE)
public class FishingAdventureController{
	
	@Autowired
	private FishingAdventureService adventureService;
	private FishingInstructorService instructorService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<FishingAdventureDto>> getAllFishingAdventures() {
		List<FishingAdventure> fishingAdventures = adventureService.findAll();

		List<FishingAdventureDto> fishingAdventureDtos = new ArrayList<FishingAdventureDto>();
		for (FishingAdventure fa: fishingAdventures) {
			FishingAdventureDto dto = new FishingAdventureDto(fa);
			fishingAdventureDtos.add(dto);
		}

		return new ResponseEntity<>(fishingAdventureDtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/{email}")
	@PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
	public ResponseEntity<List<FishingAdventure>> getAllFishingAdventuresByEmail(@PathVariable String email) {
		FishingInstructor instructor = instructorService.findByEmail(email);
		List<FishingAdventure> fishingAdventures = adventureService.findByFishingInstructor(instructor);
		return new ResponseEntity<>(fishingAdventures, HttpStatus.OK);
	}
}
