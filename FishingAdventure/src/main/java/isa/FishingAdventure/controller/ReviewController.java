package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.NewReservationDto;
import isa.FishingAdventure.dto.NewReviewDto;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.Review;
import isa.FishingAdventure.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;

	@PostMapping(value = "/new")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Transactional
	public ResponseEntity<NewReviewDto> saveNewAppointment(@RequestBody NewReviewDto dto) {
		Review newReview = new Review(dto.getContent(), dto.getRate(), dto.getServiceId(), false, false);
		reviewService.addNewReview(newReview, dto.getReservationId());
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping(value = "exists/{reservationId}")
	public ResponseEntity<Boolean> exists(@PathVariable Integer reservationId) {
		return new ResponseEntity<>(reviewService.exists(reservationId), HttpStatus.OK);
	}
}
