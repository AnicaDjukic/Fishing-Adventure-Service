package isa.FishingAdventure.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.FishingAdventure.dto.VacationHomeDto;
import isa.FishingAdventure.model.VacationHome;
import isa.FishingAdventure.service.VacationHomeService;

@RestController
@RequestMapping(value = "/vacationHome", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationHomeController{
	
	@Autowired
	private VacationHomeService  homeService;

	@GetMapping(value = "/all")
	//@PreAuthorize("hasRole('CLIENT')")
	public ResponseEntity<List<VacationHomeDto>> getAllVacationHomes() {
		List<VacationHome> vacationHomes = homeService.findAll();

		List<VacationHomeDto> VacationHomeDto = new ArrayList<>();
		for (VacationHome h : vacationHomes) {
			VacationHome home = (VacationHome)h;
			VacationHomeDto dto = new VacationHomeDto(home);
			VacationHomeDto.add(dto);
		}

		return new ResponseEntity<>(VacationHomeDto, HttpStatus.OK);
	}
}
