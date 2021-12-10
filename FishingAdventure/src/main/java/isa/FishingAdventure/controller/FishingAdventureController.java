package isa.FishingAdventure.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.FishingAdventure.dto.BoatDto;
import isa.FishingAdventure.dto.FishingAdventureDto;
import isa.FishingAdventure.model.Boat;
import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.service.FishingAdventureService;

@RestController
@RequestMapping(value = "fishingAdventure")
public class FishingAdventureController{
	
	@Autowired
	private FishingAdventureService adventureService;
	
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
}
