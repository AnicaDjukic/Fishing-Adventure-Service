package isa.FishingAdventure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.FishingAdventure.model.FishingInstructor;
import isa.FishingAdventure.model.User;

@Repository
public interface FishingInstructorRepository extends JpaRepository<User, Integer>{
	
	FishingInstructor findByEmail(String email);

}
