package isa.FishingAdventure.service;

import isa.FishingAdventure.model.*;
import isa.FishingAdventure.repository.BoatRepository;
import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoatService {

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private BoatOwnerService ownerService;

    @Autowired
    private ServiceProfileService serviceProfileService;

    @Autowired
    private AvailabilityDateRangeService dateRangeService;

    @Autowired
    private FishingEquipmentService fishingEquipmentService;

    @Autowired
    private NavigationEquipmentService navigationEquipmentService;

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

    public List<Boat> findByBoatOwnerToken(String token) {
        String email = tokenUtils.getEmailFromToken(token);
        BoatOwner owner = ownerService.findByEmail(email);
        return findByBoatOwner(owner);
    }

    public void save(Boat boat) {
        boatRepository.save(boat);
    }

    public Boat getById(int id) {
        return boatRepository.getById(id);
    }

    public boolean exists(Integer id) {
        return boatRepository.findById(id).isPresent();
    }

    public List<Appointment> getOffersByAdvertiser(String email) {
        BoatOwner owner = ownerService.findByEmail(email);
        return getOwnerAppointments(owner);
    }

    private List<Appointment> getOwnerAppointments(BoatOwner owner) {
        List<Appointment> appointments = new ArrayList<>();
        for (Boat boat : findByBoatOwner(owner)) {
            appointments.addAll(boat.getAppointments());
        }
        return appointments;
    }

    public List<Boat> findAllAvailableBoats(Date start, Date end, int persons) {
        ArrayList<Boat> availableBoats = new ArrayList<>();
        for (Boat boat : findAll()) {
            if (boat.getPersons() < persons) continue;
            boolean available = isBoatAvailableForDateRange(boat.getId(), start, end);
            if (available) availableBoats.add(boat);
        }
        return availableBoats;
    }

    public boolean isBoatAvailableForDateRange(Integer id, Date start, Date end) {
        Boat boat = findById(id);
        boolean available = checkAvailabilityDateRanges(start, end, boat);
        if (!available) return false;
        return checkAppointmentsByBoat(start, end, boat);
    }

    private boolean checkAppointmentsByBoat(Date start, Date end, Boat boat) {
        boolean available = true;
        for (Appointment ap : boat.getAppointments()) {
            if (ap.getCancelled().equals(true))
                continue;
            if (start.equals(ap.getStartDate()) || end.equals(ap.getEndDate()) ||
                    end.equals(ap.getStartDate()) || start.equals(ap.getEndDate())
                    || (start.after(ap.getStartDate()) && start.before(ap.getEndDate()))
                    || (end.after(ap.getStartDate()) && end.before(ap.getEndDate()))
                    || (start.before(ap.getStartDate()) && end.after(ap.getEndDate()))) {
                available = false;
                break;
            }
        }
        return available;
    }

    private boolean checkAvailabilityDateRanges(Date start, Date end, Boat boat) {
        boolean available = false;
        for (AvailabilityDateRange dateRange : dateRangeService.findByServiceProfile(boat)) {
            if (start.after(dateRange.getStartDate()) && end.before(dateRange.getEndDate())) {
                available = true;
                break;
            }
        }
        return available;
    }

    public Boat findById(Integer id) {
        return boatRepository.findById(id).orElse(new Boat());
    }

    public Optional<Boat> findByIdIfExists(Integer id) {
        return boatRepository.findById(id);
    }



    public List<Boat> findAllNonDeleted() {
        List<Boat> boats = new ArrayList<Boat>();
        for (Boat boat : boatRepository.findAll()) {
            if (!boat.getDeleted()) {
                boats.add(boat);
            }
        }
        return boats;
    }

    public int getMaxPersons(Integer id) {
        Boat boat = boatRepository.findById(id).orElse(new Boat());
        return boat.getPersons();
    }

    public void deleteById(int id) {
        serviceProfileService.delete(id);
    }

    public void saveNewBoat(Boat boat, String token) {
        String email = tokenUtils.getEmailFromToken(token);
        BoatOwner owner = ownerService.findByEmail(email);
        for (FishingEquipment fe : boat.getFishingEquipment()) {
            fishingEquipmentService.save(fe);
        }
        for (NavigationEquipment ne : boat.getNavigationEquipment()) {
            navigationEquipmentService.save(ne);
        }
        boat.setBoatOwner(owner);
        save(boat);
    }

    public List<ServiceProfile> findBoatsByBoatOwner(BoatOwner boatOwner) {
        return boatRepository.findBoatsByBoatOwner(boatOwner);
    }
}
