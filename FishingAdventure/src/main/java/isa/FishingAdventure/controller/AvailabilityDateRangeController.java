package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AvailableDateRangeDto;
import isa.FishingAdventure.model.AvailabilityDateRange;
import isa.FishingAdventure.service.AvailabilityDateRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_VACATION_HOME_OWNER')")
    public ResponseEntity<List<AvailableDateRangeDto>> getNamesByUser(@PathVariable String id) {
        List<AvailabilityDateRange> dates = availabilityDateRangeService.getAllByServiceProfileId(Integer.parseInt(id));

        List<AvailableDateRangeDto> dtos = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            AvailableDateRangeDto newDto = new AvailableDateRangeDto(dates.get(i));
            dtos.add(newDto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
