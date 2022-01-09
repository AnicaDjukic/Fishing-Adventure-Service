package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.model.Review;
import isa.FishingAdventure.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;

    @Autowired
    private ReservationService reservationService;

    public void addNewReview(Review newReview, Integer reservationId) {
        repository.save(newReview);
        Reservation reservation = reservationService.findById(reservationId);
        reservation.setReview(newReview);
        reservationService.save(reservation);
    }
}
