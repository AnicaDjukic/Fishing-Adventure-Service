package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.NewReservationDto;
import isa.FishingAdventure.model.AdditionalService;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "appointment")
public class AppointmentController{
	
	@Autowired
	private AppointmentService appointmentService;
}
