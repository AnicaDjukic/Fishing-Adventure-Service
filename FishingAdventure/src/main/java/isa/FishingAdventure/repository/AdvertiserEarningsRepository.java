package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.FishingAdventure.model.AdvertiserEarnings;

@Repository
public interface AdvertiserEarningsRepository extends JpaRepository<AdvertiserEarnings, Integer> {

    AdvertiserEarnings getByReservation(Reservation reservation);
}
