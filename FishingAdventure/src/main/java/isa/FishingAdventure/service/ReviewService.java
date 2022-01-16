package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.ReviewDto;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.model.Review;
import isa.FishingAdventure.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ServiceProfileService profileService;

    @Autowired
    private EmailService emailService;

    public void addNewReview(Review newReview, Integer reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        newReview.setReservation(reservation);
        repository.save(newReview);
    }

    public boolean exists(Integer reservationId) {
        for (Review review : repository.findAll()) {
            if (review.getReservation().getReservationId().equals(reservationId))
                return true;
        }
        return false;
    }

    public Review findById(Integer id) {
        return repository.getById(id);
    }

    public List<Review> getAllApprovedReviewsForService(int id) {
        List<Review> reviews = new ArrayList<>();
        for (Review review : repository.findAll()) {
            if (review.getServiceId().equals(id) && review.isApproved()) {
                reviews.add(review);
            }
        }
        return reviews;
    }

    public List<ReviewDto> getAllReviewsForServicePage(int id) {
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review : getAllApprovedReviewsForService(id)) {
            reviewDtos.add(createReviewDto(review));
        }
        return reviewDtos;
    }

    public List<ReviewDto> getReviewsForAdmin() {
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review : getAllUnreviewedReviews()) {
            reviewDtos.add(createReviewDto(review));
        }
        return reviewDtos;
    }

    private ReviewDto createReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto(review.getId(), review.getContent(), review.getRating(),
                review.getDatePosted());
        reviewDto.setClientFullName(review.getReservation().getClient().getName() + " "
                + review.getReservation().getClient().getSurname());
        reviewDto.setAdvertiserEmail(reservationService.getReservationInfo(review.getReservation()).getAdvertiserEmail());
        reviewDto.setServiceName(profileService.getById(review.getServiceId()).getName());

        return reviewDto;
    }

    public List<Review> getAllUnreviewedReviews() {
        List<Review> reviews = new ArrayList<>();
        for (Review review : repository.findAll()) {
            if (!review.isApproved() && !review.isRejected()) {
                reviews.add(review);
            }
        }
        return reviews;
    }

    public void approveReview(Review review, String advertiserEmail) {
        review.setApproved(true);
        repository.save(review);
        profileService.recalculateServiceRating(review.getServiceId());
        sendEmailAboutNewReview(review.getServiceId(), advertiserEmail);
    }

    private void sendEmailAboutNewReview(int serviceId, String advertiserEmail) {
        String serviceName = profileService.getById(serviceId).getName();
        String emailText = emailService.createGenericEmail("Review posted", "A review has been posted for" +
                serviceName + ".");
        try {
            emailService.sendEmail(advertiserEmail,"New review posted for " + serviceName, emailText);
        } catch (Exception e) {
            System.out.println("Email could not be sent.");
        }
    }

    public void rejectReview(Review review) {
        review.setRejected(true);
        repository.save(review);
    }
}
