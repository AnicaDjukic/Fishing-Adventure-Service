package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.NewReviewDto;
import isa.FishingAdventure.dto.ReviewDto;
import isa.FishingAdventure.model.Review;
import isa.FishingAdventure.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "review")
@CrossOrigin
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

	@GetMapping(value="getReviewsForService/{serviceId}")
	@Transactional
	public ResponseEntity<List<ReviewDto>> getReviewsForService(@PathVariable Integer serviceId) {
		return new ResponseEntity<>(reviewService.getAllReviewsForServicePage(serviceId), HttpStatus.OK);
	}

	@GetMapping(value="getReviewsForAdmin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public ResponseEntity<List<ReviewDto>> getReviewsForAdmin() {
		return new ResponseEntity<>(reviewService.getReviewsForAdmin(), HttpStatus.OK);
	}

	@PutMapping(value = "/approveReview/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public ResponseEntity<String> approveReview(@RequestBody ReviewDto dto) {
		reviewService.approveReview(reviewService.findById(dto.getId()), dto.getAdvertiserEmail());
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	@PutMapping(value = "/rejectReview/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public ResponseEntity<String> rejectReview(@RequestBody ReviewDto dto) {
		reviewService.rejectReview(reviewService.findById(dto.getId()));
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}
}
