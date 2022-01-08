package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.AppointmentDto;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.repository.VacationHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VacationHomeService {

    @Autowired
    private VacationHomeRepository homeRepository;
    @Autowired
    private AvailabilityDateRangeService dateRangeService;

    @Autowired
    private VacationHomeOwnerService ownerService;

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

    public List<VacationHome> findAllAvailableVacationHomes(Date start, Date end, int persons) {
        boolean available = true;
        ArrayList<VacationHome> availableVacationHomes = new ArrayList<VacationHome>();
        System.out.println(findAllNonDeleted().size());
        for (VacationHome vh : findAllNonDeleted()) {

            if (vh.getPersons() < persons)
                continue;

            available = false;
            for (AvailabilityDateRange dateRange : dateRangeService.findByServiceProfile(vh)) {
                if (start.after(dateRange.getStartDate()) && end.before(dateRange.getEndDate())) {
                    available = true;
                    break;
                }
            }

            if (!available)
                continue;

            for (Appointment ap : vh.getAppointments()) {
                if ((start.after(ap.getStartDate()) && start.before(ap.getEndDate())) || (end.after(ap.getStartDate()) && end.before(ap.getEndDate())) || (start.before(ap.getStartDate()) && end.after(ap.getEndDate()))) {
                    available = false;
                    break;
                }
            }

            System.out.println(available);
            if (available)
                availableVacationHomes.add(vh);
        }

        return availableVacationHomes;
    }

    public VacationHome findById(Integer id) {
        return homeRepository.findById(id).orElse(new VacationHome());
    }

    public List<AdditionalService> findAdditionalServicesByVacationHomeId(Integer id) {
        VacationHome vacationHome = findById(id);
        return new ArrayList<>(vacationHome.getAdditionalServices());
    }

    public boolean isCottageAvailableForPersons(Integer id, int persons) {
        VacationHome vacationHome = findById(id);
        return vacationHome.getPersons() >= persons;
    }

    public boolean isCottageAvailableForDateRange(Integer id, Date start, Date end) {
        VacationHome vacationHome = findById(id);

        boolean available = false;
        for (AvailabilityDateRange dateRange : dateRangeService.findByServiceProfile(vacationHome)) {
            if (start.after(dateRange.getStartDate()) && end.before(dateRange.getEndDate())) {
                available = true;
                break;
            }
        }

        if (!available)
            return false;

        for (Appointment ap : vacationHome.getAppointments()) {
            System.out.println(start + " " + ap.getStartDate());
            System.out.println(end + " " + ap.getEndDate());
            if (start.equals (ap.getStartDate()) || end.equals(ap.getEndDate()) || (start.after(ap.getStartDate()) && start.before(ap.getEndDate())) || (end.after(ap.getStartDate()) && end.before(ap.getEndDate())) || (start.before(ap.getStartDate()) && end.after(ap.getEndDate()))) {
                available = false;
                break;
            }
        }

        return available;
    }

    public boolean exists(Integer id) {
        return homeRepository.findById(id).isPresent();
    }

    // TODO: add getOffersByAdvertiser and getAppointmentDtos to FishingAdvenureService
    public List<AppointmentDto> getOffersByAdvertiser(String email) {
        VacationHomeOwner owner = ownerService.findByEmail(email);

        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (VacationHome home : findByVacationHomeOwner(owner)) {
            appointmentDtos.addAll(getAppointmentDtos(home));
        }
        return appointmentDtos;
    }

    public List<AppointmentDto> getAppointmentDtos(VacationHome home) {
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : home.getAppointments()) {
            if (!appointment.isReserved() && Date.from(appointment.getDateCreated().toInstant().plusMillis(appointment.getDuration().toMillis() / 1000)).after(new Date())) {
                AppointmentDto dto = new AppointmentDto(appointment);
                dto.setServiceProfileName(home.getName());
                dto.setServiceProfileId(home.getId());
                for (Image img : home.getImages()) {
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
}
