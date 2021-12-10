package isa.FishingAdventure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.FishingAdventure.model.FishingAdventure;

@Repository
public interface FishingAdventureRepository extends JpaRepository<FishingAdventure, Integer>{

}
