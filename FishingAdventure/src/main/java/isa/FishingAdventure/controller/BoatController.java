package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.BoatDto;
import isa.FishingAdventure.dto.NewBoatDto;
import isa.FishingAdventure.dto.ServiceNameDto;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.Boat;
import isa.FishingAdventure.model.BoatOwner;
import isa.FishingAdventure.model.Image;
import isa.FishingAdventure.security.util.TokenUtils;
import isa.FishingAdventure.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "boat")
@CrossOrigin
public class BoatController {

    @Autowired
    private ServiceProfileService serviceProfileService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private BoatOwnerService boatOwnerService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private LocationService locationService;

    @Autowired
    private AddressService addressService;

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


    @GetMapping(value = "/allByUser")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<List<NewBoatDto>> getAllBoatsByEmail(@RequestHeader("Authorization") String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        BoatOwner owner = boatOwnerService.findByEmail(email);

        List<NewBoatDto> boats = new ArrayList<NewBoatDto>();
        for (Boat boat : boatService.findByBoatOwner(owner)) {
            boats.add(new NewBoatDto(boat));
        }

        return new ResponseEntity<>(boats, HttpStatus.OK);
    }

    @GetMapping(value = "/getNamesByUser")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<List<ServiceNameDto>> getNamesByUser(@RequestHeader("Authorization") String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        BoatOwner owner = boatOwnerService.findByEmail(email);

        List<ServiceNameDto> boats = new ArrayList<ServiceNameDto>();
        for (Boat boat : boatService.findByBoatOwner(owner)) {
            boats.add(new ServiceNameDto(boat));
        }

        return new ResponseEntity<>(boats, HttpStatus.OK);
    }

    @GetMapping(value = "/deleteBoat/{id}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<String> deleteBoat(@PathVariable String id) {
        serviceProfileService.delete(Integer.parseInt(id));
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }


    @PostMapping(value = "/newBoat")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<NewBoatDto> saveNewBoat(@RequestHeader("Authorization") String token, @RequestBody NewBoatDto dto) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        BoatOwner owner = boatOwnerService.findByEmail(email);
        Boat newBoat = new Boat();
        boatService.save(createBoat(newBoat, dto, owner));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<NewBoatDto> updateBoat(@PathVariable String id, @RequestHeader("Authorization") String token, @RequestBody NewBoatDto dto) {
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

        locationService.save(boat.getLocation(), dto.getLocation());
        addressService.save(boat.getLocation().getAddress(), dto.getLocation().getAddress());

        return boat;
    }

    private Boat createBoat(Boat boat, NewBoatDto dto, BoatOwner owner) {
        boat.setType(dto.getType());
        boat.setMaxSpeed(dto.getMaxSpeed());
        boat.setLength(dto.getLength());
        boat.setMotorNumber(dto.getMotorNumber());
        boat.setMotorPower(dto.getMotorPower());
        boat.setAppointments(new HashSet<Appointment>());
        boat.setImages(new HashSet<Image>());
        boat.setRating(0.0);
        boat.setLocation(dto.getLocation());
        boat.setAdditionalServices(dto.getAdditionalServices());
        boat.setCancellationRule(dto.getCancellationRule());
        boat.setFishingEquipment(dto.getFishingEquipments());
        boat.setNavigationEquipment(dto.getNavigationEquipments());
        boat.setRules(dto.getRules());
        boat.setName(dto.getName());
        boat.setDescription(dto.getDescription());
        boat.setBoatOwner(owner);
        boat.setDeleted(false);
        boat.setPersons(dto.getPersons());
        return boat;
    }


    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity<NewBoatDto> getById(@PathVariable String id) {
        Boat boat = boatService.getById(Integer.parseInt(id));
        NewBoatDto dto = new NewBoatDto(boat);
        return new ResponseEntity<>(dto, HttpStatus.OK);
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
}
