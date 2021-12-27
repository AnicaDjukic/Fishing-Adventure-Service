package isa.FishingAdventure.service;

import isa.FishingAdventure.model.VacationHome;
import isa.FishingAdventure.model.VacationHomeOwner;
import isa.FishingAdventure.repository.VacationHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacationHomeService {

    @Autowired
    private VacationHomeRepository homeRepository;

    public List<VacationHome> findAllNonDeleted() {
        List<VacationHome> homes = new ArrayList<VacationHome>();
        for (VacationHome home : homeRepository.findAll()) {
            if (!home.getDeleted()) {
                homes.add(home);
            }
        }
        return homes;
    }

    public List<VacationHome> findByVacationHomeOwner(VacationHomeOwner owner) {
        List<VacationHome> homes = new ArrayList<VacationHome>();
        for (VacationHome home : homeRepository.findByVacationHomeOwner(owner)) {
            if (!home.getDeleted()) {
                homes.add(home);
            }
        }
        return homes;
    }

    public VacationHome save(VacationHome home) {
        return homeRepository.save(home);
    }

    public VacationHome getById(int id) {
        return homeRepository.getById(id);
    }
}
