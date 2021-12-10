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
import isa.FishingAdventure.model.Boat;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.service.BoatService;

@RestController
@RequestMapping(value = "boat")
public class BoatController {
	
	@Autowired
	private BoatService boatService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<BoatDto>> getAllBoats() {
		List<Boat> boats = boatService.findAll();

		List<BoatDto> boatDtos = new ArrayList<BoatDto>();
		for (Boat b : boats) {
			BoatDto dto = new BoatDto(b);
			boatDtos.add(dto);
		}

		return new ResponseEntity<>(boatDtos, HttpStatus.OK);
	}
}
