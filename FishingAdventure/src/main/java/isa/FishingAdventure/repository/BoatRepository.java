package isa.FishingAdventure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.FishingAdventure.model.Boat;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Integer> {

}
