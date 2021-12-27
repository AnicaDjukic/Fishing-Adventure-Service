package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.NewHomeDto;
import isa.FishingAdventure.dto.VacationHomeDto;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.Image;
import isa.FishingAdventure.model.VacationHome;
import isa.FishingAdventure.model.VacationHomeOwner;
import isa.FishingAdventure.security.util.TokenUtils;
import isa.FishingAdventure.service.ServiceProfileService;
import isa.FishingAdventure.service.VacationHomeOwnerService;
import isa.FishingAdventure.service.VacationHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@Configurable
@RequestMapping(value = "/vacationHome", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationHomeController {

    @Autowired
    private VacationHomeService homeService;

    @Autowired
    private ServiceProfileService serviceProfileService;

    @Autowired
    private VacationHomeOwnerService homeOwnerService;

    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value = "/all")
    public ResponseEntity<List<VacationHomeDto>> getAllVacationHomes() {
        List<VacationHome> vacationHomes = homeService.findAllNonDeleted();

        List<VacationHomeDto> VacationHomeDto = new ArrayList<>();
        for (VacationHome h : vacationHomes) {
            VacationHomeDto dto = new VacationHomeDto(h);
            VacationHomeDto.add(dto);
        }

        return new ResponseEntity<>(VacationHomeDto, HttpStatus.OK);
    }

    @GetMapping(value = "/allByUser")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<List<NewHomeDto>> getAllVacationHomesByEmail(@RequestHeader("Authorization") String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        VacationHomeOwner owner = homeOwnerService.findByEmail(email);

        List<NewHomeDto> vacationHomes = new ArrayList<NewHomeDto>();
        for (VacationHome home : homeService.findByVacationHomeOwner(owner)) {
            vacationHomes.add(new NewHomeDto(home));
        }

        return new ResponseEntity<>(vacationHomes, HttpStatus.OK);
    }

    @PostMapping(value = "/newHome")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<NewHomeDto> saveNewCottage(@RequestHeader("Authorization") String token, @RequestBody NewHomeDto dto) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        VacationHomeOwner owner = homeOwnerService.findByEmail(email);
        homeService.save(createHome(dto, owner));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private VacationHome createHome(NewHomeDto dto, VacationHomeOwner owner) {
        VacationHome home = new VacationHome();
        home.setAvailabilityEnd(new Date());
        home.setAvailabilityStart(new Date());
        home.setDescription(dto.getDescription());
        home.setLocation(dto.getLocation());
        home.setAdditionalServices(dto.getAdditionalServices());
        home.setAppointments(new HashSet<Appointment>());
        home.setCancellationRule(dto.getCancellationRule());
        home.setImages(new HashSet<Image>());
        home.setName(dto.getName());
        home.setRating(0.0);
        home.setRooms(dto.getRooms());
        home.setRules(dto.getRules());
        home.setVacationHomeOwner(owner);
        return home;
    }

    @GetMapping(value = "/deleteHome/{id}")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<String> deleteVacationHome(@PathVariable String id) {
        serviceProfileService.delete(Integer.parseInt(id));
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity<NewHomeDto> getById(@PathVariable String id) {
        VacationHome home = homeService.getById(Integer.parseInt(id));
        NewHomeDto dto = new NewHomeDto(home);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

	

	

		

		

		

	

		