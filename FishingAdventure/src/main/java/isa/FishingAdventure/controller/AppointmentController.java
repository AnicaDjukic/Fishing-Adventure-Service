package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.*;

@RestController
@RequestMapping(value = "appointment")
@CrossOrigin
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/getOffersByAdvertiser")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_FISHING_INSTRUCTOR')")
    @Transactional
    public ResponseEntity<List<AppointmentDto>> getOffersByAdvertiser(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(appointmentService.getOffersByAdvertiser(token),
                HttpStatus.OK);
    }

    @GetMapping(value = "/getOffersByServiceId/{id}")
    @Transactional
    public ResponseEntity<List<AppointmentDto>> getOffersByServiceId(@PathVariable String id) {
        return new ResponseEntity<>(appointmentService.getOffersByServiceId(Integer.parseInt(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_FISHING_INSTRUCTOR')")
    @Transactional
    public ResponseEntity<AppointmentDto> create(@RequestBody AppointmentDto dto)
            throws InterruptedException, MessagingException {
        ResponseEntity<AppointmentDto> retVal = new ResponseEntity<AppointmentDto>(dto, HttpStatus.BAD_REQUEST);
        Appointment savedAppointment = new Appointment(dto);
        Integer appointmentId = appointmentService.createAppointment(savedAppointment, dto.getDuration(),
                dto.getServiceProfileId());
        if (appointmentId != null) {
            dto.setOfferId(appointmentId);
            retVal = new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return retVal;
    }

    @DeleteMapping(value = "/{offerId}/{serviceProfileId}")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_FISHING_INSTRUCTOR')")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable String offerId, @PathVariable String serviceProfileId) {
        appointmentService.deleteById(Integer.parseInt(offerId), Integer.parseInt(serviceProfileId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
