package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AvailableDateRangeDto;
import isa.FishingAdventure.model.AvailabilityDateRange;
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
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_BOAT_OWNER')")
    public ResponseEntity<List<AvailableDateRangeDto>> getNamesByUser(@PathVariable String id) {
        List<AvailabilityDateRange> dates = availabilityDateRangeService.getAllByServiceProfileId(Integer.parseInt(id));

        List<AvailableDateRangeDto> dtos = new ArrayList<>();
        for (AvailabilityDateRange date : dates) {
            AvailableDateRangeDto newDto = new AvailableDateRangeDto(date);
            dtos.add(newDto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<AvailableDateRangeDto> update(@PathVariable String id, @RequestBody AvailableDateRangeDto dto) {
        AvailabilityDateRange date = availabilityDateRangeService.getById(Integer.parseInt(id));

        date.setEndDate(dto.getEnd());
        date.setStartDate(dto.getStart());
        availabilityDateRangeService.save(date);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/save/{id}")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_BOAT_OWNER')")
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

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER') || hasRole('ROLE_BOAT_OWNER')")
    @Transactional
    public ResponseEntity<String> save(@PathVariable String id) {
        availabilityDateRangeService.delete(Integer.parseInt(id));
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
