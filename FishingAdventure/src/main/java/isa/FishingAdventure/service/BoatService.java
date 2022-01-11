package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.AvailabilityDateRange;
import isa.FishingAdventure.model.Boat;
import isa.FishingAdventure.model.BoatOwner;
import isa.FishingAdventure.repository.BoatRepository;
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
    private BoatOwnerService ownerService;

    @Autowired
    private AvailabilityDateRangeService dateRangeService;

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

    public boolean exists(Integer id) {
        return boatRepository.findById(id).isPresent();
    }

    public List<Appointment> getOffersByAdvertiser(String email) {
        BoatOwner owner = ownerService.findByEmail(email);

        List<Appointment> appointments = new ArrayList<>();
        for (Boat boat : findByBoatOwner(owner)) {
            appointments.addAll(boat.getAppointments());
        }
        return appointments;
    }

    public List<Boat> findAllAvailableBoats(Date start, Date end, int persons) {

        boolean available = true;
        ArrayList<Boat> availableBoats = new ArrayList<Boat>();
        System.out.println(findAll().size());
        for (Boat boat : findAll()) {

            if (boat.getPersons() < persons)
                continue;

            available = false;
            for (AvailabilityDateRange dateRange : dateRangeService.findByServiceProfile(boat)) {
                if (start.after(dateRange.getStartDate()) && end.before(dateRange.getEndDate())) {
                    available = true;
                    break;
                }
            }

            if (!available)
                continue;

            for (Appointment ap : boat.getAppointments()) {
                if ((start.after(ap.getStartDate()) && start.before(ap.getEndDate())) || (end.after(ap.getStartDate()) && end.before(ap.getEndDate())) || (start.before(ap.getStartDate()) && end.after(ap.getEndDate()))) {
                    available = false;
                    break;
                }
            }

            System.out.println(available);
            if (available)
                availableBoats.add(boat);
        }

        return availableBoats;
    }

    public boolean isBoatAvailableForPersons(Integer id, int persons) {
        Boat boat = findById(id);
        return boat.getPersons() >= persons;
    }

    private Boat findById(Integer id) {
        return boatRepository.findById(id).orElse(new Boat());
    }

    public Optional<Boat> findByIdIfExists(Integer id) {
        return boatRepository.findById(id);
    }

    public boolean isBoatAvailableForDateRange(Integer id, Date start, Date end) {
        Boat boat = findById(id);

        boolean available = false;
        for (AvailabilityDateRange dateRange : dateRangeService.findByServiceProfile(boat)) {
            if (start.after(dateRange.getStartDate()) && end.before(dateRange.getEndDate())) {
                available = true;
                break;
            }
        }

        if (!available)
            return false;

        for (Appointment ap : boat.getAppointments()) {
            System.out.println(start + " " + ap.getStartDate());
            System.out.println(end + " " + ap.getEndDate());
            if (start.equals(ap.getStartDate()) || end.equals(ap.getEndDate()) || (start.after(ap.getStartDate()) && start.before(ap.getEndDate())) || (end.after(ap.getStartDate()) && end.before(ap.getEndDate())) || (start.before(ap.getStartDate()) && end.after(ap.getEndDate()))) {
                available = false;
                break;
            }
        }

        return available;
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
}
