package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AvailablityDateRangeDto;
import isa.FishingAdventure.model.AvailabilityDateRange;
import isa.FishingAdventure.service.AvailabilityDateRangeService;
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

    @GetMapping(value = "/getByServiceProfile/{id}")
    @PreAuthorize("hasAnyRole('ROLE_VACATION_HOME_OWNER','ROLE_BOAT_OWNER')")
    public ResponseEntity<List<AvailablityDateRangeDto>> getByServiceProfile(@PathVariable String id) {
        List<AvailabilityDateRange> dates = availabilityDateRangeService.getAllByServiceProfileId(Integer.parseInt(id));
        return new ResponseEntity<>(createAvailableDateRangeDtos(dates), HttpStatus.OK);
    }

    private List<AvailablityDateRangeDto> createAvailableDateRangeDtos(List<AvailabilityDateRange> dates) {
        List<AvailablityDateRangeDto> dtos = new ArrayList<>();
        for (AvailabilityDateRange date : dates) {
            AvailablityDateRangeDto newDto = new AvailablityDateRangeDto(date);
            dtos.add(newDto);
        }
        return dtos;
    }

    @PutMapping(value = "/update/{dateRangeId}/{serviceId}")
    @PreAuthorize("hasAnyRole('ROLE_VACATION_HOME_OWNER','ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<List<AvailablityDateRangeDto>> update(@PathVariable String dateRangeId,
            @PathVariable String serviceId, @RequestBody AvailablityDateRangeDto dto) {
        AvailabilityDateRange date = availabilityDateRangeService.getById(Integer.parseInt(dateRangeId));
        List<AvailablityDateRangeDto> dateRanges = availabilityDateRangeService.updateAvailabilityDate(date,
                dto.getStart(), dto.getEnd(), Integer.parseInt(serviceId));
        return new ResponseEntity<>(dateRanges, HttpStatus.OK);
    }

    @PostMapping(value = "/save/{id}")
    @PreAuthorize("hasAnyRole('ROLE_VACATION_HOME_OWNER','ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<AvailablityDateRangeDto> save(@PathVariable String id,
            @RequestBody AvailablityDateRangeDto dto) {
        AvailabilityDateRange newDate = availabilityDateRangeService.saveNewAvailabilityDateRange(id, dto);
        dto.setId(newDate.getId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{dateRangeId}/{serviceId}")
    @PreAuthorize("hasAnyRole('ROLE_VACATION_HOME_OWNER','ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<List<AvailablityDateRangeDto>> delete(@PathVariable String dateRangeId,
            @PathVariable String serviceId) {
        List<AvailablityDateRangeDto> dateRanges = availabilityDateRangeService.delete(Integer.parseInt(dateRangeId),
                Integer.parseInt(serviceId));
        return new ResponseEntity<>(dateRanges, HttpStatus.OK);
    }
}
