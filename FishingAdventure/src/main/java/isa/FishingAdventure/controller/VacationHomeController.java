package isa.FishingAdventure.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import isa.FishingAdventure.model.VacationHomeOwner;
import isa.FishingAdventure.service.AddressService;
import isa.FishingAdventure.service.VacationHomeOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import isa.FishingAdventure.dto.NewHomeDto;
import isa.FishingAdventure.dto.VacationHomeDto;
import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Address;
import isa.FishingAdventure.model.Image;
import isa.FishingAdventure.model.Location;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.model.VacationHome;
import isa.FishingAdventure.service.VacationHomeService;
import java.util.Set;
import java.util.List;

@RestController
@Configurable
@RequestMapping(value = "/vacationHome", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationHomeController{
	
	@Autowired
	private VacationHomeService  homeService;

	@Autowired
	private VacationHomeOwnerService homeOwnerService;

	@GetMapping(value = "/all")
	@PreAuthorize("hasRole('CLIENT')")
	public ResponseEntity<List<VacationHomeDto>> getAllVacationHomes() {
		System.out.println("DA");
		List<ServiceProfile> vacationHomes = homeService.findAll();

		List<VacationHomeDto> VacationHomeDto = new ArrayList<>();
		for (ServiceProfile h : vacationHomes) {
			VacationHome home = (VacationHome)h;
			VacationHomeDto dto = new VacationHomeDto(home);
			VacationHomeDto.add(dto);
		}

		return new ResponseEntity<>(VacationHomeDto, HttpStatus.OK);
	}

	@GetMapping(value = "/all/{email}")
	@PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
	public ResponseEntity<List<VacationHome>> getAllVacationHomesByEmail(@PathVariable String email) {
		VacationHomeOwner owner = homeOwnerService.findByEmail(email);
		List<VacationHome> vacationHomes = homeService.findByVacationHomeOwner(owner);
		return new ResponseEntity<>(vacationHomes, HttpStatus.OK);
	}
	
	@PostMapping(value = "/newHome")
	@PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
	public ResponseEntity<VacationHome> saveNewCottage(@RequestBody NewHomeDto dto) {
		VacationHomeOwner owner = homeOwnerService.findByEmail(dto.getVocationHomeOwner());
		VacationHome home = new VacationHome();
		

		
		home.setAvailabilityEnd(new Date());
		home.setAvailabilityStart(new Date());
		home.setDescription(dto.getDescription());
		home.setLocation(dto.getLocation());
		home.setAdditionalServices(dto.getAdditionalServices());
		home.setAppointments(dto.getAppointments());
		home.setCancellationRule(dto.getCancellationRule());
		home.setImages(new HashSet<Image>());
		home.setName(dto.getName());
		home.setRating(0.0);
		home.setRooms(dto.getRooms());
		home.setRules(dto.getRules());
		home.setVocationHomeOwner(owner);
		
		homeService.save(home);
		
		return new ResponseEntity<>(home, HttpStatus.OK);
	}
	
	@GetMapping(value = "/deleteHome/{id}")
	@PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
	public ResponseEntity<String> deleteVacationHome(@PathVariable String id) {
		homeService.delete(Integer.parseInt(id));
		
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

}
