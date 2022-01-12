package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.ReportDto;
import isa.FishingAdventure.model.ReservationReport;
import isa.FishingAdventure.service.ReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "reservationReport")
@CrossOrigin
public class ReservationReportController {

    @Autowired
    private ReservationReportService reportService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_VACATION_HOME_OWNER','ROLE_BOAT_OWNER','ROLE_FISHING_INSTRUCTOR')")
    @Transactional
    public ResponseEntity<String> newReport(@RequestBody ReportDto reportDto) {
        ReservationReport report = reportService.save(reportDto.getReservationId(), reportDto.getContent());
        reportService.managePenalties(reportDto.getOption(), report);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
