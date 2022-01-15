package isa.FishingAdventure.repository;

import java.util.List;

import isa.FishingAdventure.model.ServiceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.FishingAdventure.model.FishingAdventure;
import isa.FishingAdventure.model.FishingInstructor;

@Repository
public interface FishingAdventureRepository extends JpaRepository<FishingAdventure, Integer>{

	List<FishingAdventure> findByFishingInstructor(FishingInstructor instructor);
	List<ServiceProfile>  findFishingAdventuresByFishingInstructor(FishingInstructor instructor);
	FishingAdventure findById(int id);

}
