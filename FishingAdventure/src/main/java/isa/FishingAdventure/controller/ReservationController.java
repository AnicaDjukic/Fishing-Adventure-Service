package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.NewReservationDto;
import isa.FishingAdventure.dto.ReservationDto;
import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping(value = "reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @PostMapping(value = "/new")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<NewReservationDto> saveNewAppointment(@RequestHeader("Authorization") String token, @RequestBody NewReservationDto dto) {
        Appointment newAppointment = getNewAppointment(dto);
        boolean sucess = reservationService.createReservation(token, newAppointment, dto.getServiceId());
        if (!sucess)
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/client/current")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<List<ReservationDto>> getClientCurrentReservations(@RequestHeader("Authorization") String token) {
        List<Reservation> reservations = reservationService.getClientCurrentReservations(token);
        List<ServiceProfile> serviceProfiles = reservationService.getServiceProfiles(reservations);
        List<ReservationDto> reservationDtos = createReservationDtos(reservations, serviceProfiles);
        return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/client/past")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity<List<ReservationDto>> getClientPastReservations(@RequestHeader("Authorization") String token) {
        List<Reservation> reservations = reservationService.getClientPastReservations(token);
        List<ServiceProfile> serviceProfiles = reservationService.getServiceProfiles(reservations);
        List<ReservationDto> reservationDtos = createReservationDtos(reservations, serviceProfiles);
        return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
    }

    private List<ReservationDto> createReservationDtos(List<Reservation> reservations, List<ServiceProfile> serviceProfiles) {
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for(int i = 0; i < reservations.size(); i++){
            ReservationDto dto = new ReservationDto();
            dto.setId(reservations.get(i).getReservationId());
            dto.setStartDate(reservations.get(i).getAppointment().getStartDate());
            dto.setEndDate(reservations.get(i).getAppointment().getEndDate());
            dto.setPersons(reservations.get(i).getAppointment().getMaxPersons());
            dto.setPrice(reservations.get(i).getAppointment().getPrice());
            dto.setChosenServices(new ArrayList<>(reservations.get(i).getAppointment().getChosenServices()));
            dto.setServiceName(serviceProfiles.get(i).getName());
            reservationDtos.add(dto);
        }
        return reservationDtos;
    }

    private Appointment getNewAppointment(NewReservationDto dto) {
        Appointment newAppointment = new Appointment();
        newAppointment.setPlace("");
        newAppointment.setDiscount(0.0);
        newAppointment.setReserved(true);
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
        newAppointment.setDateCreated(new Date());
        newAppointment.setOwnerPresence(dto.isOwnersPresence());
        return newAppointment;
    }
}
