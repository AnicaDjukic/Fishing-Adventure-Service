package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.repository.FishingAdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FishingAdventureService {

    @Autowired
    private FishingAdventureRepository adventureRepository;

    @Autowired
    private FishingInstructorService instructorService;

    @Autowired
    private AvailabilityDateRangeService dateRangeService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ServiceProfileService profileService;

    public List<FishingAdventure> findAll() {
        return adventureRepository.findAll();
    }

    public List<FishingAdventure> findAllNotDeleted() {
        List<FishingAdventure> adventures = new ArrayList<>();
        for (FishingAdventure adventure : findAll()) {
            if (adventure.getDeleted().equals(false))
                adventures.add(adventure);
        }
        return adventures;
    }

    public List<FishingAdventure> findByFishingInstructor(FishingInstructor instructor) {
        List<FishingAdventure> adventures = new ArrayList<>();
        for (FishingAdventure adventure : adventureRepository.findByFishingInstructor(instructor)) {
            if (adventure.getDeleted().equals(false))
                adventures.add(adventure);
        }
        return adventures;
    }

    public List<ServiceProfile> findFishingAdventuresByFishingInstructor(FishingInstructor instructor) {
        return adventureRepository.findFishingAdventuresByFishingInstructor(instructor);
    }

    public FishingAdventure findById(int id) {
        return adventureRepository.findById(id);
    }

    public Optional<FishingAdventure> findByIdIfExists(Integer id) {
        return adventureRepository.findById(id);
    }

    public void deleteById(int id) {
        profileService.delete(id);
    }

    public List<Appointment> getOffersByAdvertiser(String email) {
        FishingInstructor instructor = instructorService.findByEmail(email);
        return getInstructorAppointments(instructor);
    }

    private List<Appointment> getInstructorAppointments(FishingInstructor instructor) {
        List<Appointment> appointments = new ArrayList<>();
        for (FishingAdventure adventure : findByFishingInstructor(instructor)) {
            appointments.addAll(adventure.getAppointments());
        }
        return appointments;
    }

    public boolean isInstructorAvailable(FishingInstructor instructor, Date start, Date end) {
        List<FishingAdventure> adventures = findByFishingInstructor(instructor);
        boolean available = false;
        List<InstructorAvailability> availabilityPeriods = new ArrayList<>(instructor.getInstructorAvailabilities());
        for (InstructorAvailability period : availabilityPeriods) {
            if (start.after(period.getStartDate()) && end.before(period.getEndDate())) {
                available = true;
                for (FishingAdventure adventure : adventures) {
                    if (areAdventuresOverlapping(start, end, new ArrayList<>(adventure.getAppointments()))) {
                        available = false;
                        break;
                    }
                }
                if (available)
                    break;
            }
        }
        return available;
    }

    private boolean areAdventuresOverlapping(Date start, Date end, List<Appointment> appointments) {
        boolean isOverlapping = false;
        for (Appointment appointment : appointments) {
            if (!reservationService.isReservationCanceled(appointment.getAppointmentId())
                    && appointment.getCancelled().equals(false) && checkIfOverlaps(start, end, appointment)) {
                isOverlapping = true;
                break;
            }
        }
        return isOverlapping;

    }

    private boolean checkIfOverlaps(Date start, Date end, Appointment appointment) {
        return (start.after(appointment.getStartDate()) && start.before(appointment.getEndDate())) ||
                (end.after(appointment.getStartDate()) && end.before(appointment.getEndDate())) ||
                start.equals(appointment.getStartDate()) || start.equals(appointment.getEndDate()) ||
                end.equals(appointment.getStartDate()) || end.equals(appointment.getEndDate());
    }

    public FishingAdventure getById(int id) {
        return adventureRepository.getById(id);
    }

    public List<AppointmentDto> getAppointmentDtos(FishingAdventure adventure) {
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : adventure.getAppointments()) {
            if (!appointment.isReserved()
                    && Date.from(appointment.getDateCreated().toInstant()
                            .plusMillis(appointment.getDuration().toMillis() / 1000)).after(new Date())) {
                appointmentDtos.add(new AppointmentDto(appointment, adventure));
            }
        }
        return appointmentDtos;
    }

    public int getMaxPersons(Integer id) {
        FishingAdventure fishingAdventure = adventureRepository.findById(id).orElse(new FishingAdventure());
        return fishingAdventure.getPersons();
    }

    public List<FishingAdventure> findAllAvailableAdventures(Date start, Date end, int persons) {
        List<FishingAdventure> fishingAdventures = new ArrayList<>();
        for(FishingInstructor fi: instructorService.findAll()) {
            if(isInstructorAvailable(fi, start, end)) {
                for(FishingAdventure fa: findByFishingInstructor(fi))
                    if(fa.getPersons() >= persons)
                        fishingAdventures.add(fa);
            }
        }
        return fishingAdventures;
    }

}
