package isa.FishingAdventure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.FishingAdventure.service.BoatOwnerService;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping(value = "boatOwner")
public class BoatOwnerController {

	@Autowired
	private BoatOwnerService boatOwnerService;

	@GetMapping(value = "/available/dateRange")
	@Transactional
	public ResponseEntity<Boolean> getIsBoatOwnerAvailable(@RequestParam("id") Integer boatId,
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) throws ParseException {

		boolean availability = boatOwnerService.isBoatOwnerAvailableForDateRange(boatId, start, end);
		return new ResponseEntity<>(availability, HttpStatus.OK);
	}
}
