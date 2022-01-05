package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.NewReservationDto;
import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.Client;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.security.util.TokenUtils;
import isa.FishingAdventure.service.AppointmentService;
import isa.FishingAdventure.service.ClientService;
import isa.FishingAdventure.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

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
		boolean sucess = reservationService.createReservation(token, newAppointment, dto.getCottageId());
		if(!sucess)
			return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	private Appointment getNewAppointment(NewReservationDto dto) {
		Appointment newAppointment = new Appointment();
		newAppointment.setDiscount(0.0);
		newAppointment.setReserved(true);
		newAppointment.setStartDate(dto.getStartDate());
		newAppointment.setEndDate(dto.getEndDate());
		long duration  = (dto.getEndDate().getTime() - dto.getStartDate().getTime()) / (1000 * 60 * 60 * 24);
		newAppointment.setDuration(Duration.ofDays(duration));
		newAppointment.setMaxPersons(dto.getPersons());
		if(newAppointment.getChosenServices() == null) {
			Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();
			newAppointment.setChosenServices(additionalServices);
		}
		for(AdditionalService as : dto.getChosenServices()){
			newAppointment.getChosenServices().add(as);
		}
		newAppointment.setPrice(dto.getPrice());
		return newAppointment;
	}
}
