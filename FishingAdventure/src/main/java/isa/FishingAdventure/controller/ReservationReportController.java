package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AdvertiserReportDto;
import isa.FishingAdventure.dto.ReportDto;
import isa.FishingAdventure.dto.ReservationInfoDto;
import isa.FishingAdventure.model.ReservationReport;
import isa.FishingAdventure.service.ReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value="getReportsAwaitingReview")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<List<AdvertiserReportDto>> getReportsAwaitingReview(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(reportService.getAdvertiserReportsForAdmin(), HttpStatus.OK);
    }

    @PutMapping(value = "/sanctionClient/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<String> approveClientPenalty(@RequestBody AdvertiserReportDto dto) {
        ReservationReport report = reportService.findById(dto.getId());
        reportService.reviewReport(report, dto.getAdvertiserEmail(), true);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PutMapping(value = "/rejectPenalty/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<String> rejectClientPenalty(@RequestBody AdvertiserReportDto dto) {
        ReservationReport report = reportService.findById(dto.getId());
        reportService.reviewReport(report, dto.getAdvertiserEmail(), false);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
