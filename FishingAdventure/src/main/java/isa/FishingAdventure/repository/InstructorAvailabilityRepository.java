package isa.FishingAdventure.repository;

import isa.FishingAdventure.model.InstructorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorAvailabilityRepository  extends JpaRepository<InstructorAvailability, Integer> {
}
