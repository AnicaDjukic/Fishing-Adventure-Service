package isa.FishingAdventure.service;

import isa.FishingAdventure.dto.AvailablityDateRangeDto;
import isa.FishingAdventure.model.Appointment;
import isa.FishingAdventure.model.AvailabilityDateRange;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.repository.AvailabilityDateRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityDateRangeService {

    @Autowired
    private AvailabilityDateRangeRepository repository;

    @Autowired
    private ServiceProfileService serviceProfileService;

    public List<AvailabilityDateRange> getAllByServiceProfileId(int id) {
        return repository.getAllByServiceProfileId(id);
    }

    public AvailabilityDateRange getById(int id) {
        return repository.getById(id);
    }

    public AvailabilityDateRange save(AvailabilityDateRange date) {
        return repository.save(date);
    }

    public List<AvailablityDateRangeDto> delete(Integer dateRangeId, Integer serviceId) {
        ServiceProfile serviceProfile = serviceProfileService.getById(serviceId);
        AvailabilityDateRange oldDateRange = getById(dateRangeId);
        createDateRangeForExistingAppointments(serviceProfile, oldDateRange);
        repository.deleteById(oldDateRange.getId());
        return createAvailabilityDateRangeDtos(getAllByServiceProfileId(serviceProfile.getId()), serviceProfile);
    }

    private void createDateRangeForExistingAppointments(ServiceProfile serviceProfile, AvailabilityDateRange oldDateRange) {
        for (Appointment appointment : serviceProfile.getAppointments()) {
            if ((appointment.getStartDate().equals(oldDateRange.getStartDate()) || appointment.getStartDate().after(oldDateRange.getStartDate())) &&
                    (appointment.getEndDate().equals(oldDateRange.getEndDate()) || appointment.getEndDate().before(oldDateRange.getEndDate()))) {
                save(new AvailabilityDateRange(appointment.getStartDate(), appointment.getEndDate(), serviceProfile));
            }
        }
    }

    public List<AvailabilityDateRange> findByServiceProfile(ServiceProfile serviceProfile) {
        return repository.findByServiceProfile(serviceProfile);
    }

    public Optional<AvailabilityDateRange> findById(Integer id) {
        return repository.findById(id);
    }

    public List<AvailablityDateRangeDto> updateAvailabilityDate(AvailabilityDateRange oldDateRange, Date newStartDate, Date newEndDate, Integer serviceId) {
        ServiceProfile serviceProfile = serviceProfileService.getById(serviceId);
        List<AvailabilityDateRange> dateRanges = getAllByServiceProfileId(serviceProfile.getId());
        List<AvailabilityDateRange> updatedDateRanges = checkScheduledAppointments(dateRanges, serviceProfile, newStartDate, newEndDate);
        AvailabilityDateRange newDateRange = mergeOverlapingAvailabilityDates(updatedDateRanges, newStartDate, newEndDate, serviceProfile);
        if (findById(oldDateRange.getId()).isPresent()) {
            repository.deleteById(oldDateRange.getId());
        }
        save(newDateRange);
        return createAvailabilityDateRangeDtos(getAllByServiceProfileId(serviceProfile.getId()), serviceProfile);
    }

    private List<AvailablityDateRangeDto> createAvailabilityDateRangeDtos(List<AvailabilityDateRange> dateRanges, ServiceProfile profile) {
        List<AvailablityDateRangeDto> dateRangeDtos = new ArrayList<>();
        for (AvailabilityDateRange dateRange : dateRanges) {
            dateRangeDtos.add(new AvailablityDateRangeDto(dateRange.getId(), profile.getName(), dateRange.getStartDate(), dateRange.getEndDate()));
        }
        return dateRangeDtos;
    }

    private AvailabilityDateRange mergeOverlapingAvailabilityDates(List<AvailabilityDateRange> dateRanges, Date newStartDate, Date newEndDate, ServiceProfile serviceProfile) {
        AvailabilityDateRange newDateRange = new AvailabilityDateRange();
        for (AvailabilityDateRange dateRange : dateRanges) {
            if (newStartDate.before(dateRange.getStartDate()) && newEndDate.after(dateRange.getEndDate())) {
                repository.deleteById(dateRange.getId());
            } else if (newStartDate.before(dateRange.getEndDate()) && newEndDate.after(dateRange.getEndDate())) {
                newStartDate = dateRange.getStartDate();
                repository.deleteById(dateRange.getId());
            } else if (newStartDate.before(dateRange.getStartDate()) && newEndDate.after(dateRange.getStartDate())) {
                newEndDate = dateRange.getEndDate();
                repository.deleteById(dateRange.getId());
            }
        }
        newDateRange.setStartDate(newStartDate);
        newDateRange.setEndDate(newEndDate);
        newDateRange.setServiceProfile(serviceProfile);
        return newDateRange;
    }

    private List<AvailabilityDateRange> checkScheduledAppointments(List<AvailabilityDateRange> dateRanges, ServiceProfile serviceProfile, Date newStartDate, Date newEndDate) {
        for (Appointment appointment : serviceProfile.getAppointments()) {
            if (newStartDate.after(appointment.getStartDate()) || newEndDate.before(appointment.getEndDate())) {
                boolean foundMatch = false;
                for (AvailabilityDateRange dateRange : dateRanges) {
                    if (appointment.getEndDate().equals(dateRange.getStartDate())) {
                        dateRange.setStartDate(appointment.getStartDate());
                        foundMatch = true;
                        break;
                    } else if (appointment.getStartDate().equals(dateRange.getEndDate())) {
                        dateRange.setEndDate(appointment.getEndDate());
                        foundMatch = true;
                        break;
                    } else if (appointment.getStartDate().equals(dateRange.getStartDate()) && appointment.getEndDate().equals(dateRange.getEndDate())) {
                        foundMatch = true;
                        break;
                    }
                }
                if (!foundMatch) {
                    AvailabilityDateRange newRange = save(new AvailabilityDateRange(appointment.getStartDate(), appointment.getEndDate(), serviceProfile));
                    dateRanges.add(newRange);
                }
            }
        }

        return dateRanges;
    }

    public AvailabilityDateRange saveNewAvailabilityDateRange(String id, AvailablityDateRangeDto dto) {
        AvailabilityDateRange date = new AvailabilityDateRange();
        date.setStartDate(dto.getStart());
        date.setEndDate(dto.getEnd());
        date.setServiceProfile(serviceProfileService.getById(Integer.parseInt(id)));
        return save(date);
    }
}
