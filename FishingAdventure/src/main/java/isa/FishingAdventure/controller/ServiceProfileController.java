package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AdditionalServiceDto;
import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.dto.OfferDto;
import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.service.ServiceProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "serviceProfile")
@CrossOrigin
public class ServiceProfileController {

    @Autowired
    private ServiceProfileService serviceProfileService;

    @GetMapping(value = "/getAdditionalServicesByName/{name}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<List<AdditionalServiceDto>> getAdditionalServicesByName(@PathVariable String name) {
        ServiceProfile profile = serviceProfileService.getByName(name);
        List<AdditionalServiceDto> dtos = new ArrayList<>();
        for (AdditionalService service : profile.getAdditionalServices()) {
            dtos.add(new AdditionalServiceDto(service));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @GetMapping(value = "/getServiceInfoForOfferByName/{name}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<OfferDto> getServiceInfoForOfferByName(@PathVariable String name) {
        ServiceProfile profile = serviceProfileService.getByName(name);
        OfferDto dto = new OfferDto(profile);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/getAppointments/{id}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByServiceId(@PathVariable String id) {
        ServiceProfile profile = serviceProfileService.getById(Integer.parseInt(id));

        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment appointment : profile.getAppointments()) {
            AppointmentDto dto = new AppointmentDto(appointment);
            dto.setServiceProfileId(Integer.parseInt(id));
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
