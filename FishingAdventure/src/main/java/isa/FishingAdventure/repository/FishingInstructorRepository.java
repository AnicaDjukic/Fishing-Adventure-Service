package isa.FishingAdventure.repository;

import org.springframework.stereotype.Repository;

import isa.FishingAdventure.model.FishingInstructor;

@Repository
public interface FishingInstructorRepository extends UserRepository {

	FishingInstructor findByEmail(String email);

	FishingInstructor save(FishingInstructor fishingInstructor);
}
