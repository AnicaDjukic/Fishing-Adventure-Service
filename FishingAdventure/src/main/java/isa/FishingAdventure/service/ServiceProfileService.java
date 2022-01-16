package isa.FishingAdventure.service;

import isa.FishingAdventure.model.*;
import isa.FishingAdventure.repository.ServiceProfileRepository;
import isa.FishingAdventure.security.util.TokenUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ServiceProfileService {

    @Autowired
    private ServiceProfileRepository profileRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private FishingAdventureService adventureService;

    @Autowired
    private VacationHomeService homeService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private ReviewService reviewService;

    public void delete(int id) {
        Optional<ServiceProfile> profile = profileRepository.findById(id);
        profile.get().setDeleted(true);
        profileRepository.save(profile.get());
    }

    public ServiceProfile getById(int id) {
        return profileRepository.getById(id);
    }

    public ServiceProfile getByAppointmentsIsContaining(Appointment appointment) {
        return profileRepository.getByAppointmentsIsContaining(appointment);
    }

    public ServiceProfile getByNameForAdvertiser(String name, String token) {
        String email = tokenUtils.getEmailFromToken(token);
        ServiceProfile service;
        String role = userService.findByEmail(email).getUserType().getName();
        if (role.equals("ROLE_FISHING_INSTRUCTOR"))
            service = getByNameForInstructor(name, email);
        else if (role.equals("ROLE_VACATION_HOME_OWNER"))
            service = getByNameForHomeOwner(name, email);
        else
            service = getByNameForBoatOwner(name, email);
        return service;
    }

    private ServiceProfile getByNameForInstructor(String name, String email) {
        ServiceProfile service = null;
        for (FishingAdventure adventure : adventureService.findAll()) {
            if (adventure.getName().equals(name)
                    && adventure.getFishingInstructor().getEmail().equals(email)) {
                service = adventure;
            }
        }
        return service;
    }

    private ServiceProfile getByNameForHomeOwner(String name, String email) {
        ServiceProfile service = null;
        for (VacationHome home : homeService.findAllNonDeleted()) {
            if (home.getName().equals(name)
                    && home.getVacationHomeOwner().getEmail().equals(email)) {
                service = home;
            }
        }
        return service;
    }

    private ServiceProfile getByNameForBoatOwner(String name, String email) {
        ServiceProfile service = null;
        for (Boat boat : boatService.findAllNonDeleted()) {
            if (boat.getName().equals(name)
                    && boat.getBoatOwner().getEmail().equals(email)) {
                service = boat;
            }
        }
        return service;
    }

    public void save(ServiceProfile profile) {
        profileRepository.save(profile);
    }

    public List<ServiceProfile> findAll() {
        return profileRepository.findAll();
    }

    public boolean isServiceAvailableForDateRange(Integer id, Date start, Date end) {
        Optional<VacationHome> home = homeService.findByIdIfExists(id);
        Optional<Boat> boat = boatService.findByIdIfExists(id);
        Optional<FishingAdventure> adventure = adventureService.findByIdIfExists(id);
        boolean isAvailable;
        if (home.isPresent()) {
            isAvailable = homeService.isCottageAvailableForDateRange(id, start, end);
        } else if (boat.isPresent()) {
            isAvailable = boatService.isBoatAvailableForDateRange(id, start, end);
        } else {
            isAvailable = adventureService.isInstructorAvailable(adventure.get().getFishingInstructor(), start, end);
        }
        return isAvailable;
    }

    public void deleteOffer(Integer offerId, Integer serviceProfileId) {
        ServiceProfile profile = profileRepository.getById(serviceProfileId);
        for (Appointment appointment : profile.getAppointments()) {
            if (appointment.getAppointmentId().equals(offerId)) {
                Set<Appointment> appointments = profile.getAppointments();
                appointments.remove(appointment);
                profile.setAppointments(appointments);
                break;
            }
        }
    }

    public void recalculateServiceRating(int id) {
        ServiceProfile serviceProfile = getById(id);
        serviceProfile.setRating(calculateAverageRatingForService(id));
        save(serviceProfile);
    }

    public double calculateAverageRatingForService(int id) {
        double sum = 0;
        int num = 0;
        double rating = 0;
        for (Review review : reviewService.getAllApprovedReviewsForService(id)) {
            if (review.getServiceId().equals(id)) {
                num += 1;
                sum += review.getRating();
            }
        }
        if (num > 0)
            rating = sum / num;

        return rating;
    }
}

