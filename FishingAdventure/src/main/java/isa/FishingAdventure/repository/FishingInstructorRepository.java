package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.FishingAdventure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.FishingAdventure.model.FishingInstructor;

import java.util.List;

@Repository
public interface FishingInstructorRepository extends UserRepository {

	FishingInstructor findByEmail(String email);

	FishingInstructor save(FishingInstructor fishingInstructor);

	@Query(value = "SELECT * FROM fishing_instructor", nativeQuery = true)
	List<FishingInstructor> getAll();
}
