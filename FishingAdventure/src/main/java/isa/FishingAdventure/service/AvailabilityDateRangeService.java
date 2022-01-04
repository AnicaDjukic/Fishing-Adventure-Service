package isa.FishingAdventure.service;

import isa.FishingAdventure.model.AvailabilityDateRange;
import isa.FishingAdventure.repository.AvailabilityDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityDateRangeService {

    @Autowired
    private AvailabilityDateRepository availabilityDateRepository;

    public List<AvailabilityDateRange> getAllByServiceProfileId(int id) {
        return availabilityDateRepository.getAllByServiceProfileId(id);
    }

    public AvailabilityDateRange getById(int id) {
        return availabilityDateRepository.getById(id);
    }

    public AvailabilityDateRange save(AvailabilityDateRange date) {
        return availabilityDateRepository.save(date);
    }

    public void delete(int id) {
        availabilityDateRepository.deleteById(id);
    }
}
