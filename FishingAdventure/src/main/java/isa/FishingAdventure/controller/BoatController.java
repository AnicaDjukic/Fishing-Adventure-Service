package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.BoatDto;
import isa.FishingAdventure.dto.NewBoatDto;
import isa.FishingAdventure.dto.ServiceNameDto;
import isa.FishingAdventure.model.Boat;
import isa.FishingAdventure.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "boat")
@CrossOrigin
public class BoatController {

    @Autowired
    private BoatService boatService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<BoatDto>> getAllBoats() {
        List<Boat> boats = boatService.findAllNonDeleted();
        return new ResponseEntity<>(createBoatDtos(boats), HttpStatus.OK);
    }

    private List<BoatDto> createBoatDtos(List<Boat> boats) {
        List<BoatDto> boatDtos = new ArrayList<>();
        for (Boat b : boats) {
            BoatDto dto = new BoatDto(b);
            boatDtos.add(dto);
        }
        return boatDtos;
    }

    @GetMapping(value = "/allByUser")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<List<NewBoatDto>> getAllBoatsByEmail(@RequestHeader("Authorization") String token) {
        List<Boat> ownerBoats = boatService.findByBoatOwnerToken(token.split(" ")[1]);
        return new ResponseEntity<>(createNewBoatDtos(ownerBoats), HttpStatus.OK);
    }

    private List<NewBoatDto> createNewBoatDtos(List<Boat> ownerBoats) {
        List<NewBoatDto> boats = new ArrayList<>();
        for (Boat boat : ownerBoats) {
            boats.add(new NewBoatDto(boat));
        }
        return boats;
    }

    @GetMapping(value = "/getNamesByUser")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<List<ServiceNameDto>> getNamesByUser(@RequestHeader("Authorization") String token) {
        List<Boat> ownerBoats = boatService.findByBoatOwnerToken(token.split(" ")[1]);
        return new ResponseEntity<>(createServiceNameDtos(ownerBoats), HttpStatus.OK);
    }

    private List<ServiceNameDto> createServiceNameDtos(List<Boat> ownerBoats) {
        List<ServiceNameDto> boats = new ArrayList<>();
        for (Boat boat : ownerBoats) {
            boats.add(new ServiceNameDto(boat));
        }
        return boats;
    }

    @GetMapping(value = "/deleteBoat/{id}")
    @PreAuthorize("hasAnyRole('ROLE_BOAT_OWNER', 'ROLE_ADMIN')")
    public ResponseEntity<String> deleteBoat(@PathVariable String id) {
        boatService.deleteById(Integer.parseInt(id));
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping(value = "/newBoat")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<NewBoatDto> saveNewBoat(@RequestHeader("Authorization") String token,
            @RequestBody NewBoatDto dto) {
        boatService.saveNewBoat(new Boat(dto), token.split(" ")[1]);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<NewBoatDto> updateBoat(@PathVariable String id, @RequestBody NewBoatDto dto) {
        Boat oldBoat = boatService.getById(Integer.parseInt(id));
        boatService.save(updateBoat(oldBoat, dto));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private Boat updateBoat(Boat boat, NewBoatDto dto) {
        boat.setType(dto.getType());
        boat.setMaxSpeed(dto.getMaxSpeed());
        boat.setLength(dto.getLength());
        boat.setMotorNumber(dto.getMotorNumber());
        boat.setMotorPower(dto.getMotorPower());
        boat.setName(dto.getName());
        boat.setDescription(dto.getDescription());
        boat.setCancellationRule(dto.getCancellationRule());
        boat.setAdditionalServices(dto.getAdditionalServices());
        boat.setFishingEquipment(dto.getFishingEquipments());
        boat.setNavigationEquipment(dto.getNavigationEquipments());
        boat.setRules(dto.getRules());
        boat.setPersons(dto.getPersons());
        boat.setLocation(dto.getLocation());

        return boat;
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity<NewBoatDto> getById(@PathVariable String id) {
        Boat boat = boatService.getById(Integer.parseInt(id));
        NewBoatDto dto = new NewBoatDto(boat);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Integer id) {
        return new ResponseEntity<>(boatService.exists(id), HttpStatus.OK);
    }

    @PutMapping(value = "/smallUpdate/{id}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<NewBoatDto> smallUpdate(@PathVariable String id, @RequestBody NewBoatDto dto) {
        Boat oldBoat = boatService.getById(Integer.parseInt(id));
        oldBoat.setPricePerDay(dto.getPricePerDay());
        oldBoat.setName(dto.getName());
        oldBoat.setDescription(dto.getDescription());
        boatService.save(oldBoat);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<BoatDto>> getSearchedVacationHomes(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end,
            @RequestParam("persons") int persons, @RequestParam("rating") double rating, @RequestParam("input") String input) {
        List<Boat> boats = new ArrayList<>();
        for(Boat b : boatService.findAllAvailableBoats(start, end, persons))
            if(b.getRating() >= rating && (searchByAddress(b, input) || b.getBoatOwner().getName().contains(input) || b.getBoatOwner().getSurname().contains(input)))
                boats.add(b);
        return new ResponseEntity<>(createBoatDtos(boats), HttpStatus.OK);
    }

    private boolean searchByAddress(Boat boat, String input) {
        return boat.getName().contains(input) || boat.getLocation().getAddress().getStreet().contains(input) || boat.getLocation().getAddress().getCity().contains(input) || boat.getLocation().getAddress().getCountry().contains(input);
    }

    @GetMapping(value = "/persons")
    public ResponseEntity<Integer> getBoatMaxPersons(@RequestParam("id") Integer id) {
        int maxPersons = boatService.getMaxPersons(id);
        return new ResponseEntity<>(maxPersons, HttpStatus.OK);
    }

    @GetMapping(value = "/available/dateRange")
    public ResponseEntity<Boolean> getIsCottageAvailable(@RequestParam("id") Integer id,
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) {
        boolean availability = boatService.isBoatAvailableForDateRange(id, start, end);
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }
}
