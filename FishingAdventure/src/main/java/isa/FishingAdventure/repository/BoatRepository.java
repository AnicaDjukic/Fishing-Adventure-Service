package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.Boat;
import isa.FishingAdventure.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Integer> {
    List<Boat> findByBoatOwner(BoatOwner owner);
}
