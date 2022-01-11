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
    private AvailabilityDateRangeService dateRangeService;

    @Autowired
    private ReservationService reservationService;

    public List<FishingAdventure> findAll() {
        return adventureRepository.findAll();
    }

    public List<FishingAdventure> findByFishingInstructor(FishingInstructor instructor) {
        return adventureRepository.findByFishingInstructor(instructor);
    }

    public FishingAdventure findById(int id) {
        return adventureRepository.findById(id);
    }

    public Optional<FishingAdventure> findByIdIfExists(Integer id) {
        return adventureRepository.findById(id);
    }

    public boolean isInstructorAvailable(FishingInstructor instructor, Date start, Date end) {
        List<FishingAdventure> adventures = findByFishingInstructor(instructor);

        boolean available = false;
        List<AvailabilityDateRange> availabilityPeriods = dateRangeService.findByServiceProfile(adventures.get(0));
        for (AvailabilityDateRange period : availabilityPeriods) {
            if (start.after(period.getStartDate()) && end.before(period.getEndDate())) {
                available = true;
                for (FishingAdventure adventure : adventures) {
                    if (areAdventuresOverlaping(start, end, new ArrayList<>(adventure.getAppointments()))) {
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

    private boolean areAdventuresOverlaping(Date start, Date end, List<Appointment> appointments) {
        boolean isOverlaping = false;
        for (Appointment appointment : appointments) {
            if(appointment.getCancelled().equals(true))
                continue;
            if (!reservationService.isReservationCanceled(appointment.getAppointmentId())) {
                if ((start.after(appointment.getStartDate()) && start.before(appointment.getEndDate())) ||
                        (end.after(appointment.getStartDate()) && end.before(appointment.getEndDate())) ||
                        start.equals(appointment.getStartDate()) || start.equals(appointment.getEndDate()) ||
                        end.equals(appointment.getStartDate()) || end.equals(appointment.getEndDate())) {
                    isOverlaping = true;
                    break;
                }
            }
        }
        return isOverlaping;
    }

    public FishingAdventure getById(int id) {
        return adventureRepository.getById(id);
    }

    public List<AppointmentDto> getAppointmentDtos(FishingAdventure adventure) {
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : adventure.getAppointments()) {
            if (!appointment.isReserved()
                    && Date.from(appointment.getDateCreated().toInstant().plusMillis(appointment.getDuration().toMillis() / 1000)).after(new Date())) {
                AppointmentDto dto = new AppointmentDto(appointment);
                dto.setServiceProfileName(adventure.getName());
                dto.setServiceProfileId(adventure.getId());
                for (Image img : adventure.getImages()) {
                    if (img.isCoverImage()) {
                        dto.setCoverImage(img.getPath());
                        break;
                    }
                }
                appointmentDtos.add(dto);
            }
        }
        return appointmentDtos;
    }

    public int getMaxPersons(Integer id) {
        FishingAdventure fishingAdventure = adventureRepository.findById(id).orElse(new FishingAdventure());
        return fishingAdventure.getPersons();
    }

}
