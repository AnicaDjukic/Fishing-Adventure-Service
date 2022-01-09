package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping(value = "/getOffersByAdvertiser")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_FISHING_INSTRUCTOR')")
    @Transactional
    public ResponseEntity<List<AppointmentDto>> getOffersByAdvertiser(@RequestHeader("Authorization") String token) throws MessagingException {
        return new ResponseEntity<>(appointmentService.getOffersByAdvertiser(token), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_BOAT_OWNER') || hasRole('ROLE_FISHING_INSTRUCTOR')")
    @Transactional
    public ResponseEntity<AppointmentDto> create(@RequestBody AppointmentDto dto) throws InterruptedException, MessagingException {
        ResponseEntity<AppointmentDto> retVal = new ResponseEntity<AppointmentDto>(dto, HttpStatus.BAD_REQUEST);
        Appointment savedAppointment = createAppointment(dto);
        Integer appointmentId = appointmentService.createAppointment(savedAppointment, dto.getDuration(), dto.getServiceProfileId());
        if (appointmentId != null) {
            dto.setOfferId(appointmentId);
            retVal = new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return retVal;
    }

    private Appointment createAppointment(AppointmentDto dto) {
        Appointment newAppointment = new Appointment();
        newAppointment.setDateCreated(new Date());
        newAppointment.setPlace("");
        newAppointment.setDiscount(dto.getDiscount());
        newAppointment.setReserved(false);
        newAppointment.setMaxPersons(dto.getMaxPersons());
        newAppointment.setPrice(dto.getPrice());
        newAppointment.setOwnerPresence(false);

        newAppointment.setStartDate(dto.getStartDate());
        newAppointment.setEndDate(dto.getEndDate());
        long duration = (dto.getEndDate().getTime() - dto.getStartDate().getTime()) / (1000 * 60 * 60 * 24);
        newAppointment.setDuration(Duration.ofDays(duration));

        if (newAppointment.getChosenServices() == null) {
            Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();
            newAppointment.setChosenServices(additionalServices);
        }
        for (AdditionalService as : dto.getChosenServices()) {
            newAppointment.getChosenServices().add(as);
        }

        return newAppointment;
    }

}
