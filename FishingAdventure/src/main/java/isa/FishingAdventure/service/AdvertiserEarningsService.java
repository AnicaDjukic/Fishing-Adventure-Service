package isa.FishingAdventure.service;

import isa.FishingAdventure.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.FishingAdventure.repository.AdvertiserEarningsRepository;

import java.util.Date;

@Service
public class AdvertiserEarningsService {

    @Autowired
    private AdvertiserEarningsRepository advertiserEarningsRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserCategoryService categoryService;

    @Autowired
    private ReservationPointsService pointsService;

    public void calculateEarningsForNewReservation(String advertiserEmail, Reservation reservation) {
        double amountEarned = categoryService.calculateAmountEarned(advertiserEmail, reservation);
        pointsService.addPointsToUsers(amountEarned, reservation.getClient().getEmail(), advertiserEmail);
        save(new AdvertiserEarnings(reservation, advertiserEmail, amountEarned));
    }

    public void calculateEarningsForCancelledReservation(Reservation reservation, Double cancellationRule) {
        Double amountEarned = reservation.getAppointment().getPrice() * cancellationRule / 100;
        AdvertiserEarnings advertiserEarnings = getByReservation(reservation);
        pointsService.subtractPointsFromUsers(advertiserEarnings.getAmountEarned(), reservation.getClient().getEmail(),
                reservationService.getReservationInfo(reservation).getAdvertiserEmail());
        advertiserEarnings.setAmountEarned(amountEarned);
        advertiserEarnings.setDateOfTransaction(new Date());
        save(advertiserEarnings);
    }

    private void save(AdvertiserEarnings advertiserEarnings) {
        advertiserEarningsRepository.save(advertiserEarnings);
    }

    public AdvertiserEarnings getByReservation(Reservation reservation) {
        return advertiserEarningsRepository.getByReservation(reservation);
    }
}
