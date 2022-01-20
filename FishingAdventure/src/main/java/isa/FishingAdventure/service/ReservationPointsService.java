package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Reservation;
import isa.FishingAdventure.model.ReservationPoints;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.repository.ReservationPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationPointsService {

    @Autowired
    private ReservationPointsRepository pointsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCategoryService categoryService;

    public ReservationPoints getReservationPoints() {
        return pointsRepository.findAll().get(0);
    }

    public void save(ReservationPoints reservationPoints) { pointsRepository.save(reservationPoints); }

    public void addPointsToUsers(double payment, String clientEmail, String advertiserEmail) {
        User client = userService.findByEmail(clientEmail);
        User advertiser = userService.findByEmail(advertiserEmail);
        client.setPoints(client.getPoints() + (payment * getReservationPoints().getClientPointsPercentage() / 100));
        advertiser.setPoints(advertiser.getPoints() + (payment * getReservationPoints().getAdvertiserPointsPercentage() / 100));
        categoryService.checkUserCategories(client, advertiser);
    }

    public void subtractPointsFromUsers(double payment, String clientEmail, String advertiserEmail) {
        User client = userService.findByEmail(clientEmail);
        User advertiser = userService.findByEmail(advertiserEmail);
        client.setPoints(client.getPoints() - (payment * getReservationPoints().getClientPointsPercentage() / 100));
        advertiser.setPoints(advertiser.getPoints() - (payment * getReservationPoints().getAdvertiserPointsPercentage() / 100));
        categoryService.checkUserCategories(client, advertiser);
    }

    public void updateReservationPointPercentages(double clientPoints, double advertiserPoints) {
        ReservationPoints rp = getReservationPoints();
        rp.setClientPointsPercentage(clientPoints);
        rp.setAdvertiserPointsPercentage(advertiserPoints);
        save(rp);
    }

}
