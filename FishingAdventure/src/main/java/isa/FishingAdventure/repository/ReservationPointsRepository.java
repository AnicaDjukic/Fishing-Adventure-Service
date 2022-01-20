package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.ReservationPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationPointsRepository extends JpaRepository<ReservationPoints, Integer> {
}
