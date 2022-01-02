package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Location;
import isa.FishingAdventure.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location save(Location original, Location updated) {
        original.setLatitude(updated.getLatitude());
        original.setLongitude(updated.getLongitude());
        return locationRepository.save(original);
    }

}
