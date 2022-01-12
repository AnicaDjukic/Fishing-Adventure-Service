package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AvailableDateRangeDto;
import isa.FishingAdventure.model.AvailabilityDateRange;
import isa.FishingAdventure.model.ServiceProfile;
import isa.FishingAdventure.service.AvailabilityDateRangeService;
import isa.FishingAdventure.service.ServiceProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "availabilityDate")
@CrossOrigin
public class AvailabilityDateRangeController {

    @Autowired
    private AvailabilityDateRangeService availabilityDateRangeService;

    @Autowired
    private ServiceProfileService serviceProfileService;

    @GetMapping(value = "/getByServiceProfile/{id}")
    @PreAuthorize("hasAnyRole('ROLE_VACATION_HOME_OWNER','ROLE_BOAT_OWNER')")
    public ResponseEntity<List<AvailableDateRangeDto>> getByServiceProfile(@PathVariable String id) {
        List<AvailabilityDateRange> dates = availabilityDateRangeService.getAllByServiceProfileId(Integer.parseInt(id));

        List<AvailableDateRangeDto> dtos = new ArrayList<>();
        for (AvailabilityDateRange date : dates) {
            AvailableDateRangeDto newDto = new AvailableDateRangeDto(date);
            dtos.add(newDto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{dateRangeId}/{serviceId}")
    @PreAuthorize("hasAnyRole('ROLE_VACATION_HOME_OWNER','ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<List<AvailableDateRangeDto>> update(@PathVariable String dateRangeId, @PathVariable String serviceId, @RequestBody AvailableDateRangeDto dto) {
        AvailabilityDateRange date = availabilityDateRangeService.getById(Integer.parseInt(dateRangeId));
        ServiceProfile profile = serviceProfileService.getById(Integer.parseInt(serviceId));
        List<AvailabilityDateRange> dateRanges = availabilityDateRangeService.updateAvailabilityDate(date, dto.getStart(), dto.getEnd(), profile.getId());
        return new ResponseEntity<>(createAvailabilityDateRangeDtos(dateRanges, profile), HttpStatus.OK);
    }

    @PostMapping(value = "/save/{id}")
    @PreAuthorize("hasAnyRole('ROLE_VACATION_HOME_OWNER','ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<AvailableDateRangeDto> save(@PathVariable String id, @RequestBody AvailableDateRangeDto dto) {
        AvailabilityDateRange date = new AvailabilityDateRange();
        date.setStartDate(dto.getStart());
        date.setEndDate(dto.getEnd());
        date.setServiceProfile(serviceProfileService.getById(Integer.parseInt(id)));
        AvailabilityDateRange newDate = availabilityDateRangeService.save(date);
        dto.setId(newDate.getId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{dateRangeId}/{serviceId}")
    @PreAuthorize("hasAnyRole('ROLE_VACATION_HOME_OWNER','ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<List<AvailableDateRangeDto>> delete(@PathVariable String dateRangeId, @PathVariable String serviceId) {
        ServiceProfile profile = serviceProfileService.getById(Integer.parseInt(serviceId));
        List<AvailabilityDateRange> dateRanges = availabilityDateRangeService.delete(Integer.parseInt(dateRangeId), Integer.parseInt(serviceId));
        return new ResponseEntity<>(createAvailabilityDateRangeDtos(dateRanges, profile), HttpStatus.OK);
    }

    private List<AvailableDateRangeDto> createAvailabilityDateRangeDtos(List<AvailabilityDateRange> dateRanges, ServiceProfile profile) {
        List<AvailableDateRangeDto> dateRangeDtos = new ArrayList<>();
        for (AvailabilityDateRange dateRange : dateRanges) {
            dateRangeDtos.add(new AvailableDateRangeDto(dateRange.getId(), profile.getName(), dateRange.getStartDate(), dateRange.getEndDate()));
        }
        return dateRangeDtos;
    }
}
