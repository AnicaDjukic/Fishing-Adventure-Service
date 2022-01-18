package isa.FishingAdventure.service;

import isa.FishingAdventure.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.repository.AdvertiserEarningsRepository;

import java.util.Date;
import java.util.List;

@Service
public class AdvertiserEarningsService {

    @Autowired
    private AdvertiserEarningsRepository advertiserEarningsRepository;


    public void calculateEarningsForNewReservation(String advertiserEmail, Reservation reservation) {
        // TODO: implement loyalty program
        Double amountEarned = reservation.getAppointment().getPrice();
        save(new AdvertiserEarnings(reservation, advertiserEmail, amountEarned));
    }

    public void calculateEarningsForCancelledReservation(Reservation reservation, Double cancellationRule) {
        // TODO: implement loyalty program
        Double amountEarned = reservation.getAppointment().getPrice() * cancellationRule/100;
        AdvertiserEarnings advertiserEarnings = getByReservation(reservation);
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
