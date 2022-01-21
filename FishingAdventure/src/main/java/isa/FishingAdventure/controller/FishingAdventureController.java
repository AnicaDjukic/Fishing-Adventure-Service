package isa.FishingAdventure.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import isa.FishingAdventure.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.service.FishingAdventureService;
import isa.FishingAdventure.service.FishingInstructorService;

@RestController
@Configurable
@CrossOrigin
@RequestMapping(value = "/fishingAdventure", produces = MediaType.APPLICATION_JSON_VALUE)
public class FishingAdventureController {

	@Autowired
	private FishingAdventureService adventureService;

	@Autowired
	private FishingInstructorService instructorService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<FishingAdventureDto>> getAllFishingAdventures() {
		List<FishingAdventure> fishingAdventures = adventureService.findAllNotDeleted();
		List<FishingAdventureDto> fishingAdventureDtos = createFishingAdventureDtos(fishingAdventures);
		return new ResponseEntity<>(fishingAdventureDtos, HttpStatus.OK);
	}

	private List<FishingAdventureDto> createFishingAdventureDtos(List<FishingAdventure> fishingAdventures) {
		List<FishingAdventureDto> fishingAdventureDtos = new ArrayList<>();
		for (FishingAdventure fa : fishingAdventures) {
			FishingAdventureDto dto = new FishingAdventureDto(fa);
			fishingAdventureDtos.add(dto);
		}
		return fishingAdventureDtos;
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<FishingAdventureDto>> getSearchedVFishingAdventures(
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end,
			@RequestParam("persons") int persons, @RequestParam("rating") double rating, @RequestParam("input") String input) {
		List<FishingAdventure> fishingAdventures = new ArrayList<>();
		for (FishingAdventure fa : adventureService.findAllAvailableAdventures(start, end, persons))
			if (fa.getRating() >= rating && (searchByAddress(fa, input) || fa.getFishingInstructor().getName().contains(input) || fa.getFishingInstructor().getSurname().contains(input)))
				fishingAdventures.add(fa);
		return new ResponseEntity<>(createFishingAdventureDtos(fishingAdventures), HttpStatus.OK);
	}

	private boolean searchByAddress(FishingAdventure fa, String input) {
		return fa.getName().contains(input) || fa.getLocation().getAddress().getStreet().contains(input) || fa.getLocation().getAddress().getCity().contains(input) || fa.getLocation().getAddress().getCountry().contains(input);
	}

	@Transactional
	@GetMapping(value = "/{id}")
	public ResponseEntity<NewAdventureDto> getById(@PathVariable String id) {
		FishingAdventure adventure = adventureService.getById(Integer.parseInt(id));
		return new ResponseEntity<>(new NewAdventureDto(adventure), HttpStatus.OK);
	}

	@GetMapping(value = "/allByUser")
	@PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
	public ResponseEntity<List<FishingAdventureDto>> getAllFishingAdventuresByEmail(
			@RequestHeader("Authorization") String token) {
		FishingInstructor instructor = instructorService.findByToken(token.split(" ")[1]);
		return new ResponseEntity<>(createFishingAdventureDtos(adventureService.findByFishingInstructor(instructor)),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getNamesByUser")
	@PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
	public ResponseEntity<List<ServiceNameDto>> getNamesByUser(@RequestHeader("Authorization") String token) {
		FishingInstructor instructor = instructorService.findByToken(token.split(" ")[1]);
		List<ServiceNameDto> adventures = createServiceNameDtos(instructor);
		return new ResponseEntity<>(adventures, HttpStatus.OK);
	}

	private List<ServiceNameDto> createServiceNameDtos(FishingInstructor instructor) {
		List<ServiceNameDto> adventures = new ArrayList<>();
		for (FishingAdventure adventure : adventureService.findByFishingInstructor(instructor)) {
			adventures.add(new ServiceNameDto(adventure));
		}
		return adventures;
	}

	@GetMapping(value = "/deleteAdventure/{id}")
	@PreAuthorize("hasAnyRole('ROLE_FISHING_INSTRUCTOR', 'ROLE_ADMIN')")
	public ResponseEntity<String> deleteVacationHome(@PathVariable String id) {
		adventureService.deleteById(Integer.parseInt(id));
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	@GetMapping(value = "/getServiceOffersById/{id}")
	@Transactional
	public ResponseEntity<List<AppointmentDto>> getServiceOffersById(@PathVariable String id) {
		FishingAdventure adventure = adventureService.getById(Integer.parseInt(id));
		return new ResponseEntity<>(adventureService.getAppointmentDtos(adventure), HttpStatus.OK);
	}

	@GetMapping(value = "/getDurationById/{id}")
	public ResponseEntity<Long> getDurationById(@PathVariable Integer id) {
		long duration = adventureService.findById(id).getAdventureDuration().toMinutes();
		return new ResponseEntity<>(duration, HttpStatus.OK);
	}

	@GetMapping(value = "/isInstructorAvailable")
	public ResponseEntity<Boolean> isInstructorAvailable(@RequestHeader("Authorization") String token,
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) {
		FishingInstructor instructor = instructorService.findByToken(token.split(" ")[1]);
		boolean available = adventureService.isInstructorAvailable(instructor, start, end);

		return new ResponseEntity<>(available, HttpStatus.OK);
	}

	@GetMapping(value = "/isAdventureInstructorAvailable")
	public ResponseEntity<Boolean> isAdventureInstructorAvailable(@RequestParam("id") Integer adventureId,
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) {
		FishingInstructor fishingInstructor = adventureService.findById(adventureId).getFishingInstructor();
		boolean available = adventureService.isInstructorAvailable(fishingInstructor, start, end);

		return new ResponseEntity<>(available, HttpStatus.OK);
	}

	@GetMapping(value = "/persons")
	public ResponseEntity<Integer> getAdventureMaxPersons(@RequestParam("id") Integer id) {
		int maxPersons = adventureService.getMaxPersons(id);
		return new ResponseEntity<>(maxPersons, HttpStatus.OK);
	}

}
