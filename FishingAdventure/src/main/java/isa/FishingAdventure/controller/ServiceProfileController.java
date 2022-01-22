package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AdditionalServiceDto;
import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.dto.OfferDto;
import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.service.ServiceProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "serviceProfile")
@CrossOrigin
public class ServiceProfileController {

    @Autowired
    private ServiceProfileService serviceProfileService;

    @GetMapping(value = "/getAdditionalServicesByName/{name}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_FISHING_INSTRUCTOR')")
    public ResponseEntity<List<AdditionalServiceDto>> getAdditionalServicesByName(
            @RequestHeader("Authorization") String token, @PathVariable String name) {
        ServiceProfile profile = serviceProfileService.getByNameForAdvertiser(name, token.split(" ")[1]);
        return new ResponseEntity<>(createAdditionalServiceDos(profile), HttpStatus.OK);
    }

    private List<AdditionalServiceDto> createAdditionalServiceDos(ServiceProfile profile) {
        List<AdditionalServiceDto> dtos = new ArrayList<>();
        for (AdditionalService service : profile.getAdditionalServices()) {
            dtos.add(new AdditionalServiceDto(service));
        }
        return dtos;
    }

    @GetMapping(value = "/getServiceInfoForOfferByName/{name}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_FISHING_INSTRUCTOR')")
    public ResponseEntity<OfferDto> getServiceInfoForOfferByName(@RequestHeader("Authorization") String token,
            @PathVariable String name) {
        ServiceProfile profile = serviceProfileService.getByNameForAdvertiser(name, token.split(" ")[1]);
        return new ResponseEntity<>(new OfferDto(profile), HttpStatus.OK);
    }

    @GetMapping(value = "/getAppointments/{id}")
    @PreAuthorize("hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_FISHING_INSTRUCTOR')")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByServiceId(@PathVariable String id) {
        ServiceProfile profile = serviceProfileService.getById(Integer.parseInt(id));
        List<AppointmentDto> dtos = createAppointmentDtos(id, profile);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    private List<AppointmentDto> createAppointmentDtos(String id, ServiceProfile profile) {
        List<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment appointment : profile.getAppointments()) {
            AppointmentDto dto = new AppointmentDto(appointment);
            dto.setServiceProfileId(Integer.parseInt(id));
            dtos.add(dto);
        }
        return dtos;
    }

    @GetMapping(value = "/isServiceAvailableForDateRange")
    public ResponseEntity<Boolean> isServiceAvailableForDateRange(@RequestParam("id") Integer id,
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) {
        return new ResponseEntity<>(serviceProfileService.isServiceAvailableForDateRange(id, start, end),
                HttpStatus.OK);
    }
}
