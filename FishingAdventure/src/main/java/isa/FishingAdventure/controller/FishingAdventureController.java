package isa.FishingAdventure.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.dto.NewAdventureDto;
import isa.FishingAdventure.dto.ServiceNameDto;
import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import isa.FishingAdventure.dto.FishingAdventureDto;
import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.service.FishingAdventureService;
import isa.FishingAdventure.service.FishingInstructorService;

@RestController
@Configurable
@CrossOrigin
@RequestMapping(value = "/fishingAdventure", produces = MediaType.APPLICATION_JSON_VALUE)
public class FishingAdventureController{

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private FishingAdventureService adventureService;

	@Autowired
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

	@Transactional
	@GetMapping(value = "/{id}")
	public ResponseEntity<NewAdventureDto> getById(@PathVariable String id) {
		FishingAdventure adventure = adventureService.getById(Integer.parseInt(id));
		NewAdventureDto dto = new NewAdventureDto(adventure);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allByUser")
	@PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
	public ResponseEntity<List<FishingAdventureDto>> getAllFishingAdventuresByEmail(@RequestHeader("Authorization") String token) {
		String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
		FishingInstructor instructor = instructorService.findByEmail(email);

		List<FishingAdventureDto> fishingAdventureDtos = new ArrayList<FishingAdventureDto>();
		for (FishingAdventure fa: adventureService.findByFishingInstructor(instructor)) {
			FishingAdventureDto dto = new FishingAdventureDto(fa);
			fishingAdventureDtos.add(dto);
		}
		return new ResponseEntity<>(fishingAdventureDtos, HttpStatus.OK);
	}

	@GetMapping(value = "/getNamesByUser")
	@PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
	public ResponseEntity<List<ServiceNameDto>> getNamesByUser(@RequestHeader("Authorization") String token) {
		String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
		FishingInstructor instructor = instructorService.findByEmail(email);

		List<ServiceNameDto> adventures = new ArrayList<ServiceNameDto>();
		for (FishingAdventure adventure : adventureService.findByFishingInstructor(instructor)) {
			adventures.add(new ServiceNameDto(adventure));
		}

		return new ResponseEntity<>(adventures, HttpStatus.OK);
	}

	@GetMapping(value = "/getServiceOffersById/{id}")
	@Transactional
	public ResponseEntity<List<AppointmentDto>> getServiceOffersById(@PathVariable String id) {
		FishingAdventure adventure = adventureService.getById(Integer.parseInt(id));
		return new ResponseEntity<>(adventureService.getAppointmentDtos(adventure), HttpStatus.OK);
	}

	@GetMapping(value = "/getDurationById/{id}")
	public ResponseEntity getDurationById(@PathVariable Integer id) {
		long duration = adventureService.findById(id).getAdventureDuration().toMinutes();
				return new ResponseEntity(duration, HttpStatus.OK);
	}

	@GetMapping(value = "/isInstructorAvailable")
	public ResponseEntity isInstructorAvailable(@RequestHeader("Authorization") String token, @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
										   @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) {
		String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
		boolean available = adventureService.isInstructorAvailable(instructorService.findByEmail(email), start, end);

		return new ResponseEntity(available, HttpStatus.OK);
	}

	@GetMapping(value = "/isAdventureInstructorAvailable")
	public ResponseEntity isAdventureInstructorAvailable(@RequestParam("id") Integer adventureId, @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
												@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) {
		FishingInstructor fishingInstructor = adventureService.findById(adventureId).getFishingInstructor();
		boolean available = adventureService.isInstructorAvailable(fishingInstructor, start, end);

		return new ResponseEntity(available, HttpStatus.OK);
	}

	@GetMapping(value = "/persons")
	public ResponseEntity getAdventureMaxPersons(@RequestParam("id") Integer id) {
		int maxPersons = adventureService.getMaxPersons(id);
		return new ResponseEntity(maxPersons, HttpStatus.OK);
	}
}
