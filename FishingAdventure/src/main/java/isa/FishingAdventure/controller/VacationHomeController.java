package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.*;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.security.util.TokenUtils;
import isa.FishingAdventure.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@Configurable
@CrossOrigin
@RequestMapping(value = "/vacationHome", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationHomeController {

    @Autowired
    private VacationHomeService homeService;

    @Autowired
    private ServiceProfileService serviceProfileService;

    @Autowired
    private VacationHomeOwnerService homeOwnerService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value = "/all")
    public ResponseEntity<List<VacationHomeDto>> getAllVacationHomes() {
        List<VacationHome> vacationHomes = homeService.findAllNonDeleted();

        List<VacationHomeDto> vacationHomeDtos = new ArrayList<>();
        for (VacationHome h : vacationHomes) {
            VacationHomeDto dto = new VacationHomeDto(h);
            vacationHomeDtos.add(dto);

        }

        return new ResponseEntity<>(vacationHomeDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<VacationHomeDto>> getSearchedVacationHomes(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
                                                                          @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end, @RequestParam("persons") int persons) throws ParseException {
        List<VacationHome> vacationHomes = homeService.findAllAvailableVacationHomes(start, end, persons);
        List<VacationHomeDto> vacationHomeDtos = new ArrayList<VacationHomeDto>();
        for (VacationHome h : vacationHomes) {
            VacationHomeDto dto = new VacationHomeDto(h);
            vacationHomeDtos.add(dto);
        }

        return new ResponseEntity<>(vacationHomeDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/additionalServices/{id}")
    public ResponseEntity<List<AdditionalServiceDto>> getAdditionalServices(@PathVariable Integer id) throws ParseException {
        List<AdditionalService> additionalServices = homeService.findAdditionalServicesByVacationHomeId(id);
        List<AdditionalServiceDto> additionalServiceDtos = new ArrayList<AdditionalServiceDto>();
        for (AdditionalService as : additionalServices) {
            AdditionalServiceDto dto = new AdditionalServiceDto(as);
            additionalServiceDtos.add(dto);
        }
        return new ResponseEntity<>(additionalServiceDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/persons")
    public ResponseEntity getCottageMaxPersons(@RequestParam("id") Integer id) {
        int maxPersons = homeService.getMaxPersons(id);
        return new ResponseEntity(maxPersons, HttpStatus.OK);
    }

    @GetMapping(value = "/available/dateRange")
    public ResponseEntity getIsCottageAvailable(@RequestParam("id") Integer id, @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
                                                @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) throws ParseException {

        boolean availability = homeService.isCottageAvailableForDateRange(id, start, end);
        return new ResponseEntity(availability, HttpStatus.OK);
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

    @GetMapping(value = "/getNamesByUser")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<List<ServiceNameDto>> getNamesByUser(@RequestHeader("Authorization") String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        VacationHomeOwner owner = homeOwnerService.findByEmail(email);

        List<ServiceNameDto> vacationHomes = new ArrayList<ServiceNameDto>();
        for (VacationHome home : homeService.findByVacationHomeOwner(owner)) {
            vacationHomes.add(new ServiceNameDto(home));
        }

        return new ResponseEntity<>(vacationHomes, HttpStatus.OK);
    }

    @PostMapping(value = "/newHome")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<NewHomeDto> saveNewCottage(@RequestHeader("Authorization") String token, @RequestBody NewHomeDto dto) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        VacationHomeOwner owner = homeOwnerService.findByEmail(email);
        VacationHome newHome = new VacationHome();
        homeService.save(createHome(newHome, dto, owner));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    @Transactional
    public ResponseEntity<NewHomeDto> updateCottage(@PathVariable String id, @RequestBody NewHomeDto dto) {
        VacationHome oldHome = homeService.getById(Integer.parseInt(id));
        homeService.save(updateHome(oldHome, dto));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private VacationHome updateHome(VacationHome home, NewHomeDto dto) {
        home.setName(dto.getName());
        home.setDescription(dto.getDescription());
        home.setCancellationRule(dto.getCancellationRule());
        home.setAdditionalServices(dto.getAdditionalServices());
        home.setRooms(dto.getRooms());
        home.setRules(dto.getRules());
        home.setPersons(dto.getPersons());

        locationService.save(home.getLocation(), dto.getLocation());
        addressService.save(home.getLocation().getAddress(), dto.getLocation().getAddress());

        return home;
    }

    private VacationHome createHome(VacationHome home, NewHomeDto dto, VacationHomeOwner owner) {
        home.setAppointments(new HashSet<Appointment>());
        home.setImages(new HashSet<Image>());
        home.setRating(0.0);
        home.setLocation(dto.getLocation());
        home.setAdditionalServices(dto.getAdditionalServices());
        home.setCancellationRule(dto.getCancellationRule());
        home.setRooms(dto.getRooms());
        home.setRules(dto.getRules());
        home.setName(dto.getName());
        home.setDescription(dto.getDescription());
        home.setVacationHomeOwner(owner);
        home.setDeleted(false);
        home.setPersons(dto.getPersons());
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

    @GetMapping(value = "exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Integer id) {
        return new ResponseEntity<>(homeService.exists(id), HttpStatus.OK);
    }

    @PutMapping(value = "/smallUpdate/{id}")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    @Transactional
    public ResponseEntity<NewHomeDto> smallUpdate(@PathVariable String id, @RequestBody NewHomeDto dto) {
        VacationHome oldHome = homeService.getById(Integer.parseInt(id));
        oldHome.setPricePerDay(dto.getPricePerDay());
        oldHome.setName(dto.getName());
        oldHome.setDescription(dto.getDescription());
        homeService.save(oldHome);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/getServiceOffersById/{id}")
    @Transactional
    public ResponseEntity<List<AppointmentDto>> getServiceOffersById(@PathVariable String id) {
        VacationHome home = homeService.getById(Integer.parseInt(id));
        return new ResponseEntity<>(homeService.getAppointmentDtos(home), HttpStatus.OK);
    }

}

	

	

		

		

		

	

		