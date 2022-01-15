package isa.FishingAdventure.service;

import isa.FishingAdventure.model.*;
import isa.FishingAdventure.repository.VacationHomeRepository;
import isa.FishingAdventure.security.util.TokenUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VacationHomeService {

    @Autowired
    private VacationHomeRepository homeRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AvailabilityDateRangeService dateRangeService;

    @Autowired
    private VacationHomeOwnerService ownerService;

    @Autowired
    private ServiceProfileService profileService;

    public List<VacationHome> findAllNonDeleted() {
        List<VacationHome> homes = new ArrayList<>();
        for (VacationHome home : homeRepository.findAll()) {
            if (!home.getDeleted()) {
                homes.add(home);
            }
        }
        return homes;
    }

    public List<VacationHome> findByVacationHomeOwner(VacationHomeOwner owner) {
        List<VacationHome> homes = new ArrayList<>();
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

    public VacationHome findById(Integer id) {
        return homeRepository.findById(id).orElse(new VacationHome());
    }

    public Optional<VacationHome> findByIdIfExists(Integer id) {
        return homeRepository.findById(id);
    }

    public List<AdditionalService> findAdditionalServicesByVacationHomeId(Integer id) {
        VacationHome vacationHome = findById(id);
        return new ArrayList<>(vacationHome.getAdditionalServices());
    }

    public List<VacationHome> findAllAvailableVacationHomes(Date start, Date end, int persons) {
        ArrayList<VacationHome> availableVacationHomes = new ArrayList<>();
        for (VacationHome vh : findAllNonDeleted()) {
            if (vh.getPersons() < persons) continue;
            boolean available = isCottageAvailableForDateRange(vh.getId(), start, end);
            if (available) availableVacationHomes.add(vh);
        }
        return availableVacationHomes;
    }

    public boolean isCottageAvailableForDateRange(Integer id, Date start, Date end) {
        VacationHome vacationHome = findById(id);
        boolean available = checkAvailabilityDateRanges(start, end, vacationHome);
        if (!available) return false;
        return checkAppointments(start, end, vacationHome);
    }

    private boolean checkAppointments(Date start, Date end, VacationHome vacationHome) {
        boolean available = true;
        for (Appointment ap : vacationHome.getAppointments()) {
            if (ap.getCancelled()) continue;
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

    private boolean checkAvailabilityDateRanges(Date start, Date end, VacationHome vacationHome) {
        boolean available = false;
        for (AvailabilityDateRange dateRange : dateRangeService.findByServiceProfile(vacationHome)) {
            if (start.after(dateRange.getStartDate()) && end.before(dateRange.getEndDate())) {
                available = true;
                break;
            }
        }
        return available;
    }

    public boolean exists(Integer id) {
        return homeRepository.findById(id).isPresent();
    }

    public List<Appointment> getOffersByAdvertiser(String email) {
        VacationHomeOwner owner = ownerService.findByEmail(email);
        List<Appointment> appointments = new ArrayList<>();
        for (VacationHome home : findByVacationHomeOwner(owner)) {
            appointments.addAll(home.getAppointments());
        }
        return appointments;
    }

    public int getMaxPersons(Integer id) {
        VacationHome vacationHome = homeRepository.findById(id).orElse(new VacationHome());
        return vacationHome.getPersons();
    }

    public List<VacationHome> findByVacationHomeOwnerToken(String token) {
        String email = tokenUtils.getEmailFromToken(token);
        VacationHomeOwner owner = ownerService.findByEmail(email);
        return findByVacationHomeOwner(owner);
    }

    public void deleteById(int id) {
        profileService.delete(id);
    }

    public void saveNewHome(VacationHome vacationHome, String token) {
        String email = tokenUtils.getEmailFromToken(token);
        VacationHomeOwner owner = ownerService.findByEmail(email);
        vacationHome.setVacationHomeOwner(owner);
        save(vacationHome);
    }

    public List<ServiceProfile> findVacationHomesByVacationHomeOwner(VacationHomeOwner homeOwner) {
       return  homeRepository.findVacationHomesByVacationHomeOwner(homeOwner);
    }
}
