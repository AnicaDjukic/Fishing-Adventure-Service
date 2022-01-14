package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AdvertiserReservationDto;
import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.dto.NewReservationDto;
import isa.FishingAdventure.dto.ReservationDto;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping(value = "reservation")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping(value = "/new")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<NewReservationDto> saveNewAppointment(@RequestHeader("Authorization") String token,
            @RequestBody NewReservationDto dto) {
        boolean success = reservationService.createReservation(token.split(" ")[1], new Appointment(dto),
                dto.getServiceId());
        if (!success)
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/new/specialOffer")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<AppointmentDto> reserveSpecialOffer(@RequestHeader("Authorization") String token,
            @RequestBody AppointmentDto dto) {
        reservationService.reserveSpecialOffer(token.split(" ")[1], dto.getOfferId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/newByAdvertiser")
    @PreAuthorize("hasAnyRole('ROLE_FISHING_INSTRUCTOR', 'ROLE_VACATION_HOME_OWNER', 'ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<NewReservationDto> saveNewAppointmentByAdvertiser(@RequestBody NewReservationDto dto) {
        boolean success = reservationService.createReservation(dto.getClientEmail(), new Appointment(dto),
                dto.getServiceId());
        if (!success)
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/client/current")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<List<ReservationDto>> getClientCurrentReservations(
            @RequestHeader("Authorization") String token) {
        List<Reservation> reservations = reservationService.getClientCurrentReservations(token);
        List<ServiceProfile> serviceProfiles = reservationService.getServiceProfiles(reservations);
        List<ReservationDto> reservationDtos = createReservationDtos(reservations, serviceProfiles);
        return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/client/past")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<List<ReservationDto>> getClientPastReservations(
            @RequestHeader("Authorization") String token) {
        List<Reservation> reservations = reservationService.getClientPastReservations(token);
        List<ServiceProfile> serviceProfiles = reservationService.getServiceProfiles(reservations);
        List<ReservationDto> reservationDtos = createReservationDtos(reservations, serviceProfiles);
        return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
    }

    private List<ReservationDto> createReservationDtos(List<Reservation> reservations,
            List<ServiceProfile> serviceProfiles) {
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (int i = 0; i < reservations.size(); i++) {
            reservationDtos.add(new ReservationDto(reservations.get(i), serviceProfiles.get(i)));
        }
        return reservationDtos;
    }

    @GetMapping(value = "/allByAdvertiser")
    @PreAuthorize("hasAnyRole('ROLE_FISHING_INSTRUCTOR', 'ROLE_VACATION_HOME_OWNER', 'ROLE_BOAT_OWNER')")
    public ResponseEntity<List<AdvertiserReservationDto>> findAllReservationsForInstructor(
            @RequestHeader("Authorization") String token) {
        List<AdvertiserReservationDto> reservationDtos = reservationService.findAllReservationsByAdvertiser(
                token.split(" ")[1]);
        return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
    }

    @PutMapping(value = "/cancel")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity<Boolean> cancelReservation(@RequestBody String id) {
        return new ResponseEntity<>(reservationService.cancelReservation(Integer.parseInt(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/cancelled")
    @Transactional
    public ResponseEntity<Boolean> hadCancelledReservation(@RequestHeader("Authorization") String token,
            @RequestParam("serviceId") Integer serviceId,
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) throws ParseException {

        List<Reservation> serviceProfileReservations = reservationService.getClientReservationsForServiceProfile(token
                .split(" ")[1], serviceId);
        boolean hadCancelled = reservationService.overlapsWithDateRange(serviceProfileReservations, start, end);
        return new ResponseEntity<>(hadCancelled, HttpStatus.OK);
    }
}
