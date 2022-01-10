package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Complaint;
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
        Reservation reservation = reservationService.findById(reservationId);
        newReview.setReservation(reservation);
        repository.save(newReview);
    }

    public boolean exists(Integer reservationId) {
        for(Review review : repository.findAll()){
            if(review.getReservation().getReservationId().equals(reservationId))
                return true;
        }

        return false;
    }
}
