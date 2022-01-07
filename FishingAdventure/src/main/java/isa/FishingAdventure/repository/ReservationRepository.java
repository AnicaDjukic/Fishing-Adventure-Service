package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.Client;
import isa.FishingAdventure.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByClient(Client client);
}
