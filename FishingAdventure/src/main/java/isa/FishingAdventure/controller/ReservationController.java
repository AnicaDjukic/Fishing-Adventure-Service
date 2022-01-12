package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AdvertiserReservationDto;
import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.dto.NewReservationDto;
import isa.FishingAdventure.dto.ReservationDto;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.security.util.TokenUtils;
import isa.FishingAdventure.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping(value = "reservation")
@CrossOrigin
public class ReservationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private ReservationService reservationService;

    @PostMapping(value = "/new")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<NewReservationDto> saveNewAppointment(@RequestHeader("Authorization") String token,
            @RequestBody NewReservationDto dto) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        Appointment newAppointment = getNewAppointment(dto);
        boolean success = reservationService.createReservation(email, newAppointment, dto.getServiceId());
        if (!success)
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/new/specialOffer")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<AppointmentDto> reserveSpecialOffer(@RequestHeader("Authorization") String token,
            @RequestBody AppointmentDto dto) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        reservationService.reserveSpecialOffer(email, dto.getOfferId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/newByAdvertiser")
    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')") // TODO: add other roles
    @Transactional
    public ResponseEntity<NewReservationDto> saveNewAppointmentByAdvertiser(@RequestBody NewReservationDto dto) {
        boolean success = reservationService.createReservation(dto.getClientEmail(), getNewAppointment(dto),
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
            ReservationDto dto = new ReservationDto();
            dto.setId(reservations.get(i).getReservationId());
            dto.setStartDate(reservations.get(i).getAppointment().getStartDate());
            dto.setEndDate(reservations.get(i).getAppointment().getEndDate());
            dto.setPersons(reservations.get(i).getAppointment().getMaxPersons());
            dto.setPrice(reservations.get(i).getAppointment().getPrice());
            dto.setChosenServices(new ArrayList<>(reservations.get(i).getAppointment().getChosenServices()));
            dto.setServiceId(serviceProfiles.get(i).getId());
            dto.setServiceName(serviceProfiles.get(i).getName());
            for (Image im : serviceProfiles.get(i).getImages()) {
                if (im.isCoverImage()) {
                    dto.setImagePath(im.getPath());
                }
            }
            reservationDtos.add(dto);
        }
        return reservationDtos;
    }

    private Appointment getNewAppointment(NewReservationDto dto) {
        Appointment newAppointment = new Appointment();
        newAppointment.setPlace("");
        newAppointment.setDiscount(0.0);
        newAppointment.setReserved(true);
        newAppointment.setCancelled(false);
        newAppointment.setDateCreated(new Date());
        newAppointment.setStartDate(dto.getStartDate());
        newAppointment.setEndDate(dto.getEndDate());
        long duration = (dto.getEndDate().getTime() - dto.getStartDate().getTime()) / (1000 * 60 * 60 * 24);
        newAppointment.setDuration(Duration.ofDays(duration));
        newAppointment.setMaxPersons(dto.getPersons());
        if (newAppointment.getChosenServices() == null) {
            Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();
            newAppointment.setChosenServices(additionalServices);
        }
        for (AdditionalService as : dto.getChosenServices()) {
            newAppointment.getChosenServices().add(as);
        }
        newAppointment.setPrice(dto.getPrice());
        newAppointment.setOwnerPresence(dto.isOwnersPresence());
        return newAppointment;
    }

    @GetMapping(value = "/allByAdvertiser")
    @PreAuthorize("hasAnyRole('ROLE_FISHING_INSTRUCTOR', 'ROLE_VACATION_HOME_OWNER', 'ROLE_BOAT_OWNER')")
    public ResponseEntity<List<AdvertiserReservationDto>> findAllReservationsForInstructor(
            @RequestHeader("Authorization") String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        String role = tokenUtils.getRoleFromToken(token.split(" ")[1]);
        List<AdvertiserReservationDto> reservationDtos = reservationService.findAllReservationsByAdvertiser(email,
                role);

        return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
    }

    @PutMapping(value = "/cancel")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity<Boolean> cancelReservation(@RequestBody String id) {
        boolean cancellationSuccess = reservationService.cancelReservation(Integer.parseInt(id));
        return new ResponseEntity<>(cancellationSuccess, HttpStatus.OK);
    }

    @GetMapping(value = "/cancelled")
    @Transactional
    public ResponseEntity hadCancelledReservation(@RequestHeader("Authorization") String token,
            @RequestParam("serviceId") Integer serviceId,
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) throws ParseException {

        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        List<Reservation> serviceProfileReservations = reservationService.getClientReservationsForServiceProfile(email,
                serviceId);
        boolean hadCancelled = reservationService.overlapsWithDateRange(serviceProfileReservations, start, end);
        return new ResponseEntity(hadCancelled, HttpStatus.OK);
    }
}
