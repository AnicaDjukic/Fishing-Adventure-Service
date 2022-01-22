package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AdditionalServiceDto;
import isa.FishingAdventure.dto.NewHomeDto;
import isa.FishingAdventure.dto.ServiceNameDto;
import isa.FishingAdventure.dto.VacationHomeDto;
import isa.FishingAdventure.model.*;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Configurable
@CrossOrigin
@RequestMapping(value = "/vacationHome", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationHomeController {

    @Autowired
    private VacationHomeService homeService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<VacationHomeDto>> getAllVacationHomes() {
        List<VacationHome> vacationHomes = homeService.findAllNonDeleted();
        return new ResponseEntity<>(createVacationHomeDtos(vacationHomes), HttpStatus.OK);
    }

    private List<VacationHomeDto> createVacationHomeDtos(List<VacationHome> vacationHomes) {
        List<VacationHomeDto> vacationHomeDtos = new ArrayList<>();
        for (VacationHome h : vacationHomes) {
            VacationHomeDto dto = new VacationHomeDto(h);
            vacationHomeDtos.add(dto);
        }
        return vacationHomeDtos;
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<VacationHomeDto>> getSearchedVacationHomes(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end,
            @RequestParam("persons") int persons, @RequestParam("rating") double rating,
            @RequestParam("input") String input) {
        List<VacationHome> vacationHomes = new ArrayList<>();
        for (VacationHome vh : homeService.findAllAvailableVacationHomes(start, end, persons))
            if (vh.getRating() >= rating
                    && (searchByAddress(vh, input) || vh.getVacationHomeOwner().getName().contains(input)
                            || vh.getVacationHomeOwner().getSurname().contains(input)))
                vacationHomes.add(vh);
        return new ResponseEntity<>(createVacationHomeDtos(vacationHomes), HttpStatus.OK);
    }

    private boolean searchByAddress(VacationHome vh, String input) {
        return vh.getName().contains(input) || vh.getLocation().getAddress().getStreet().contains(input)
                || vh.getLocation().getAddress().getCity().contains(input)
                || vh.getLocation().getAddress().getCountry().contains(input);
    }

    @GetMapping(value = "/additionalServices/{id}")
    public ResponseEntity<List<AdditionalServiceDto>> getAdditionalServices(@PathVariable Integer id) {
        List<AdditionalService> additionalServices = homeService.findAdditionalServicesByVacationHomeId(id);
        List<AdditionalServiceDto> additionalServiceDtos = createAdditionalServiceDtos(additionalServices);
        return new ResponseEntity<>(additionalServiceDtos, HttpStatus.OK);
    }

    private List<AdditionalServiceDto> createAdditionalServiceDtos(List<AdditionalService> additionalServices) {
        List<AdditionalServiceDto> additionalServiceDtos = new ArrayList<>();
        for (AdditionalService as : additionalServices) {
            AdditionalServiceDto dto = new AdditionalServiceDto(as);
            additionalServiceDtos.add(dto);
        }
        return additionalServiceDtos;
    }

    @GetMapping(value = "/persons")
    public ResponseEntity<Integer> getCottageMaxPersons(@RequestParam("id") Integer id) {
        int maxPersons = homeService.getMaxPersons(id);
        return new ResponseEntity<>(maxPersons, HttpStatus.OK);
    }

    @GetMapping(value = "/available/dateRange")
    public ResponseEntity<Boolean> getIsCottageAvailable(@RequestParam("id") Integer id,
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) {
        return new ResponseEntity<>(homeService.isCottageAvailableForDateRange(id, start, end), HttpStatus.OK);
    }

    @GetMapping(value = "/allByUser")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<List<NewHomeDto>> getAllVacationHomesByEmail(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(createNewHomeDtos(token.split(" ")[1]), HttpStatus.OK);
    }

    private List<NewHomeDto> createNewHomeDtos(String token) {
        List<NewHomeDto> vacationHomes = new ArrayList<>();
        for (VacationHome home : homeService.findByVacationHomeOwnerToken(token)) {
            vacationHomes.add(new NewHomeDto(home));
        }
        return vacationHomes;
    }

    @GetMapping(value = "/getNamesByUser")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<List<ServiceNameDto>> getNamesByUser(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(createServiceNameDtos(token.split(" ")[1]), HttpStatus.OK);
    }

    private List<ServiceNameDto> createServiceNameDtos(String token) {
        List<ServiceNameDto> vacationHomes = new ArrayList<>();
        for (VacationHome home : homeService.findByVacationHomeOwnerToken(token)) {
            vacationHomes.add(new ServiceNameDto(home));
        }
        return vacationHomes;
    }

    @PostMapping(value = "/newHome")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<NewHomeDto> saveNewCottage(@RequestHeader("Authorization") String token,
            @RequestBody NewHomeDto dto) {
        homeService.saveNewHome(new VacationHome(dto), token.split(" ")[1]);
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
        home.setLocation(dto.getLocation());
        home.setImages(dto.getImages());
        return home;
    }

    @GetMapping(value = "/deleteHome/{id}")
    @PreAuthorize("hasAnyRole('ROLE_VACATION_HOME_OWNER', 'ROLE_ADMIN')")
    public ResponseEntity<String> deleteVacationHome(@PathVariable String id) {
        homeService.deleteById(Integer.parseInt(id));
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity<NewHomeDto> getById(@PathVariable String id) {
        VacationHome home = homeService.getById(Integer.parseInt(id));
        return new ResponseEntity<>(new NewHomeDto(home), HttpStatus.OK);
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

}
