package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.AdvertiserEarningsDto;
import isa.FishingAdventure.service.AdvertiserEarningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "advertiserEarnings")
@CrossOrigin
public class AdvertiserEarningsController {

    @Autowired
    public AdvertiserEarningsService earningsService;

    @GetMapping(value = "/getEarningsForPeriod")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Double> getEarningsForPeriod(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
                                               @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) {
        return new ResponseEntity<>(earningsService.calculateEarningForPeriod(start, end), HttpStatus.OK);
    }

    @GetMapping(value = "/getTransactionsForPeriod")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AdvertiserEarningsDto>> getTransactionsForPeriod(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
                                                                                @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) {
        return new ResponseEntity<>(earningsService.getAllTransactionsForPeriod(start, end), HttpStatus.OK);
    }

    @GetMapping(value = "/getTransactionsForPeriodForAdvertiser")
    @PreAuthorize("hasAnyRole('ROLE_FISHING_INSTRUCTOR', 'ROLE_VACATION_HOME_OWNER', 'ROLE_BOAT_OWNER')")
    public ResponseEntity<List<AdvertiserEarningsDto>> getAllTransactionsForPeriodForAdvertiser(@RequestHeader("Authorization") String token,
                                                                                                @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date start,
                                                                                                @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date end) {
        return new ResponseEntity<>(earningsService.getAllTransactionsForPeriodForAdvertiser(token.split(" ")[1], start, end), HttpStatus.OK);
    }

}
