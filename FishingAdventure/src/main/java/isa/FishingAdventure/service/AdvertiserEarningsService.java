package isa.FishingAdventure.service;

import isa.FishingAdventure.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.FishingAdventure.repository.AdvertiserEarningsRepository;

import java.sql.DatabaseMetaData;
import java.util.Date;

@Service
public class AdvertiserEarningsService {

    @Autowired
    private AdvertiserEarningsRepository advertiserEarningsRepository;


    public void calculateEarnings(String advertiserEmail, Reservation reservation) {
        // TODO: implement loyalty program
        Double amountEarned = reservation.getAppointment().getPrice();
        save(new AdvertiserEarnings(reservation, advertiserEmail, amountEarned));
    }

    private void save(AdvertiserEarnings advertiserEarnings) {
        advertiserEarningsRepository.save(advertiserEarnings);
    }
}
