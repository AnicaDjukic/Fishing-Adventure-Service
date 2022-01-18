package isa.FishingAdventure.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import isa.FishingAdventure.dto.*;
import isa.FishingAdventure.model.VacationHome;
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
		List<FishingAdventure> fishingAdventures = adventureService.findAll();
		List<FishingAdventureDto> fishingAdventureDtos = createFishingAdventureDtos(fishingAdventures);
		return new ResponseEntity<>(fishingAdventureDtos, HttpStatus.OK);
	}

	private List<FishingAdventureDto> createFishingAdventureDtos(List<FishingAdventure> fishingAdventures) {
		List<FishingAdventureDto> fishingAdventureDtos = new ArrayList<FishingAdventureDto>();
		for (FishingAdventure fa : fishingAdventures) {
			FishingAdventureDto dto = new FishingAdventureDto(fa);
			fishingAdventureDtos.add(dto);
		}
		return fishingAdventureDtos;
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<VacationHomeDto>> getSearchedVFishingAdventures(
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end,
			@RequestParam("persons") int persons, @RequestParam("rating") double rating, @RequestParam("input") String input) {
		List<FishingAdventure> fishingAdventures = new ArrayList<>();
		for (VacationHome vh : serv.findAllAvailableVacationHomes(start, end, persons))
			if (vh.getRating() >= rating && (searchByAddress(vh, input) || vh.getVacationHomeOwner().getName().contains(input) || vh.getVacationHomeOwner().getSurname().contains(input)))
				fishingAdventures.add(vh);
		return new ResponseEntity<>(createVacationHomeDtos(fishingAdventures), HttpStatus.OK);
	}

	private boolean searchByAddress(VacationHome vh, String input) {
		return vh.getName().contains(input) || vh.getLocation().getAddress().getStreet().contains(input) || vh.getLocation().getAddress().getCity().contains(input) || vh.getLocation().getAddress().getCountry().contains(input);
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
		List<ServiceNameDto> adventures = new ArrayList<ServiceNameDto>();
		for (FishingAdventure adventure : adventureService.findByFishingInstructor(instructor)) {
			adventures.add(new ServiceNameDto(adventure));
		}
		return adventures;
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
