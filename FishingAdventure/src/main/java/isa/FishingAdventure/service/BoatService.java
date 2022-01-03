package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Boat;
import isa.FishingAdventure.model.BoatOwner;
import isa.FishingAdventure.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoatService {

    @Autowired
    private BoatRepository boatRepository;

    public List<Boat> findAll() {
        return boatRepository.findAll();
    }

    public List<Boat> findByBoatOwner(BoatOwner owner) {
        List<Boat> boats = new ArrayList<Boat>();
        for (Boat boat : boatRepository.findByBoatOwner(owner)) {
            if (!boat.getDeleted()) {
                boats.add(boat);
            }
        }
        return boats;
    }

    public void save(Boat boat) {
        boatRepository.save(boat);
    }

    public Boat getById(int id) {
        return boatRepository.getById(id);
    }
}
