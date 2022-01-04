package isa.FishingAdventure.service;

import isa.FishingAdventure.model.AvailabilityDateRange;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.repository.AvailabilityDateRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityDateRangeService {

    @Autowired
    AvailabilityDateRangeRepository repository;

    public List<AvailabilityDateRange> findByServiceProfile(ServiceProfile serviceProfile){
        return repository.findByServiceProfile(serviceProfile);
    }
}
