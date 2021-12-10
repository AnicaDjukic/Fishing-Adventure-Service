package isa.FishingAdventure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.FishingInstructor;

@Repository
public interface FishingAdventureRepository extends JpaRepository<FishingAdventure, Integer>{

	List<FishingAdventure> findByFishingInstructor(FishingInstructor instructor);

}
