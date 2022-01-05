package isa.FishingAdventure.service;

import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.repository.ServiceProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceProfileService {

    @Autowired
    private ServiceProfileRepository profileRepository;

    public void delete(int id) {
        Optional<ServiceProfile> profile = profileRepository.findById(id);
        profile.get().setDeleted(true);
        profileRepository.save(profile.get());
    }

    public ServiceProfile getById(int id) {
        return profileRepository.getById(id);
    }

    public void save(ServiceProfile profileService) {
        profileRepository.save(profileService);
    }
}
