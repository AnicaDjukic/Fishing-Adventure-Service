package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.NewAppointmentDto;
import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.between;

@RestController
@RequestMapping(value = "appointment")
public class AppointmentController{
	
	@Autowired
	private AppointmentService appointmentService;

	@PostMapping(value = "/new")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ResponseEntity<NewAppointmentDto> saveNewAppointment(@RequestBody NewAppointmentDto dto) {
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
		appointmentService.save(newAppointment);
		appointmentService.addAppointmentToVacationHome(dto.getCottageId(), newAppointment);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
