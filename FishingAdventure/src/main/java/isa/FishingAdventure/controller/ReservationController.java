package isa.FishingAdventure.controller;

import isa.FishingAdventure.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "reservation")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
}