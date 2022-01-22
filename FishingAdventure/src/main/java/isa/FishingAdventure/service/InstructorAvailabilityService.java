package isa.FishingAdventure.service;

import isa.FishingAdventure.model.InstructorAvailability;
import isa.FishingAdventure.repository.InstructorAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorAvailabilityService {

    @Autowired
    private InstructorAvailabilityRepository repository;

    public void delete(InstructorAvailability availability) {repository.delete(availability);}

    public InstructorAvailability save(InstructorAvailability availability) { return repository.save(availability);}
}
