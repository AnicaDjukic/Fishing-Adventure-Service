package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.AdvertiserEarningsDto;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.FishingAdventure.repository.AdvertiserEarningsRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private TokenUtils tokenUtils;

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

    public double calculateEarningForPeriod(Date periodStart, Date periodEnd) {
        double sum = 0;
        for (AdvertiserEarnings earnings : advertiserEarningsRepository.findAll()) {
            if (earnings.getDateOfTransaction().after(periodStart)
                    && earnings.getDateOfTransaction().before(periodEnd))
                sum += earnings.getAmountEarned();
        }
        return sum;
    }

    public List<AdvertiserEarningsDto> getAllTransactionsForPeriod(Date periodStart, Date periodEnd) {
        List<AdvertiserEarningsDto> earningsDtos = new ArrayList<>();
        for (AdvertiserEarnings earnings : advertiserEarningsRepository.findAll()) {
            if (earnings.getDateOfTransaction().after(periodStart)
                    && earnings.getDateOfTransaction().before(periodEnd))
                earningsDtos.add(new AdvertiserEarningsDto(earnings));
        }
        return earningsDtos;
    }

    public List<AdvertiserEarningsDto> getAllTransactionsForPeriodForAdvertiser(String token, Date periodStart, Date periodEnd) {
        List<AdvertiserEarningsDto> earningsDtos = new ArrayList<>();
        String email = tokenUtils.getEmailFromToken(token);
        for (AdvertiserEarnings earnings : advertiserEarningsRepository.findAll()) {
            if (earnings.getAdvertiserEmail().equals(email)
                    && earnings.getDateOfTransaction().after(periodStart)
                    && earnings.getDateOfTransaction().before(periodEnd))
                earningsDtos.add(new AdvertiserEarningsDto(earnings));
        }
        return earningsDtos;
    }
}
