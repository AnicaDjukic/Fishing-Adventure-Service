package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.ReviewDto;
import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.model.Review;
import isa.FishingAdventure.repository.ReviewRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    protected final Log loggerLog = LogFactory.getLog(getClass());

    public void addNewReview(Review newReview, Integer reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        newReview.setReservation(reservation);
        repository.save(newReview);
    }

    public List<Review> findAll() { return repository.findAll(); }

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
        reviewDto.setAdvertiserEmail(
                reservationService.getReservationInfo(review.getReservation()).getAdvertiserEmail());
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

    @Transactional
    public void approveReview(Review review, String advertiserEmail) {
        try {
        review.setApproved(true);
        repository.save(review);
        profileService.recalculateServiceRating(review.getServiceId());
        sendEmailAboutNewReview(review.getServiceId(), advertiserEmail);
        } catch (OptimisticEntityLockException e) {
            loggerLog.debug("Optimistic lock exception.");
        }
    }

    @Transactional
    public void sendEmailAboutNewReview(int serviceId, String advertiserEmail) {
        String serviceName = profileService.getById(serviceId).getName();
        String emailText = emailService.createGenericEmail("Review posted", "A review has been posted for" +
                serviceName + ".");
        try {
            emailService.sendEmail(advertiserEmail, "New review posted for " + serviceName, emailText);
        } catch (Exception e) {
            loggerLog.debug("Email could not be sent.");
        }
    }

    @Transactional
    public void rejectReview(Review review) {
        try {
        review.setRejected(true);
        repository.save(review);
        } catch (OptimisticEntityLockException e) {
            loggerLog.debug("Optimistic lock exception.");
    }
    }
}
